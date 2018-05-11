package com.example.chanh.toeic09.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.example.chanh.toeic09.model.Vocabulary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nguyendoanh on 5/10/2018.
 */

public class WordDetailAdapter extends ArrayAdapter<Vocabulary> {
    ArrayList<Vocabulary> arr;
    Context ct;

    public WordDetailAdapter(@NonNull Context context, int resource, @NonNull List<Vocabulary> objects, ArrayList<Vocabulary> arr, Context ct) {
        super(context, resource, objects);
        this.arr = arr;
        this.ct = ct;
    }


}
