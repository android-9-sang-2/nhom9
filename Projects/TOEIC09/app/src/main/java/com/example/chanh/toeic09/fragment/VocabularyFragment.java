package com.example.chanh.toeic09.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chanh.toeic09.R;
import com.example.chanh.toeic09.activity.MainActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class VocabularyFragment extends Fragment {


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

}
