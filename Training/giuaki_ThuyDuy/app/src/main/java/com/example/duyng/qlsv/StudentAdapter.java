package com.example.duyng.qlsv;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class StudentAdapter extends CursorAdapter {
    public StudentAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvName, tvAge, tvEmail;
        tvName=(TextView)view.findViewById(R.id.tvName);
        tvAge=(TextView)view.findViewById(R.id.tvAge);
        tvEmail=(TextView)view.findViewById(R.id.tvEmail);

        tvName.setText(String.valueOf(cursor.getPosition()+1)+ ". "+cursor.getString(1));
        tvAge.setText(String.valueOf(cursor.getInt(3))+" ages");
        tvEmail.setText(cursor.getString(2));

    }
}
