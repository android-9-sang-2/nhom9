package com.example.chanh.toeic09.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.chanh.toeic09.R;
import com.example.chanh.toeic09.data.DBManager;
import com.example.chanh.toeic09.model.TestSet;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nguyendoanh on 5/3/2018.
 */

public class ResultActivity extends AppCompatActivity {
    PieChart pieChart;
    String indexPart, indexTestSet;
    ArrayList<String> selectedAnswers;
    private int incorrect,sum,result;
    private int correct_answer;
    private int score;
    Button btnreview;
    TestSet testSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        // Get intent tu QuestionGroup
        correct_answer = intent.getIntExtra("count_correct_answer",1);
        sum = intent.getIntExtra("count_question",1);
        testSet = (TestSet) intent.getSerializableExtra("testSet");
        indexPart = testSet.getIndexPart();
        indexTestSet = testSet.getIndexTestSet();
        selectedAnswers = intent.getStringArrayListExtra("selectedAnswers");
        final DBManager dbManager = new DBManager(this, "toeic81");
        dbManager.InsertScore(indexPart,indexTestSet,String.valueOf(correct_answer));
        setTitle("Part " + indexPart + "- TestSet " + indexTestSet);
//        final String testname=intent.getStringExtra("TestName");
        incorrect = sum - correct_answer;
        result = (correct_answer/sum) * 100 ;
        Anhxa();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set Title name
        setTitle("RESULT");

        //--------------
        String ten = "" + String.valueOf(correct_answer) + " / " + String.valueOf(sum);
        pieChart = findViewById(R.id.pcResult);
        pieChart.getDescription().setEnabled(false);
        pieChart.getLegend().setEnabled(false);
        pieChart.setCenterText(ten);
        List<PieEntry> entries = new ArrayList<>();
        int[] color ={R.color.colorAccent,R.color.colorPrimary};
        entries.add(new PieEntry(correct_answer, "Correct"));
        entries.add(new PieEntry(incorrect, "Incorrect"));
        PieDataSet set = new PieDataSet(entries, "");
        int[] colors = {Color.RED, Color.BLUE};
        set.setColors(colors);

        PieData data = new PieData(set);
        pieChart.setData(data);
        btnreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ResultActivity.this,QuestionGroupSliderActivity.class);
                intent1.putExtra("testSet", (Serializable) testSet);
                intent1.putExtra("reviewmode", true);
                intent1.putStringArrayListExtra("selectedAnswers",selectedAnswers);
                startActivity(intent1);
            }
        });

        //pieChart.invalidate(); // refresh
        //----------------------
    }

    private void Anhxa() {
        btnreview = (Button) findViewById(R.id.btnreview);
//        tvsum = findViewById(R.id.idtvtsum);
//        tvcorrect_answer = findViewById(R.id.idtvcorrectanswer);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public void onBackPressed()
    {
        Intent intent1 = new Intent(ResultActivity.this,QuestionGroupSliderActivity.class);
        intent1.putExtra("testSet", (Serializable) testSet);
        intent1.putExtra("reviewmode", true);
        intent1.putStringArrayListExtra("selectedAnswers",selectedAnswers);
        startActivity(intent1);
    }
}
