package com.example.chanh.toeic.adapter;


import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chanh.toeic.Activity.ScreenSlideActivity;
import com.example.chanh.toeic.R;
import com.example.chanh.toeic.model.Questions;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScreenSlidePageFragment extends Fragment {
    ArrayList<Questions> arr_Quest;
    public static final String ARG_PAGE="page";
    public static final String ARG_showAnswer="showAnswer";
    private  int pageNumber;// vi tri trang hien tai
    public int showAnswer; // kiem tra dap an
    TextView txtCHP5;
    RadioButton rdAP5;
    RadioButton rdBP5;
    RadioButton rdCP5 ;
    RadioButton rdDP5 ;
    RadioGroup rdGP5;
    public ScreenSlidePageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.viewpager_part5, container, false);
        txtCHP5 = (TextView) rootView.findViewById(R.id.txtCHP5);
        rdAP5 = (RadioButton) rootView.findViewById(R.id.rdAP5);
        rdBP5 = (RadioButton) rootView.findViewById(R.id.rdBP5);
        rdCP5 = (RadioButton) rootView.findViewById(R.id.rdCP5);
        rdDP5 = (RadioButton) rootView.findViewById(R.id.rdDP5);
        rdGP5 = (RadioGroup) rootView.findViewById(R.id.rdGP5);
        return rootView;
    }

    // lay data tu activity


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arr_Quest = new ArrayList<Questions>();

        // lay data tu activity
        ScreenSlideActivity screenSlideActivity = (ScreenSlideActivity) getActivity();
        arr_Quest = screenSlideActivity.getData();
        pageNumber= getArguments().getInt(ARG_PAGE); // lay vi tri trang voi key la page
        showAnswer = getArguments().getInt(ARG_showAnswer);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        txtCHP5.setText("Questions "+ (pageNumber+1) +": "+ getItem(pageNumber).getCauHoi());
        rdAP5.setText("(A). " + getItem(pageNumber).getAns_A());
        rdBP5.setText("(B). " + getItem(pageNumber).getAns_B());
        rdCP5.setText("(C). " + getItem(pageNumber).getAns_C());
        rdDP5.setText("(D). " +getItem(pageNumber).getAns_D());
        rdGP5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                getItem(pageNumber).choiceID=checkedId; // luu gia tri id vao12 choice
                getItem(pageNumber).setTraloi(getChoiceFromID(checkedId));
                //                Toast.makeText(getActivity(),"Day la dap an" + checkedId,Toast.LENGTH_SHORT).show();
            }
        });
        if (showAnswer!=0){
            rdAP5.setClickable(false);// khong cho check nua
            rdBP5.setClickable(false);
            rdCP5.setClickable(false);
            rdDP5.setClickable(false);
            getDapAn(getItem(pageNumber).getDapAn().toString());
        }
//        txtTipsP5.setText(questions.getHuongDan());

    }


    public Questions getItem(int position){
        return arr_Quest.get(position);
    }
    // lay vi tri cua radoigroup chuyen thanh dap an ABCD
    private  String getChoiceFromID(int ID){
        if (ID==R.id.rdAP5) return "A";
        else if (ID==R.id.rdBP5) return "B";
        else if (ID==R.id.rdCP5) return "C";
        else if  (ID==R.id.rdDP5) return "D";
        else return "";

    }
    public static  ScreenSlidePageFragment create(int pageNumber,int checkAnswer){
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle(); // goi du lieu lai, gui di
        args.putInt(ARG_PAGE,pageNumber); // gui di, gan tag
        args.putInt(ARG_showAnswer,checkAnswer);
        fragment.setArguments(args);
        return fragment;
    }

    // ham kiem tra ket qua cau dung
    public void getDapAn(String dapAn){
        if(dapAn.equals("A")==true){
            rdAP5.setBackgroundColor(Color.RED);
        }

        else if(dapAn.equals("B")==true){
            rdBP5.setBackgroundColor(Color.RED);
        }else if(dapAn.equals("C")==true) {
            rdCP5.setBackgroundColor(Color.RED);
        }
            else if(dapAn.equals("D")==true){
                rdDP5.setBackgroundColor(Color.RED);
        }else {}
    }
}
