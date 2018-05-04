package com.example.chanh.toeic9.fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chanh.toeic9.QuestionGroupSliderActivity;
import com.example.chanh.toeic9.R;
import com.example.chanh.toeic9.data.DBManager;
import com.example.chanh.toeic9.model.Question;
import com.example.chanh.toeic9.model.QuestionAdapter;
import com.example.chanh.toeic9.model.QuestionGroup;

import java.util.ArrayList;

public class GroupQuestionPageFragment extends android.support.v4.app.Fragment{
    QuestionGroup questionGroups[];
    public static final String ARG_PAGE = "page";
    private int pageNumber;
    TextView tvContent;
    ImageView imgP1;
    ListView lvQuestions;
    public GroupQuestionPageFragment(){

    }
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        questionGroups = new ArrayList<QuestionGroup>();

        // lay data tu activity
        QuestionGroupSliderActivity questionGroupSliderActivity = (QuestionGroupSliderActivity) getActivity();
        questionGroups = questionGroupSliderActivity.getData();
        pageNumber= getArguments().getInt(ARG_PAGE); // lay vi tri trang voi key la page

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) { // tao view
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.content_questiongroup,container,false);
        tvContent = (TextView) view.findViewById(R.id.tvContent);
        lvQuestions = (ListView) view.findViewById(R.id.lvQuestions);
        imgP1 = (ImageView) view.findViewById(R.id.imgP1);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) { // set noi dung cho viewpager
        super.onActivityCreated(savedInstanceState);
        if(getItem(pageNumber).getContent()==null){
            tvContent.setVisibility(View.INVISIBLE);
        }
        else tvContent.setText(getItem(pageNumber).getContent());
        if(getItem(pageNumber).getIndexPart().equals("1")){
            imgP1.setVisibility(View.VISIBLE);
        }
        //DAI them
        Question[] questions;
        final DBManager dbManager = new DBManager(this.getContext(), "toeic81");
        String indexPart = getItem(pageNumber).getIndexPart();
        String indexTestSet = getItem(pageNumber).getIndexTestSet();
        String indexQuestionGroup = getItem(pageNumber).getIndexQuestionGroup();
        questions = dbManager.getQuestionArray(indexPart, indexTestSet, indexQuestionGroup);
        QuestionAdapter questionAdapter = new QuestionAdapter(this.getContext(), R.layout.question_row, questions);
        lvQuestions.setAdapter(questionAdapter);
//        parts = dbManager.getPartArray();
    }
    public QuestionGroup getItem(int position){ // lay gia tri dua theo vi tri
        return questionGroups[position];
    }

    public static android.support.v4.app.Fragment create(int pageNumber){
        GroupQuestionPageFragment fragment = new GroupQuestionPageFragment();
        Bundle args = new Bundle(); // goi du lieu de gui di
        args.putInt(ARG_PAGE,pageNumber); // gui page = sotrang
        fragment.setArguments(args);
        return fragment;
    }
}
