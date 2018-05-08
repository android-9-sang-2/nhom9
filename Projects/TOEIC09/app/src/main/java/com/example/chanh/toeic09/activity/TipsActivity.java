package com.example.chanh.toeic09.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.chanh.toeic09.R;
import com.example.chanh.toeic09.adapter.GridViewAdapter;
import com.example.chanh.toeic09.adapter.GridViewAdapterTip;


public class TipsActivity extends Activity {

    GridView gvMain;
    GridViewAdapterTip gridViewAdapterTip;
    String[] ten = {
            "Tip Photographs", "Tip Questions and Response", "Tip Conversations", "Tip Short Talks",
            "Tip Incomplete Sentences", "Tip Text Completion", "Tip Reading"
    };
    int[] hinhanh = {
            R.drawable.photographs, R.drawable.questions, R.drawable.conversations, R.drawable.shorttalk,
            R.drawable.incomplete, R.drawable.textcompletion, R.drawable.read
    };
    int[] test_list = {

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_tip);
        gvMain = (GridView) findViewById(R.id.gvMain);
        gridViewAdapterTip = new GridViewAdapterTip(this, ten, hinhanh);
        gvMain.setAdapter(gridViewAdapterTip);
        gvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //chuyen qua trang kia
                Intent intent = new Intent(TipsActivity.this, TipsListActivity.class);
                intent.putExtra("TestName", ten[i]);
                intent.putExtra("indexPart",String.valueOf(i+1));
                intent.putExtra("Logo", hinhanh[i]);
                //Log.e("Testname",String.valueOf(i));
                startActivity(intent);
            }
        });
    }
}
