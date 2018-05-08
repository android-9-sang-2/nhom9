package com.example.chanh.toeic09.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.example.chanh.toeic09.adapter.TipsAdapter;
import com.example.chanh.toeic09.model.TipSource;
import com.example.chanh.toeic09.model.Tips;

import java.util.ArrayList;
import java.util.List;
import com.example.chanh.toeic09.R;
public class TipsListActivity extends AppCompatActivity {
    TipSource tipSource;
    ListView lvTest;
    List<Tips> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_tips_list); // cái chứa listview
        Intent intent = getIntent();
        String indexPart = intent.getStringExtra("indexPart");
        tipSource = new TipSource(this);
        list =  tipSource.layDanhSachTip(indexPart);
        lvTest = findViewById(R.id.lvTest);
        TipsAdapter tipsAdapter = new TipsAdapter(this, (ArrayList<Tips>) list);
        lvTest.setAdapter(tipsAdapter);
        lvTest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tips listItem = (Tips) lvTest.getItemAtPosition(position);
                Intent intent = new Intent(TipsListActivity.this, TipsContentActivity.class);
                intent.putExtra("indexTip",listItem.getIndexTip());
                startActivity(intent);
            }
        });
    }
}


