package com.example.chanh.toeic09;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class VocabularyActivity extends AppCompatActivity {

    ListView listView;
    VocabularyAdapter adapter;
    DatabaseReference mdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);


        listView = (ListView) findViewById(R.id.ListViewVocabulary);
        mdata = FirebaseDatabase.getInstance().getReference();

        getData();

        adapter = new VocabularyAdapter(this, 0, DataVocabulary.getData().arrVoc);
        listView.setAdapter(adapter);
    }
    public void getData(){
        DataVocabulary.getData().arrVoc = new ArrayList<>();
    }
}
