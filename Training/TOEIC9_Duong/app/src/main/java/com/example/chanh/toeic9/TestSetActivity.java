package com.example.chanh.toeic9;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.chanh.toeic9.data.DBManager;
import com.example.chanh.toeic9.model.TestSet;
import com.example.chanh.toeic9.model.TestSetAdapter;

import java.io.Serializable;

public class TestSetActivity extends AppCompatActivity {

//    Button btn;
    ListView lvTestSet;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_set);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //back arrow
        //nhan du lieu
        Intent intent = getIntent();
        final String indexPart = intent.getStringExtra("indexPart");
//        final String testname=intent.getStringExtra("TestName");
        //thiet lap truyen noi dung cho listview

//        count = 15;

        lvTestSet = findViewById(R.id.lvTestSet);
        final TestSet[] testSets;
        final DBManager dbManager = new DBManager(this, "toeic81");
//        GridView_Works();

        testSets = dbManager.getTestSetArray(indexPart);
        TestSetAdapter testSetAdapter = new TestSetAdapter(this, testSets);
        lvTestSet.setAdapter(testSetAdapter);
        //set the title
//        setTitle(testname);
        lvTestSet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TestSet testSet = testSets[i];
                Intent intent = new Intent(TestSetActivity.this,QuestionGroupSliderActivity.class);
//                intent.putExtra("indexPart",indexPart);
//                intent.putExtra("indexTestSet",String.valueOf(i+1));
//                intent.putExtra("audio",indexPart);
                intent.putExtra("testSet", (Serializable) testSet);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

