package com.example.chanh.toeic.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.chanh.toeic.R;
import com.example.chanh.toeic.model.ListPart5;

import java.util.ArrayList;

public class ListPart5Adapter extends ArrayAdapter<ListPart5> {
    public ListPart5Adapter(@NonNull Context context, ArrayList<ListPart5> exam) {
        super(context,0,exam);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listview_listpart5,parent,false);

        }

        TextView tvTHTieudeP5 = (TextView) convertView.findViewById(R.id.tvTHTieudeP5);
        ListPart5 list = getItem(position);
        if(list!=null){
            tvTHTieudeP5.setText(""+list.getName());

        }

        return convertView;
    }
}
