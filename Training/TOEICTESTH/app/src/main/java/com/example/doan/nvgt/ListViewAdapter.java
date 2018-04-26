package com.example.doan.nvgt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class ListViewAdapter  extends BaseAdapter {
    private Context context;
    private String NameTest;
    private int Logo;
    private int count;

    public ListViewAdapter(Context context, String tenLogo, int hinhLogo, int count) {
        this.context = context;
        this.NameTest = tenLogo;
        this.Logo = hinhLogo;
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
        tvTestName.setText(NameTest + " " + (i+1));
        ivTestLogo.setImageResource(Logo);
        return view;
    }
}
