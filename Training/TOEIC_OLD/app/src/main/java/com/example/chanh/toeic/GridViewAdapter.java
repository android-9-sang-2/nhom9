package com.example.chanh.toeic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by chanh on 007, 07, 03, 2018.
 */

public class GridViewAdapter extends BaseAdapter{
    private Context context;
    private String[] tenLogo;
    private int[] hinhLogo;

    public GridViewAdapter(Context context, String[] tenLogo, int[] hinhLogo) {
        this.context = context;
        this.tenLogo = tenLogo;
        this.hinhLogo = hinhLogo;
    }

    @Override
    public int getCount() {
        return tenLogo.length;
    }

    @Override
    public Object getItem(int i) {
        return tenLogo[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.gridview_row,null);
        TextView textView =(TextView) view.findViewById(R.id.textView);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        textView.setText(tenLogo[i]);
        imageView.setImageResource(hinhLogo[i]);
        return view;
    }
}
