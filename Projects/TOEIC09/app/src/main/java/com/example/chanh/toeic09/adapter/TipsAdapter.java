package com.example.chanh.toeic09.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.chanh.toeic09.R;
import com.example.chanh.toeic09.model.Tips;

import java.util.ArrayList;

public class TipsAdapter extends ArrayAdapter<Tips> {
    Context context;
    int indexId;




    public TipsAdapter(Context context, ArrayList<Tips> list) {
        super(context,0,list);
        this.context = context;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.tips_row,null);
        LinearLayout tipview = (LinearLayout) view.findViewById(R.id.tipview);
        TextView tvMainTips = (TextView) view.findViewById(R.id.tvMainTips);
        TextView stt = (TextView) view.findViewById(R.id.tvidTip);
        TextView title = (TextView) view.findViewById(R.id.tvTitleTip);
        //int size = getCount();
        //while (size >0){}
        Tips tips = (Tips) getItem(i);
        //indexId = tips.getId();
        //Log.e("size",String.valueOf(size));
        stt.setText(String.valueOf(i+1));
        title.setText(tips.getTitleTip());
        //tvMainTips.setText("TOEIC TIPS FOR PART");
//        btnTip.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, TipsContentActivity.class);
//                intent.putExtra("indexId", indexId);
//                context.startActivity(intent);
//            }
//        });
        // ok key
        return view;
    }

}
