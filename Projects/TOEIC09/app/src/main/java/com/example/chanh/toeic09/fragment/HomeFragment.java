package com.example.chanh.toeic09.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.chanh.toeic09.R;
import com.example.chanh.toeic09.activity.MainActivity;
import com.example.chanh.toeic09.activity.TestSetActivity;
import com.example.chanh.toeic09.adapter.GridViewAdapter;
import com.example.chanh.toeic09.data.DBManager;
import com.example.chanh.toeic09.model.Part;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    GridView gvHome;
    String[] partNames = new String[7];
    String[] icons = new String[7];
    Part[] parts = new Part[7];
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Home"); // thay doi title action bar

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // xu ly trong nay
//        aaa = (Button) getActivity().findViewById(R.id.aaaa);
//        aaa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(), "NOI DUNG O DAY", Toast.LENGTH_SHORT).show();
//            }
//        });
        final DBManager dbManager = new DBManager(getContext(), "toeic81");
        parts = dbManager.getPartArray();
        Log.d("try2", parts[0].getName());
        gvHome = getActivity().findViewById(R.id.gvHome);
        GridViewAdapter gridViewAdapter = new GridViewAdapter(getContext(), parts);
        gvHome.setAdapter(gridViewAdapter);
        gvHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),TestSetActivity.class);
                intent.putExtra("indexPart", String.valueOf(position+1));
                startActivity(intent);
            }
        });

    }
}
