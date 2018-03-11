package com.pino.toeictest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Pino on 3/8/2018.
 */

public class testing_Adapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<testing> testingList;

    public testing_Adapter(Context context,int layout,List<testing> testingList){
        this.context=context;
        this.layout=layout;
        this.testingList= testingList;
    }
    @Override
    public int getCount() {
        return testingList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    public class ViewHolder{
        TextView txtTestName,txtTotal,txtTScore,txtListening,txtReading,txtLScore,txtRScore;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(layout,null);
            holder = new ViewHolder();
            holder.txtTestName = view.findViewById(R.id.txtTestName);
//            holder.txtListening = view.findViewById(R.id.txtListening);
//            holder.txtReading = view.findViewById(R.id.txtReading);
            holder.txtLScore = view.findViewById(R.id.txtLScore);
            holder.txtRScore = view.findViewById(R.id.txtRScore);
//            holder.txtTotal = view.findViewById(R.id.txtTotal);
            holder.txtTScore = view.findViewById(R.id.txtTestScore);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        testing test = testingList.get(i);
        holder.txtTScore.setText(test.getTongdiem());
        holder.txtLScore.setText(test.getListening());
//        holder.txtRScore.setText(test.getReading());
        holder.txtTestName.setText(test.getTestname());
        return view;
    }
}
