package com.pino.toeictest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class practiceListActivity extends AppCompatActivity {
    ListView lvPractice;
    ArrayList<practice> arrayPT;
    practice_Adapter prtAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_list);
        practiceList();
        prtAdapter = new practice_Adapter(this,R.layout.practice_row,arrayPT);
        lvPractice.setAdapter(prtAdapter);
    }
    private void practiceList(){
        lvPractice = (ListView) findViewById(R.id.lvTH);
        arrayPT = new ArrayList<>();
        arrayPT.add(new practice("PART 1 - TEST 1","High core: 8/10",R.drawable.part1));
        arrayPT.add(new practice("PART 1 - TEST 2","High core: 7/10",R.drawable.part1));
        arrayPT.add(new practice("PART 1 - TEST 3","High core: 8/10",R.drawable.part1));
        arrayPT.add(new practice("PART 1 - TEST 4","High core: 2/10",R.drawable.part1));
        arrayPT.add(new practice("PART 1 - TEST 5","High core: 6/10",R.drawable.part1));
        arrayPT.add(new practice("PART 1 - TEST 6","High core: 8/10",R.drawable.part1));
        arrayPT.add(new practice("PART 1 - TEST 7","High core: 8/10",R.drawable.part1));
        arrayPT.add(new practice("PART 1 - TEST 8","High core: 8/10",R.drawable.part1));
        arrayPT.add(new practice("PART 1 - TEST 9","High core: 8/10",R.drawable.part1));
        arrayPT.add(new practice("PART 1 - TEST 10","High core: 8/10",R.drawable.part1));
    }
}
