package com.example.chanh.toeic.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.chanh.toeic.R;
import com.example.chanh.toeic.adapter.CheckAnswerAdapter;
import com.example.chanh.toeic.adapter.ScreenSlidePageFragment;
import com.example.chanh.toeic.data.SQLDBSource;
import com.example.chanh.toeic.model.Questions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ScreenSlideActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 5; // dinh nghia so trang

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;
    SQLDBSource db;
    TextView txtTime,tvXemDiemP5,txtP2Questions;
    ImageButton btnPrevP5,btnNextP5;
    ImageButton btnDoneP5;
    ArrayList<Questions> arr_Quest;
    CounterClass timer;
    String part;
    int num_practice,num_practice2;
    public int showAnswer=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part5);
        txtTime = (TextView) findViewById(R.id.txtTime);
        tvXemDiemP5 = (TextView) findViewById(R.id.tvXemDiemP5);
        txtP2Questions = (TextView) findViewById(R.id.txtP2Questions);
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.vp_smile);
        btnDoneP5 = (ImageButton) findViewById(R.id.btnDoneP5);
        btnNextP5 = (ImageButton) findViewById(R.id.btnNextP5);
        btnPrevP5 = (ImageButton) findViewById(R.id.btnPrevP5);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageTransformer(true, new DepthPageTransformer());



        Intent intent = getIntent(); // lay ve num_practice
        part = intent.getStringExtra("part");
        num_practice = intent.getIntExtra("num_practice",0);
        num_practice2 = intent.getIntExtra("num_practice2",0);
        arr_Quest = new ArrayList<Questions>();
        db = new SQLDBSource(this);
        arr_Quest = (ArrayList<Questions>) db.layDanhSachCauHoi(num_practice,part);

        btnDoneP5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckAnser();

            }
        });
        timer = new CounterClass(45*1000,1000);
        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, float positionOffset, int positionOffsetPixels) {
                int vitri = position+1;


                txtP2Questions.setText(String.valueOf(vitri)+"/"+NUM_PAGES); // set text page
                if(position==0) {
                    btnPrevP5.setVisibility(View.INVISIBLE); // hide button

                } else {
                    btnPrevP5.setVisibility(View.VISIBLE);// show button
                }

                if(position== NUM_PAGES-1){
                    btnNextP5.setVisibility(View.INVISIBLE);
                } else {
                    btnNextP5.setVisibility(View.VISIBLE);
                }




            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        timer.start();
        tvXemDiemP5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                finish(); chuyen qua man hinh chinh
                Intent intent = new Intent(ScreenSlideActivity.this,chamDiem_P5Activity.class);
                intent.putExtra("arr_Quest",arr_Quest); // truyen mang sang
                intent.putExtra("num_practice2",num_practice2);
                startActivity(intent);
            }
        });

        btnNextP5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int trang = mPager.getCurrentItem() +1 ;
                mPager.setCurrentItem(trang);
            }
        });
        btnPrevP5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int trang = mPager.getCurrentItem() -1 ;
                mPager.setCurrentItem(trang);
            }
        });

    }


    // hien nut xem diem, ket thuc thi
    public void showKetQua(){
        showAnswer=1;
        if (mPager.getCurrentItem()>=4){ // load them  trang thi backgroud no moi hien
            mPager.setCurrentItem(mPager.getCurrentItem()-4);
        }else if (mPager.getCurrentItem()<=4) { // load them  trang thi backgroud no moi hien
            mPager.setCurrentItem(mPager.getCurrentItem() + 4);
        }
        tvXemDiemP5.setVisibility(View.VISIBLE); // hien
        btnDoneP5.setVisibility(View.GONE); // an
    }


    // DEM THOI GIAN
    public class CounterClass extends CountDownTimer {
        // millisinfurtr 60*1000 coutdown ... thoi gian thuc hien nhay so
     public CounterClass(long millisInFuture, long countDownInterval) {
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
    public void CheckAnser(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.check_part5_dialog);
        dialog.setTitle("Your Result!");
        CheckAnswerAdapter answerAdapter = new CheckAnswerAdapter((ArrayList) arr_Quest,this);
        GridView gridView = (GridView) dialog.findViewById(R.id.grid_ketqua);
        gridView.setAdapter(answerAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPager.setCurrentItem(position); // click vao cau nao ra cau do
                dialog.dismiss();
            }
        });
        Button btn_dong,btnKetThuc;
        btn_dong = (Button) dialog.findViewById(R.id.btn_dong);
        btnKetThuc = (Button) dialog.findViewById(R.id.btnKetThuc);
        btn_dong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                countDownTimer.cancel();
//                showKetQua();
                timer.cancel();
                showKetQua();
                dialog.dismiss();


            }
        });

        dialog.show();
    }
    public ArrayList<Questions> getData(){
        return  arr_Quest;
    }
    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            dialogExit();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }
    public void dialogExit(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_exit);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn thoát hay không?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                timer.cancel();
                finish(); // thoat activity
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {

            super(fm);
        }


        @Override
        public Fragment getItem(int position) { // position vi tri hien tai cua cau hoi
            return ScreenSlidePageFragment.create(position,showAnswer); // lay gia tri page ve , showkq !=0 ket thuc
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }


    public class DepthPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 0) { // [-1,0]
                // Use the default slide transition when moving to the left page
                view.setAlpha(1);
                view.setTranslationX(0);
                view.setScaleX(1);
                view.setScaleY(1);

            } else if (position <= 1) { // (0,1]
                // Fade the page out.
                view.setAlpha(1 - position);

                // Counteract the default slide transition
                view.setTranslationX(pageWidth * -position);

                // Scale the page down (between MIN_SCALE and 1)
                float scaleFactor = MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - Math.abs(position));
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }
}
