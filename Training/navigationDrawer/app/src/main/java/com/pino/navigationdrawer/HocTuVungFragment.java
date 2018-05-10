package com.pino.navigationdrawer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class HocTuVungFragment extends Fragment {


    public HocTuVungFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Mẹo cần biết"); // thay doi title action bar
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hoc_tu_vung, container, false);
    }

}
