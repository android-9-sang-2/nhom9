package com.example.chanh.toeic;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {
    PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        final String testname=intent.getStringExtra("testname");
        setTitle(testname);

        //--------------
        pieChart = findViewById(R.id.pcResult);
        pieChart.getDescription().setEnabled(false);
        pieChart.getLegend().setEnabled(false);
        pieChart.setCenterText("Something");
        List<PieEntry> entries = new ArrayList<>();
        int[] color ={R.color.colorAccent,R.color.colorPrimary};
        entries.add(new PieEntry(40, "Correct"));
        entries.add(new PieEntry(60, "Incorrect"));
        PieDataSet set = new PieDataSet(entries, "");
        int[] colors = {Color.RED, Color.BLUE};
        set.setColors(colors);

        PieData data = new PieData(set);
        pieChart.setData(data);

        //pieChart.invalidate(); // refresh
        //----------------------
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
