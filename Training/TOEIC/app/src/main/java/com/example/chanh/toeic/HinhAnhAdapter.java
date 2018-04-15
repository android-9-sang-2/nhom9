package com.example.chanh.toeic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class HinhAnhAdapter extends BaseAdapter{

    Context myContext;
    int myLayout;
    List<HinhAnh> arrayHinh;

    public HinhAnhAdapter(Context myContext, int myLayout, List<HinhAnh> arrayHinh) {
        this.myContext = myContext;
        this.myLayout = myLayout;
        this.arrayHinh = arrayHinh;
    }

    @Override
    public int getCount() {
        return arrayHinh.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayHinh.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{
        ImageView imgHinh;
        TextView txtTen;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = view;
        ViewHolder holder = new ViewHolder();

        if (rowView == null){
            rowView = inflater.inflate(myLayout, null);
            holder.txtTen = (TextView) rowView.findViewById(R.id.textViewTen);
            holder.imgHinh = (ImageView) rowView.findViewById(R.id.imageViewHinh);
            rowView.setTag(holder);
        }else {
            holder = (ViewHolder) rowView.getTag();
        }
        // Gán giá trị
        holder.txtTen.setText(arrayHinh.get(i).TenHinh);
        Picasso.with(myContext).load(arrayHinh.get(i).LinkHinh).into(holder.imgHinh);

        return rowView;
    }
}
