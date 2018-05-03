package com.example.chanh.toeic9.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.chanh.toeic9.QuestionGroupSliderActivity;
import com.example.chanh.toeic9.R;

import java.util.ArrayList;

import static com.example.chanh.toeic9.QuestionGroupSliderActivity.selectedAnswers;

public class QuestionAdapter extends ArrayAdapter<Question> {
    private Context context;
    private int resource;
    private Question[] questions;

    public QuestionAdapter(@NonNull Context context, int resource, @NonNull Question[] questions) {
        super(context, resource, questions);
        this.context = context;
        this.resource = resource;
        this.questions = questions;

    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource, parent,false);

        TextView tvQuestion;
        RadioButton rbA;
        RadioButton rbB;
        RadioButton rbC;
        RadioButton rbD;
        RadioGroup rg;
        tvQuestion = (TextView) convertView.findViewById(R.id.tvquestion);
        rbA = (RadioButton) convertView.findViewById(R.id.rbA);
        rbB = (RadioButton) convertView.findViewById(R.id.rbB);
        rbC = (RadioButton) convertView.findViewById(R.id.rbC);
        rbD = (RadioButton) convertView.findViewById(R.id.rbD);
        rg =(RadioGroup) convertView.findViewById(R.id.rgAnswer);
//        tvAnswer = (TextView) convertView.findViewById(R.id.tvAnswer);
        Question q = questions[position];
        tvQuestion.setText(q.getIndexQuestion() + ". " + q.getContentQuestion());
        rbA.setText("A. " + q.getAnswerA());
        rbB.setText("B. " +q.getAnswerB());
        rbC.setText("C. " +q.getAnswerC());
        rbD.setText("D. " +q.getAnswerD());
        String choice = "";
        if(q.selectedAnswer==null){
            if(!selectedAnswers.get(Integer.valueOf(q.getIndexQuestion())).equals("null")){ //neu cau hoi do da duoc chon, ko phai set lai
                choice = selectedAnswers.get(Integer.valueOf(q.getIndexQuestion())); //de biet check cai nao
            }
            else{  //truong hop chua lua chon thi ko lam gi

            }
        }
        else{  //truong hop nay dc goi tu khi onDataChanged notify
            //ca truong hop cau hoi duoc chon hay ko dc chon deu phai add vao ArrayList va set choice
            selectedAnswers.set(Integer.valueOf(q.getIndexQuestion()), q.selectedAnswer);
            choice = q.selectedAnswer;
        }
        if (choice.equals("A")){
            rbA.setChecked(true);
//            selectedAnswers.set(Integer.valueOf(q.getIndexQuestion()),"A");
        }
        else if (choice.equals("B")){
            rbB.setChecked(true);
//            selectedAnswers.set(Integer.valueOf(q.getIndexQuestion()),"B");
        }
        else if (choice.equals("C")){
            rbC.setChecked(true);
//            selectedAnswers.set(Integer.valueOf(q.getIndexQuestion()),"C");
        }
        else if (choice.equals("D")){
            rbD.setChecked(true);
//            selectedAnswers.set(Integer.valueOf(q.getIndexQuestion()),"D");
        }
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                Question question1=questions[position];
                RadioButton radioButton= (RadioButton) group.findViewById(checkedId);
//                selectedAnswers.set(Integer.valueOf(q.getIndexQuestion()),"A");
                questions[position].selectedAnswer = radioButton.getText().toString().substring(0,1);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
}
