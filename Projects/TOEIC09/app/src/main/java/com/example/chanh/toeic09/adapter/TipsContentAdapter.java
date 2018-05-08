package com.example.chanh.toeic09.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.chanh.toeic09.R;
import com.example.chanh.toeic09.model.Tips;

import java.util.ArrayList;

public class TipsContentAdapter extends ArrayAdapter<Tips> {

        Context context;

        public TipsContentAdapter(Context context, ArrayList<Tips> list) {
            super(context,0,list);
            this.context = context;
        }
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.content_tips_content,null);
            TextView tvtttip = (TextView) view.findViewById(R.id.tvttTip);
            TextView tvContentTip = (TextView) view.findViewById(R.id.tvContentTip);
            Tips tips = (Tips) getItem(i);
            tvtttip.setText(tips.getTitleTip());
            tvContentTip.setText(tips.getContentTip());
            // ok key
            return view;
        }
    }

