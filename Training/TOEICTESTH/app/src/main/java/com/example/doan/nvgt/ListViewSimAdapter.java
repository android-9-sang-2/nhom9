package com.example.doan.nvgt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by chanh on 010, 10, 03, 2018.
 */

public class ListViewSimAdapter extends BaseAdapter {
    private Context context;
    private int count;

    public ListViewSimAdapter(Context context, int count) {
        this.context = context;
        this.count = count;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.listview_row,null);
        TextView tvTestName =(TextView) view.findViewById(R.id.tvTestName);
        ImageView ivTestLogo = (ImageView) view.findViewById(R.id.ivTestLogo);
        tvTestName.setText("Test " + (i+1));
        return view;
    }
}
