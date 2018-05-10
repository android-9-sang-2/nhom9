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

import com.example.chanh.toeic09.R;
import com.example.chanh.toeic09.activity.MainActivity;
import com.example.chanh.toeic09.activity.TipsListActivity;
import com.example.chanh.toeic09.adapter.GridViewAdapterTip;


/**
 * A simple {@link Fragment} subclass.
 */
public class TipsFragment extends Fragment {

    GridView gvMain;
    GridViewAdapterTip gridViewAdapterTip;
    String[] ten = {
            "Tip Photographs", "Tip Questions and Response", "Tip Conversations", "Tip Short Talks",
            "Tip Incomplete Sentences", "Tip Text Completion", "Tip Reading"
    };
    int[] hinhanh = {
            R.drawable.photographs, R.drawable.questions, R.drawable.conversations, R.drawable.shorttalk,
            R.drawable.incomplete, R.drawable.textcompletion, R.drawable.read
    };
    int[] test_list = {

    };

    public TipsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Tips"); // set title
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tips, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // xu ly trong nay
        gvMain = (GridView) getActivity().findViewById(R.id.gvMain);
        gridViewAdapterTip = new GridViewAdapterTip(getContext(), ten, hinhanh);
        gvMain.setAdapter(gridViewAdapterTip);
        gvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //chuyen qua trang kia
                Intent intent = new Intent(getActivity(), TipsListActivity.class);
                intent.putExtra("TestName", ten[i]);
                intent.putExtra("indexPart",String.valueOf(i+1));
                intent.putExtra("Logo", hinhanh[i]);
                //Log.e("Testname",String.valueOf(i));
                startActivity(intent);
            }
        });
    }



}
