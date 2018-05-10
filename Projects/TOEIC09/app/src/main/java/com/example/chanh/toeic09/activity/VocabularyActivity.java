package com.example.chanh.toeic09.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.chanh.toeic09.R;
import com.example.chanh.toeic09.adapter.VocabularyAdapter;
import com.example.chanh.toeic09.data.DBManager;
import com.example.chanh.toeic09.model.Vocabulary;

import java.util.ArrayList;


public class VocabularyActivity extends AppCompatActivity {
    ListView listView;
    VocabularyAdapter adapter;
//    DatabaseReference mdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);
        listView = (ListView) findViewById(R.id.ListViewVocabulary);
//        mdata = FirebaseDatabase.getInstance().getReference();
        final ArrayList<Vocabulary> arrVoc;
//        mdata.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot ds: dataSnapshot.child("vocabulary").getChildren()){
//                    Vocabulary vo = ds.getValue(Vocabulary.class);
//                    arrVoc.add(vo);
//                }
//                adapter = new VocabularyAdapter(getApplicationContext(), 0, arrVoc);
//                listView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
        final DBManager dbManager = new DBManager(this, "toeic81");
        arrVoc = dbManager.getVocArray();
        adapter = new VocabularyAdapter(getApplicationContext(), 0, arrVoc);
        listView.setAdapter(adapter);
//        getData();

//        adapter = new VocabularyAdapter(this, 0, arrVoc);
//        listView.setAdapter(adapter);
    }
//    public void getData(){
//        DataVocabulary.getData().arrVoc = new ArrayList<>();
//    }
}
