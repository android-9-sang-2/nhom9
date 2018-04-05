package com.example.chanh.toeic.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.chanh.toeic.R;
import com.example.chanh.toeic.model.Questions;

import java.util.ArrayList;

public class Part5Adapter extends PagerAdapter { // co the dung FragmentAdapter
    private  ArrayList<Questions> cauHoiPart5;
    private LayoutInflater inflater;

    OnAnswerListener onAnswerListener;
    public void setOnAnswerListener(OnAnswerListener onAnswerListener) {
        this.onAnswerListener = onAnswerListener;
    }

    public Part5Adapter(Context context, ArrayList<Questions> cauHoiPart5) {
        this.cauHoiPart5 = cauHoiPart5;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return cauHoiPart5.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) { //  kiểm tra  các đối tượng đc trả về bởi instantiateItem() đc liên kết với View được cung cấp

        return view.equals(object);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        // chuyen xml thanh view
        View view = inflater.inflate(R.layout.viewpager_part5,container,false);
        // Anh xa view
        TextView txtCHP5 = (TextView) view.findViewById(R.id.txtCHP5);
        RadioButton rdAP5 = (RadioButton) view.findViewById(R.id.rdAP5);
        RadioButton rdBP5 = (RadioButton) view.findViewById(R.id.rdBP5);
        RadioButton rdCP5 = (RadioButton) view.findViewById(R.id.rdCP5);
        RadioButton rdDP5 = (RadioButton) view.findViewById(R.id.rdDP5);
        final TextView txtTipsP5 = (TextView) view.findViewById(R.id.txtTipsP5);
        ImageButton btnTipP5 = (ImageButton) view.findViewById(R.id.btnTipP5);
        // Them du lieu vao
        final Questions questions = cauHoiPart5.get(position);
        String tmp = String.valueOf(position+1);
        txtCHP5.setText("Questions "+ tmp +": "+questions.getCauHoi());
        rdAP5.setText("(A). " + questions.getAns_A());
        rdBP5.setText("(B). " + questions.getAns_B());
        rdCP5.setText("(C). " + questions.getAns_C());
        rdDP5.setText("(D). " + questions.getAns_D());
        txtTipsP5.setText(questions.getHuongDan());

        // bat su kien khi click
        rdAP5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    onAnswerListener.onAnswer(questions,position,AnswerTAG.A);

                }
            }
        });

        rdBP5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    onAnswerListener.onAnswer(questions,position,AnswerTAG.B); // .B gia tri trong enum
                }
            }
        });

        rdCP5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    onAnswerListener.onAnswer(questions,position,AnswerTAG.C);
                }
            }
        });

        rdDP5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    onAnswerListener.onAnswer(questions,position,AnswerTAG.D);
                }
            }
        });
        btnTipP5.setOnClickListener(new View.OnClickListener() {
            int showing = 2;
            @Override
            public void onClick(View v) {

                if(showing%2==0){
                    txtTipsP5.setVisibility(View.VISIBLE);
                    showing +=1;
                }else{
                    txtTipsP5.setVisibility(View.INVISIBLE);
                    showing +=1;
                }

            }
        });

        // them vao container
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((View) object);
    }

    public interface OnAnswerListener
    {
        void onAnswer(Questions questions, int position, AnswerTAG answer);
    }


   public enum AnswerTAG{ // liet ke dap an
        A,B,C,D
    }

}
