package com.pino.toeictest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Pino on 3/6/2018.
 */

public class gvMenuAdaptor extends BaseAdapter {
    private Context context;
    private  String[] tenIC;
    private int[] hinhIC;

    public gvMenuAdaptor(Context context, String[] tenIC, int[] hinhIC) {
        this.context = context;
        this.tenIC = tenIC;
        this.hinhIC = hinhIC;
    }

    @Override
    public int getCount() {
        return tenIC.length;
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
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.gvmenu_row,null);
        TextView tvmenu= (TextView) convertView.findViewById(R.id.tvmenu);

        ImageButton ibHome = (ImageButton) convertView.findViewById(R.id.ibHome);
        tvmenu.setText(tenIC[i]);
        ibHome.setImageResource(hinhIC[i]);
        return convertView;
    }
}
