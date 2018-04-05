package com.example.chanh.toeic.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chanh.toeic.R;


import com.example.chanh.toeic.adapter.CheckAnswerAdapter;
import com.example.chanh.toeic.adapter.Part5Adapter;
import com.example.chanh.toeic.data.SQLDBSource;
import com.example.chanh.toeic.model.Answer;
import com.example.chanh.toeic.model.Questions;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Part5Activity extends Activity {
    private ViewPager viewPager;
    SQLDBSource db;
    TextView txtP2Questions,txtCHP5,txtTime,tvTraloi,txtTipsP5;
    ImageButton btnPrevP5,btnNextP5;
    RadioGroup rgP5;
    ImageButton btnDoneP5;
    SharedPreferences saveDapAn;


    List<Questions> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part5);
        btnNextP5 = (ImageButton) findViewById(R.id.btnNextP5);
        btnPrevP5 = (ImageButton) findViewById(R.id.btnPrevP5);

        rgP5 = (RadioGroup) findViewById(R.id.rgP5);
        btnDoneP5 = (ImageButton) findViewById(R.id.btnDoneP5);
        txtTime = (TextView) findViewById(R.id.txtTime);
        db = new SQLDBSource(this);
        list = db.layDanhSachCauHoi();
        initView();
        saveDapAn = getSharedPreferences("luutruthongtin", Context.MODE_PRIVATE); // khong cho nuoi khac doc

        // coutdown...( tong thoi gian thuc hien,thoi gian thuc hien hanh dong) 1000 - 1 ms onTick se chay 1 lan. sau do se finish
        CountDownTimer countDownTimer = new CountDownTimer(1500000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                txtTime.setText(String.valueOf((millisUntilFinished / 60000)+":"+(millisUntilFinished % 60000 / 1000)));
            }

            @Override
            public void onFinish() {
                txtTime.setText("Done!");
            }
        }.start();

        btnNextP5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(getItem(+1) );
            }
        });
        btnPrevP5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(getItem(-1) );
            }
        });
//        ViewPager myview = (ViewPager) findViewById(R.id.vp_smile);
//        final TextView txtTipsP5 = (TextView) myview.findViewById(R.id.txtTipsP5);// activiti ko tro den layout



    }

    //
    private  void  luuDapAn(String cauHoi,String dapAn){
        SharedPreferences.Editor edit = saveDapAn.edit();
        edit.putString(cauHoi,dapAn);
        edit.commit();
    }
    // kiem tra dap an
    public void CheckAnser(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.check_part5_dialog);
        dialog.setTitle("Your Result!");
        CheckAnswerAdapter answerAdapter = new CheckAnswerAdapter((ArrayList) list,this);
        GridView gridView = (GridView) dialog.findViewById(R.id.grid_ketqua);
        gridView.setAdapter(answerAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
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
                // do something
            }
        });
        dialog.show();
    }
    // next or prev trang
    private int getItem(int i){
        return viewPager.getCurrentItem() + i;
    }

    private void initView() {
        viewPager = findViewById(R.id.vp_smile);
        final Part5Adapter adapter = new Part5Adapter(this, (ArrayList<Questions>) list);

        // bat su kien de lay gia tri da chon
        adapter.setOnAnswerListener(new Part5Adapter.OnAnswerListener() {
            @Override
            public void onAnswer(Questions questions, int position, Part5Adapter.AnswerTAG answer) {
                //dap an se tra ve trong nay
                db.layDanhSachCauHoi();
                luuDapAn(String.valueOf(position+1),answer.name());
//                Log.e("acncd",String.valueOf(questions.getId()));
//                Log.e("TRL",answer.name() +  String.valueOf(position));
            }
        });
        btnDoneP5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.notifyDataSetChanged();
                CheckAnser();
                adapter.notifyDataSetChanged();

            }
        });
        txtP2Questions = (TextView) findViewById(R.id.txtP2Questions);
        txtCHP5  = (TextView) findViewById(R.id.txtCHP5);

        // set so cau hoi 1/60
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, float positionOffset, int positionOffsetPixels) {
                int vitri = position+1;
                txtP2Questions.setText(String.valueOf(vitri)+"/"+list.size()); // set text page
                if(position==0) {
                    btnPrevP5.setVisibility(View.INVISIBLE); // hide button

                } else {
                    btnPrevP5.setVisibility(View.VISIBLE);// show button
                }

                if(position== list.size()-1){
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

//        adapter.showTips(btnTipP5,txtTipsP5);

        viewPager.setAdapter(adapter);
    }

}
