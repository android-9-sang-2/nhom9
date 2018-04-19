package com.example.chanh.toeic.Activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.chanh.toeic.Fragment.GroupQuestionPageFragment;
import com.example.chanh.toeic.R;
import com.example.chanh.toeic.data.SQLDBSource;
import com.example.chanh.toeic.model.QuestionGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
// Duong
public class QuestionGroupSliderActivity extends FragmentActivity {
    private static final int SOTRANG = 5; // so trang
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    SQLDBSource db;
    DemGio timer;
    TextView txtTime,tvXemDiem,txtQuestions;
    ImageButton btnPrev,btnNext,btnDone;
    List<QuestionGroup> questionGroupList;
    String part;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_group_slider);
        txtTime = (TextView) findViewById(R.id.txtTime);
        tvXemDiem = (TextView) findViewById(R.id.tvXemDiem);
        txtQuestions = (TextView) findViewById(R.id.txtQuestions);
        btnPrev = (ImageButton) findViewById(R.id.btnPrev);
        btnNext = (ImageButton) findViewById(R.id.btnNext);
        btnDone = (ImageButton) findViewById(R.id.btnDone);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        // lay du lieu
        db = new SQLDBSource(this);
        questionGroupList = db.laydanhSachQuestionGroup(1,1);  // truyen vao 2 gia tri
        // chay thoi gian
        timer = new DemGio(45*1000*60,1000);
        timer.start();

        pagerAdapter = new QuestionGroupPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(true, new chuyenTrang());
        xuLyButton();
    }

    public void xuLyButton(){
        tvXemDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // xu ly xem diem
            }
        });
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // xu ly dap an
            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem( viewPager.getCurrentItem()+1); // trang hien tai + 1
            }
        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem( viewPager.getCurrentItem()-1); // trang hien tai -1
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int trangHienTai = position+1;
                txtQuestions.setText(String.valueOf(trangHienTai)+"/"+SOTRANG);
                if(position==0) {
                    btnPrev.setVisibility(View.INVISIBLE); // hide button

                } else {
                    btnPrev.setVisibility(View.VISIBLE);// show button
                }

                if(position== SOTRANG-1){
                    btnNext.setVisibility(View.INVISIBLE);
                } else {
                    btnNext.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    public List<QuestionGroup> getData(){
        return  questionGroupList;
    }

    private class DemGio extends CountDownTimer{
        // millisinfurtr 60*1000 coutdown ... thoi gian thuc hien nhay so
        public DemGio(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String text = String.format(Locale.getDefault(), "%02d:%02d", // dinh dang thoi gian 45:02
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60);
            txtTime.setText(text);
        }

        @Override
        public void onFinish() {
            txtTime.setText("00:00"); // khi ket thuc dat gia tri bang 0
        }
    }
    private class QuestionGroupPagerAdapter extends FragmentStatePagerAdapter {
        public QuestionGroupPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            return GroupQuestionPageFragment.create(position); // them show kq vao
        }

        @Override
        public int getCount() {
            return SOTRANG; // tra ve so trang
        }
    }
    public class chuyenTrang implements ViewPager.PageTransformer{
        private static final float MIN_SCALE = 0.75f;
        @Override
        public void transformPage(View page, float position) {
            int pageWidth = page.getWidth();
            if(position<-1){
                // khong truoc qua trai duoc
                page.setAlpha(0);
            } else if (position<=0){
                page.setAlpha(1);
                page.setTranslationX(0);
                page.setScaleX(1);
                page.setScaleY(1);
            } else if(position<=1){
                page.setAlpha(1 - position);
                page.setTranslationX(pageWidth* -position);
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
            } else {page.setAlpha(0);} // vi tri cuoi k truoc duoc
        }
    }
}
