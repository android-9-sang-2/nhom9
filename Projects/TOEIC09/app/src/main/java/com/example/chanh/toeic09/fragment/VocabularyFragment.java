package com.example.chanh.toeic09.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.example.chanh.toeic09.R;
import com.example.chanh.toeic09.activity.MainActivity;
import com.example.chanh.toeic09.activity.TipsListActivity;
import com.example.chanh.toeic09.adapter.GridViewAdapterTip;
import com.example.chanh.toeic09.adapter.VocabularyAdapter;
import com.example.chanh.toeic09.data.DBManager;
import com.example.chanh.toeic09.model.Vocabulary;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class VocabularyFragment extends Fragment {
    ListView listView;
    VocabularyAdapter adapter;

    public VocabularyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Vocabulary"); // thay doi title action bar
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vocabulary, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // xu ly trong nay
        listView = (ListView) getActivity().findViewById(R.id.ListViewVocabulary);
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
        final DBManager dbManager = new DBManager(getContext(), "toeic81");
        arrVoc = dbManager.getVocArray();
        adapter = new VocabularyAdapter(getContext(), 0, arrVoc);
        listView.setAdapter(adapter);
    }


}
