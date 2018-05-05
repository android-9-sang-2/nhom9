package com.example.chanh.toeic09.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chanh.toeic09.R;

import java.io.File;

/**
 * Created by chanh on 007, 07, 03, 2018.
 */

public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private Part[] parts;    //mang chua 7 phan
//    private String[] icons;     //mang chua path den 7 icon dai dien tuong ung

    public GridViewAdapter(Context context, Part[] parts) {
        this.context = context;
        this.parts = parts;
//        this.icons = icons;
    }

    @Override
    public int getCount() {
        return parts.length;
    }

    @Override
    public Object getItem(int i) {
        return parts[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.activity_home_gridview_row,null);
        TextView textView =(TextView) view.findViewById(R.id.textView);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        textView.setText(parts[i].getName());
        File myImageFile = new File(parts[i].getIcon());
        imageView.setImageDrawable(Drawable.createFromPath(myImageFile.toString()));
        return view;
    }

}
