package com.pino.toeictest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Pino on 3/7/2018.
 */

public class practice_Adapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<practice>  practiceList;

    public practice_Adapter(Context context, int layout, List<practice> practiceList) {
        this.context = context;
        this.layout = layout;
        this.practiceList = practiceList;
    }

    @Override
    public int getCount() { // tra ve so dong de hien thi
        return practiceList.size(); // tra ve het
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private class ViewHolder{ // giam viec anh xa cua view
        ImageView imgHinh;
        TextView txtTen,txtdiem;

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) { // tra ve moi dong item
        ViewHolder holder;
        if(view == null){ // lan dau tien chay chua co anh xa
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); // lay context gan layout vao
            view = inflater.inflate(layout,null);
            // anh xa view
            holder = new ViewHolder();

            holder.txtTen = (TextView) view.findViewById(R.id.txtTHTieude);
            holder.txtdiem = (TextView) view.findViewById(R.id.txtTHdiem);
            holder.imgHinh = (ImageView) view.findViewById(R.id.imgTHTrai);
            view.setTag(holder); // chay anh xa

//            TextView txtTen = (TextView) view.findViewById(R.id.txtTHTieude);
//            TextView txtDiem = (TextView) view.findViewById(R.id.txtTHdiem);
//            ImageView imgHinh = (ImageView) view.findViewById(R.id.imgTHTrai);
        } else { // run lai , lay anh xa, ko lay lai
            holder = (ViewHolder) view.getTag();
        }

        // gan gia tri
        practice pt = practiceList.get(i); // moi phan tu trong mang
        holder.txtTen.setText(pt.getTitle());
        holder.txtdiem.setText(pt.getDiem());
        holder.imgHinh.setImageResource(pt.getHinh());


        return view; // tra ve view
    }
}
