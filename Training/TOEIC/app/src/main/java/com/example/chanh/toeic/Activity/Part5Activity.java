package com.example.chanh.toeic.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.chanh.toeic.R;

import com.example.chanh.toeic.adapter.Part5Adapter;
import com.example.chanh.toeic.data.SQLDBSource;
import com.example.chanh.toeic.model.Questions;

import java.util.ArrayList;
import java.util.List;

public class Part5Activity extends Activity {
    private ViewPager viewPager;
    SQLDBSource db;
    TextView txtP2Questions,txtCHP5,txtTime;
    ImageButton btnPrevP5,btnNextP5;
    RadioGroup rgP5;
    ImageButton btnDoneP5;

    List<Questions> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part5);
        db = new SQLDBSource(this);
        list = db.layDanhSachCauHoi();
        initView();
        btnNextP5 = (ImageButton) findViewById(R.id.btnNextP5);
        btnPrevP5 = (ImageButton) findViewById(R.id.btnPrevP5);
        rgP5 = (RadioGroup) findViewById(R.id.rgP5);
        btnDoneP5 = (ImageButton) findViewById(R.id.btnDoneP5);
        txtTime = (TextView) findViewById(R.id.txtTime);
        btnDoneP5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CheckAnser();
            }
        });

        // coutdown...( tong thoi gian thuc hien,thoi gian thuc hien hanh dong)
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





    }

//    public void CheckAnser(){
//        final Dialog dialog = new Dialog(this);
//        dialog.setContentView(R.layout.check_part5_dialog);
//        dialog.setTitle("Your Result!");
//        CheckAnswerAdapter answerAdapter = new CheckAnswerAdapter((ArrayList) list,this);
//        GridView gridView = (GridView) dialog.findViewById(R.id.grid_ketqua);
//        gridView.setAdapter(answerAdapter);
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                dialog.dismiss();
//            }
//        });
//        Button btn_dong,btnKetThuc;
//        btn_dong = (Button) findViewById(R.id.btn_dong);
//        dialog.show();
//    }
    // next or prev trang
    private int getItem(int i){
        return viewPager.getCurrentItem() + i;
    }
    private void initView() {
        viewPager = findViewById(R.id.vp_smile);
        Part5Adapter adapter = new Part5Adapter(this, (ArrayList<Questions>) list);

        txtP2Questions = (TextView) findViewById(R.id.txtP2Questions);
        txtCHP5  = (TextView) findViewById(R.id.txtCHP5);
        // set so cau hoi 1/60
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
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


//                rgP5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(RadioGroup group, int checkedId) {
//                        String value = "";
//                        switch (checkedId){
//                            case R.id.rbAnswerA:
//                                value="A";
//                                Log.e("Dap An","aaaa");
//                                break;
//                        }
//                    }
//                });
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(adapter);
    }
}
