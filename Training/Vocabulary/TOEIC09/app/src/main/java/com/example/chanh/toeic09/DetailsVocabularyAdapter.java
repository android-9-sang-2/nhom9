package com.example.chanh.toeic09;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailsVocabularyAdapter extends ArrayAdapter<Vocabulary> {

    ArrayList<Vocabulary> arr;
    Context ct;

    public DetailsVocabularyAdapter(Context context, int resource, ArrayList<Vocabulary> o){
        super(context, resource, o);
        this.ct = context;
        this.arr = o;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder v;
        if(row == null){
            LayoutInflater inflater = (LayoutInflater)ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.activity_details_vocabulary, null);
            v = new ViewHolder(row);
            row.setTag(v);
        }
        else{
            v = (ViewHolder)row.getTag();
        }
        if(arr.size() > 0){
            System.out.println(arr.size());
            v.setView(arr.get(position));
        }
        return row;
    }

    class ViewHolder{
        TextView word, mean, sentences, meansentences;

        public ViewHolder(View v){
            word = (TextView) v.findViewById(R.id.textViewWord);
            mean = (TextView) v.findViewById(R.id.textViewMean);
            sentences = (TextView) v.findViewById(R.id.textViewSentences);
            meansentences = (TextView) v.findViewById(R.id.textViewMeanSentences);
        }

        public void setView(Vocabulary vo){
            word.setText(vo.getWord());
            mean.setText(vo.getMean());
            sentences.setText(vo.getSentences());
            meansentences.setText(vo.getMeanSentences());
        }
    }
    public interface OnClickedListenner{
        void OnSelectedItem(int position);
    }
}