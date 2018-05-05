package com.example.chanh.toeic9;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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
    private int incorrect,sum,result,correct_answer,indextestset,indexpart;
    TextView tvsum,tvcorrect_answer;
    Button btnreview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        // Get intent tu QuestionGroup
        correct_answer = intent.getIntExtra("count_correct_answer",1);
        sum = intent.getIntExtra("count_question",1);
        indexpart = intent.getIntExtra("index__part",1);
        indextestset = intent.getIntExtra("index_test",1);
        setTitle("Part " + indexpart + "- TestSet " + indextestset);
//        final String testname=intent.getStringExtra("TestName");
        incorrect = sum - correct_answer;
        result = (correct_answer/sum) * 100 ;
        Anhxa();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final String testname=intent.getStringExtra("testname");
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
                Intent intent1 = new Intent(ResultActivity.this,ReviewActivity.class);
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
}
