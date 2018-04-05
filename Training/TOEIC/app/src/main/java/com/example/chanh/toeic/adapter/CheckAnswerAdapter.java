package com.example.chanh.toeic.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.chanh.toeic.R;
import com.example.chanh.toeic.model.Questions;

import java.util.ArrayList;

public class CheckAnswerAdapter extends BaseAdapter {
    ArrayList lsData;
    LayoutInflater inflater;
    SharedPreferences saveDapAn;

    public CheckAnswerAdapter(ArrayList lsData, Context context) {
        this.lsData = lsData;
        saveDapAn = context.getSharedPreferences("luutruthongtin", Context.MODE_PRIVATE);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return lsData.size();
    }

    @Override
    public Object getItem(int position) {
        return lsData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Questions data = (Questions) getItem(position);
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_gridview_ketqua,null);
            holder.tvNum = (TextView) convertView.findViewById(R.id.tvNum);
            holder.tvTraloi = (TextView) convertView.findViewById(R.id.tvTraloi);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        int i = position + 1;

        holder.tvTraloi.setText(saveDapAn.getString(String.valueOf(i),""));
        holder.tvNum.setText(String.valueOf(i));
//        holder.tvNum.setText(i + "- ");
//        holder.tvTraloi.setText(data.getTraloi());
        Log.e( "getView: ",data.getTraloi() );

        return convertView;
    }

    private static  class ViewHolder{
        TextView tvNum,tvTraloi;
    }
}
