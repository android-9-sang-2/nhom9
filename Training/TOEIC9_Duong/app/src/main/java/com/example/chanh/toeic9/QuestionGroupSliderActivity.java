package com.example.chanh.toeic9;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chanh.toeic9.data.DBManager;
import com.example.chanh.toeic9.fragment.GroupQuestionPageFragment;
import com.example.chanh.toeic9.model.QuestionGroup;
import com.example.chanh.toeic9.model.TestSet;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

// Duong
public class QuestionGroupSliderActivity extends FragmentActivity {
    public static ArrayList<String> selectedAnswers;
    public ArrayList<String> correctAnswers; //se lay ngay tu dau
    public boolean reviewmode;

    TestSet testSet;
    String indexPart, indexTestSet, audio;
    int SOTRANG = 5; // so trang
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    Handler handler;
    //    SQLDBSource db;
    DemGio timer;
    TextView txtTime,tvXemDiem,txtQuestions;
    ImageButton btnPrev,btnNext,btnDone, btnPlay;
    SeekBar sbMedia;
    LinearLayout llviewpager;
    QuestionGroup[] questionGroupArray;
    RelativeLayout rlMediaPlayer, footer;
    String part;
    int thoigian;

    MediaPlayer mediaPlayer;
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(QuestionGroupSliderActivity.this,TestSetActivity.class);
        intent.putExtra("indexPart", indexPart);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_group_slider);
        Intent intent = getIntent();
        testSet = (TestSet) intent.getSerializableExtra("testSet");
        reviewmode = intent.getBooleanExtra("reviewmode",false);
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
        //nhan du lieu tu REVIEW
        if(reviewmode){
            selectedAnswers = intent.getStringArrayListExtra("selectedAnswers");
        }

        AnhXa();
        hienDuLieuTheoPart();
        //set MediaPlayer xuat hien hay ko,



        // lay du lieu
//        final DBManager dbManager1 = new DBManager(this, "toeic81");
        questionGroupArray = dbManager.getQuestionGroupArray(indexPart, indexTestSet);
        SOTRANG = questionGroupArray.length;
//        db = new SQLDBSource(this);
//        questionGroupList = db.laydanhSachQuestionGroup(3,1);  // truyen vao 2 gia tri
        // chay thoi gian
        timer = new DemGio(thoigian,1000);
        timer.start();

        pagerAdapter = new QuestionGroupPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(true, new chuyenTrang());
        xuLyButton();
    }
    public void AnhXa(){
        llviewpager= (LinearLayout) findViewById(R.id.llviewpager);
        rlMediaPlayer= findViewById(R.id.rlMediaPlayer);
        btnPlay = findViewById(R.id.btnPlay);

        txtTime = (TextView) findViewById(R.id.txtTime);
        tvXemDiem = (TextView) findViewById(R.id.tvXemDiem);
        txtQuestions = (TextView) findViewById(R.id.txtQuestions);
        btnPrev = (ImageButton) findViewById(R.id.btnPrev);
        sbMedia = (SeekBar) findViewById(R.id.sbMedia);
        btnNext = (ImageButton) findViewById(R.id.btnNext);
        btnDone = (ImageButton) findViewById(R.id.btnDone);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        footer = findViewById(R.id.footer);
    }
    public void hienDuLieuTheoPart(){
        if(indexPart.equalsIgnoreCase("1")
                || indexPart.equalsIgnoreCase("2")
                || indexPart.equalsIgnoreCase("3")
                || indexPart.equalsIgnoreCase("4")){
            rlMediaPlayer.setVisibility(View.VISIBLE);
            footer.setBackgroundColor(Color.parseColor("#0288D1"));
            //DAI
            if(!reviewmode){

            }
        }
        else {
            rlMediaPlayer.setVisibility(View.INVISIBLE);
            ViewGroup.LayoutParams params = llviewpager.getLayoutParams(); // set lai height cho listviewpager
            params.height = 280;
            llviewpager.setLayoutParams(params);
        }
        if (indexPart.equalsIgnoreCase("1")){
            thoigian=5*60*1000;
        } else if(indexPart.equalsIgnoreCase("2")){
            thoigian=10*60*1000;
        } else if(indexPart.equalsIgnoreCase("3")){
            thoigian=11*60*1000;
        } else if(indexPart.equalsIgnoreCase("4")){
            thoigian=16*60*1000;
        } else if(indexPart.equalsIgnoreCase("5")){
            thoigian=25*60*1000;
        } else if(indexPart.equalsIgnoreCase("6")){
            thoigian=8*60*1000;
        } else if(indexPart.equalsIgnoreCase("7")){
            thoigian=60*60*1000;
        }


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
        khoiTaoMediaPlayer();


        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    // neu dang play -> pause > doi hinh play
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.play);
                } else {
                    // dang ngung -> phat -> doi hinh pause
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.pause);

                }
                UpdateSeekBar();

            }
        });
        sbMedia.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // keo qua keo lai
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // khi cham vao
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // khi vua chon xong
                mediaPlayer.seekTo(sbMedia.getProgress());
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
                intent.putExtra("testSet", (Serializable) testSet);//truyen 1 object boo de(indexPart, indexTestSet, audio) de cho nut REVIEW
                intent.putStringArrayListExtra("selectedAnswers", selectedAnswers);
                startActivity(intent);


            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                String trangHienTai="";
                if(indexPart.equalsIgnoreCase("1")
                        || indexPart.equalsIgnoreCase("2")
                        || indexPart.equalsIgnoreCase("5")){
                    trangHienTai= String.valueOf(position+1)+"/"+SOTRANG;
                } else {
                    if(position==0){
                        trangHienTai= String.valueOf(position+1)+"-"+String.valueOf(position+3)+"/"+(SOTRANG*3);
                    } else  trangHienTai= String.valueOf(position*3+1)+"-"+String.valueOf(position*3+3)+"/"+SOTRANG*3;

                }
                txtQuestions.setText(trangHienTai);
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

    @Override
    protected void onPause() {
        super.onPause();
//        handler.removeCallbacksAndMessages(null);
        mediaPlayer.stop(); // stop khi out

    }
    private void UpdateSeekBar(){ // seekbar se thay doi theo thoi gian
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sbMedia.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this,500); // lap lai 0.5s
            }
        },100); // giam muc nho nhat de vua click k cap nhat
    }
    private void khoiTaoMediaPlayer(){
        mediaPlayer = new MediaPlayer();
        String media_path=audio;
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        Uri uri = Uri.parse(media_path);
        try {
            mediaPlayer.setDataSource(getApplicationContext(),uri);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sbMedia.setMax(mediaPlayer.getDuration()); // gan gia tri cho seekbar biet bai hat dai bao nhieu
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // xong media
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();
                btnPlay.setImageResource(R.drawable.play);
                // xu ly su kien o day
                khoiTaoMediaPlayer();
            }
        });
    }
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

    public boolean getMode(){
        return reviewmode;
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
