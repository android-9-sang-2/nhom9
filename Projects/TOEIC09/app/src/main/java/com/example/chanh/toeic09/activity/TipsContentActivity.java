package com.example.chanh.toeic09.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.chanh.toeic09.adapter.TipsContentAdapter;
import com.example.chanh.toeic09.model.TipSource;
import com.example.chanh.toeic09.model.Tips;
import com.example.chanh.toeic09.R;
import java.util.ArrayList;

public class TipsContentActivity extends AppCompatActivity {
    TipSource tipSource;
    ListView lvContentTip;
    ArrayList<Tips> list;
    String indexTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_content);
        Intent intent = getIntent();
        indexTip = intent.getStringExtra("indexTip");
        //Log.e("bbbbb",String.valueOf(indexId));
        tipSource = new TipSource(this);
        list = (ArrayList<Tips>) tipSource.layContentTip(indexTip);
        lvContentTip = findViewById(R.id.lvContentTip);
        TipsContentAdapter tipsContentAdapter = new TipsContentAdapter(this, (ArrayList<Tips>) list);
        lvContentTip.setAdapter(tipsContentAdapter);

    }

}
