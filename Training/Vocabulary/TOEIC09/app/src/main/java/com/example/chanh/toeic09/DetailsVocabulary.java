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


public class DetailsVocabulary extends AppCompatActivity {

    ListView listView;
    DetailsVocabularyAdapter adapter;
    DatabaseReference mdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);
        listView = (ListView) findViewById(R.id.ListViewVocabulary);
        mdata = FirebaseDatabase.getInstance().getReference();
        final ArrayList<Vocabulary> arrVoc = new ArrayList<>();
        mdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.child("vocabulary").getChildren()){
                    Vocabulary vo = ds.getValue(Vocabulary.class);
                    arrVoc.add(vo);
                }
                adapter = new DetailsVocabularyAdapter(getApplicationContext(), 0, arrVoc);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        getData();

//        adapter = new VocabularyAdapter(this, 0, arrVoc);
//        listView.setAdapter(adapter);
    }
    public void getData(){
        DataVocabulary.getData().arrVoc = new ArrayList<>();
    }
}