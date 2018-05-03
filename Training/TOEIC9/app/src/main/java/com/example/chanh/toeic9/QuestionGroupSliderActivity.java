package com.example.chanh.toeic9;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chanh.toeic9.data.DBManager;
import com.example.chanh.toeic9.fragment.GroupQuestionPageFragment;
import com.example.chanh.toeic9.model.Question;
import com.example.chanh.toeic9.model.QuestionGroup;
import com.example.chanh.toeic9.model.TestSet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

// Duong
public class QuestionGroupSliderActivity extends FragmentActivity {
    public static ArrayList<String> selectedAnswers;
    public ArrayList<String> correctAnswers; //se lay ngay tu dau


    TestSet testSet;
    String indexPart, indexTestSet, audio;
    int SOTRANG = 5; // so trang
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
//    SQLDBSource db;
    DemGio timer;
    TextView txtTime,tvXemDiem,txtQuestions;
    ImageButton btnPrev,btnNext,btnDone, btnPlay, btnPause;
    QuestionGroup[] questionGroupArray;
    RelativeLayout rlMediaPlayer;
//    List<QuestionGroup> questionGroupList;
    String part;

    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_group_slider);
        Intent intent = getIntent();
//        indexPart = intent.getStringExtra("indexPart");
//        indexTestSet = intent.getStringExtra("indexTestSet");
        testSet = (TestSet) intent.getSerializableExtra("testSet");
        indexPart = testSet.getIndexPart();
        indexTestSet = testSet.getIndexTestSet();
        audio = testSet.getAudio();
         //kiem tra xem co tong so bao nhieu cau hoi tuong ung indexTestSet va indexPart nay
        final DBManager dbManager = new DBManager(this, "toeic81");
        //lay cac cau tra loi dung
        correctAnswers = dbManager.getCorrectAnswers(indexPart, indexTestSet);
        //dem so luong cau hoi
        int count = dbManager.countQuestion(indexPart, indexTestSet);
        //dua tren so luong do, chuan bi ArrayList selectedAnswers
        selectedAnswers = new ArrayList<>();
        for (int i = 0; i <= count; i++) {   //ko su dung index 0
            selectedAnswers.add("null");
        }


        rlMediaPlayer= findViewById(R.id.rlMediaPlayer);
        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        //set MediaPlayer xuat hien hay ko,
        if(indexPart.equalsIgnoreCase("1")
                || indexPart.equalsIgnoreCase("2")
                || indexPart.equalsIgnoreCase("3")
                || indexPart.equalsIgnoreCase("4")){
            rlMediaPlayer.setVisibility(View.VISIBLE);
        }
        else {
            rlMediaPlayer.setVisibility(View.INVISIBLE);
        }
        txtTime = (TextView) findViewById(R.id.txtTime);
        tvXemDiem = (TextView) findViewById(R.id.tvXemDiem);
        txtQuestions = (TextView) findViewById(R.id.txtQuestions);
        btnPrev = (ImageButton) findViewById(R.id.btnPrev);
        btnNext = (ImageButton) findViewById(R.id.btnNext);
        btnDone = (ImageButton) findViewById(R.id.btnDone);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        // lay du lieu
//        final DBManager dbManager1 = new DBManager(this, "toeic81");
        questionGroupArray = dbManager.getQuestionGroupArray(indexPart, indexTestSet);
        SOTRANG = questionGroupArray.length;
//        db = new SQLDBSource(this);
//        questionGroupList = db.laydanhSachQuestionGroup(3,1);  // truyen vao 2 gia tri
        // chay thoi gian
        timer = new DemGio(45*1000*60,1000);
        timer.start();

        pagerAdapter = new QuestionGroupPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(true, new chuyenTrang());
        xuLyButton();
    }

    public void xuLyButton(){
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
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = new MediaPlayer();
                String media_path=audio;
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                Uri uri = Uri.parse(media_path);
                try {
                    mediaPlayer.setDataSource(getApplicationContext(),uri);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    Toast.makeText(getApplicationContext(), "Playback started", Toast.LENGTH_SHORT).show();
//                    btnPlay.setVisibility(View.INVISIBLE);
//                    btnPause.setVisibility(View.VISIBLE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
//                        btnPlay.setVisibility(View.VISIBLE);
//                        btnPause.setVisibility(View.INVISIBLE);
                        mediaPlayer.release();
                        mediaPlayer=null;
                        Toast.makeText(getApplicationContext(), "Playback started", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
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
                int countCorrectAnswer=0;
                for(int i=1; i<correctAnswers.size(); i++){
                    if(selectedAnswers.get(i).equals(correctAnswers.get(i))){
                        countCorrectAnswer++;
                    }
                }
                Intent intent = new Intent(QuestionGroupSliderActivity.this,ResultActivity.class);
                intent.putExtra("count_correct_answer", countCorrectAnswer);
                intent.putExtra("count_question", correctAnswers.size()-1);
                startActivity(intent);


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
//    public List<QuestionGroup> getData(){
//        return  questionGroupList;
//    }

    private class DemGio extends CountDownTimer {
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
            return questionGroupArray.length; // tra ve so trang
        }
    }

    public QuestionGroup[] getData(){
        return  questionGroupArray;
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
