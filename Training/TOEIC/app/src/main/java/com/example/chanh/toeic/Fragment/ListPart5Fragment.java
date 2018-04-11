package com.example.chanh.toeic.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.chanh.toeic.Activity.ScreenSlideActivity;
import com.example.chanh.toeic.MainActivity;
import com.example.chanh.toeic.R;
import com.example.chanh.toeic.adapter.ListPart5Adapter;
import com.example.chanh.toeic.model.ListPart5;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListPart5Fragment extends Fragment {

    ListPart5Adapter part5Adapter;
    ListView listp5;
    ArrayList<ListPart5> part5ArrayList = new ArrayList<ListPart5>();
    public ListPart5Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // return ve layout da tao
//        (MainActivity) getActivity().getSupportActionBar().setTitle("Part 5 ");

        return inflater.inflate(R.layout.fragment_partlist, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listp5 = (ListView) getActivity().findViewById(R.id.lvpart5);
        part5ArrayList.add(new ListPart5("Test 01"));
        part5ArrayList.add(new ListPart5("Test 02"));
        part5ArrayList.add(new ListPart5("Test 03"));

        part5Adapter = new ListPart5Adapter(getActivity(),part5ArrayList);
        listp5.setAdapter(part5Adapter);
        listp5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), ScreenSlideActivity.class);
                intent.putExtra("num_practice",position+1);
                intent.putExtra("part","5");

                startActivity(intent);
            }
        });

    }
}
