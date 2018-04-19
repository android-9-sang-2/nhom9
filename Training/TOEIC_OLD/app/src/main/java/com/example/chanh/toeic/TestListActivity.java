package com.example.chanh.toeic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class TestListActivity extends AppCompatActivity {

    Button btn;
    ListView lvTest;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //back arrow
        //nhan du lieu
        Intent intent = getIntent();
        int logo=intent.getIntExtra("Logo",0);
        final String testname=intent.getStringExtra("TestName");
        //thiet lap truyen noi dung cho listview

        count = 15;
        lvTest = findViewById(R.id.lvTest);
        ListViewAdapter listViewAdapter = new ListViewAdapter(this, testname, logo, count);
        lvTest.setAdapter(listViewAdapter);
        //set the title
        setTitle(testname);
        lvTest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TestListActivity.this,TestPageActivity.class);
                intent.putExtra("testname",testname + " " + (i+1));
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
