package com.example.chanh.toeic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class SimulationTestActivity extends AppCompatActivity {
    ListView lvSimTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation_test);
        lvSimTest = findViewById(R.id.lvSimTest);
        ListViewSimAdapter listViewAdapter = new ListViewSimAdapter(this, 15);
        lvSimTest.setAdapter(listViewAdapter);
        //set the title
        setTitle("Simulation Tests");
        lvSimTest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SimulationTestActivity.this,TestPageActivity.class);
                //intent.putExtra("testname",testname + " " + (i+1));
                startActivity(intent);
            }
        });
    }
}
