package com.pino.toeictest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity  {
    GridView gvmenu;
    private gvMenuAdaptor gvMenuAdaptor;
    String[] ten = {
        "PART 1","PART 2","PART 3","PART 4","PART 5","PART 6","PART 7"
    };
    int[] hinh = {
            R.drawable.part1, R.drawable.part2, R.drawable.part3, R.drawable.part4, R.drawable.part5, R.drawable.part6, R.drawable.part7
    };

//    ListView lvPractice;
//    ArrayList<practice> arrayPT;
//    practice_Adapter prtAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gvmenu = (GridView) findViewById(R.id.gvmenu);
        gvMenuAdaptor = new gvMenuAdaptor(this,ten,hinh);
        gvmenu.setAdapter(gvMenuAdaptor);


//        practiceList();
//        prtAdapter = new practice_Adapter(this,R.layout.practice_row,arrayPT);
//        lvPractice.setAdapter(prtAdapter);

    }





//    private void practiceList(){
//        lvPractice = (ListView) findViewById(R.id.lvTH);
//        arrayPT = new ArrayList<>();
//        arrayPT.add(new practice("PART 1 - TEST 1","High core: 8/10",R.drawable.part1));
//        arrayPT.add(new practice("PART 1 - TEST 2","High core: 7/10",R.drawable.part1));
//        arrayPT.add(new practice("PART 1 - TEST 3","High core: 8/10",R.drawable.part1));
//        arrayPT.add(new practice("PART 1 - TEST 4","High core: 2/10",R.drawable.part1));
//        arrayPT.add(new practice("PART 1 - TEST 5","High core: 6/10",R.drawable.part1));
//        arrayPT.add(new practice("PART 1 - TEST 6","High core: 8/10",R.drawable.part1));
//        arrayPT.add(new practice("PART 1 - TEST 7","High core: 8/10",R.drawable.part1));
//        arrayPT.add(new practice("PART 1 - TEST 8","High core: 8/10",R.drawable.part1));
//        arrayPT.add(new practice("PART 1 - TEST 9","High core: 8/10",R.drawable.part1));
//        arrayPT.add(new practice("PART 1 - TEST 10","High core: 8/10",R.drawable.part1));
//    }
}
