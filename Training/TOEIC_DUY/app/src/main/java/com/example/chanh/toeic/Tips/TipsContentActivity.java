package com.example.chanh.toeic.Tips;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.chanh.toeic.R;
import com.example.chanh.toeic.Tips.TipSource;
import com.example.chanh.toeic.Tips.Tips;
import com.example.chanh.toeic.Tips.TipsAdapter;
import com.example.chanh.toeic.Tips.TipsContentAdapter;

import java.util.ArrayList;

public class TipsContentActivity extends AppCompatActivity {
    TipSource tipSource;
    ListView lvContentTip;
    ArrayList<Tips> list;
    int indexId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_content);
        Intent intent = getIntent();
        indexId = intent.getIntExtra("indexId",0);
        //Log.e("bbbbb",String.valueOf(indexId));
        tipSource = new TipSource(this);
        list = (ArrayList<Tips>) tipSource.layContentTip(indexId);
        lvContentTip = findViewById(R.id.lvContentTip);
        TipsContentAdapter tipsContentAdapter = new TipsContentAdapter(this, (ArrayList<Tips>) list);
        lvContentTip.setAdapter(tipsContentAdapter);

    }

}
