package com.pino.toeictest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class testingListActivity extends AppCompatActivity {

    ListView lvTestList;
    ArrayList<testing> arrayTestList;
    testing_Adapter testAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_list);
        testingList();
        testAdapter = new testing_Adapter(this,R.layout.testing_row,arrayTestList);
        lvTestList.setAdapter(testAdapter);
    }

    private void testingList() {
        lvTestList = findViewById(R.id.lvTesting);
        arrayTestList = new ArrayList<>();
        arrayTestList.add(new testing("TEST 01","750/990","375","375"));
        arrayTestList.add(new testing("TEST 02","760/990","375","385"));
        arrayTestList.add(new testing("TEST 03","770/990","375","395"));
        arrayTestList.add(new testing("TEST 04","750/990","375","375"));
        arrayTestList.add(new testing("TEST 05","750/990","375","375"));
        arrayTestList.add(new testing("TEST 06","750/990","375","375"));
        arrayTestList.add(new testing("TEST 07","750/990","375","375"));
    }


}
