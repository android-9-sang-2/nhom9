package com.example.chanh.toeic9;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nguyendoanh on 5/3/2018.
 */

public class ResultActivity extends AppCompatActivity {
    PieChart pieChart;
    private int incorrect;
    private int result;
    private int sum;
    private int correct_answer;
    TextView tvsum,tvcorrect_answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        correct_answer = intent.getIntExtra("count_correct_answer",1);
        sum = intent.getIntExtra("count_question",1);
//        final String testname=intent.getStringExtra("TestName");
        incorrect = sum - correct_answer;
        result = (correct_answer/sum) * 100 ;
        Anhxa();
        // De set gia tri
        tvsum.setText(String.valueOf(sum));
        tvcorrect_answer.setText(String.valueOf(tvcorrect_answer));
        //Lam viec voi piechart
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        Intent intent = getIntent();
//        final String testname=intent.getStringExtra("testname");
//        setTitle(testname);

        //--------------
        pieChart = findViewById(R.id.pcResult);
        pieChart.getDescription().setEnabled(false);
        pieChart.getLegend().setEnabled(false);
        pieChart.setCenterText("Sum of Correct:");
        List<PieEntry> entries = new ArrayList<>();
        int[] color ={R.color.colorAccent,R.color.colorPrimary};
        entries.add(new PieEntry(correct_answer, "Correct"));
        entries.add(new PieEntry(incorrect, "Incorrect"));
        PieDataSet set = new PieDataSet(entries, "");
        int[] colors = {Color.RED, Color.BLUE};
        set.setColors(colors);

        PieData data = new PieData(set);
        pieChart.setData(data);

        //pieChart.invalidate(); // refresh
        //----------------------
    }

    private void Anhxa() {
        tvsum = findViewById(R.id.idtvtsum);
        tvcorrect_answer = findViewById(R.id.idtvcorrectanswer);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
