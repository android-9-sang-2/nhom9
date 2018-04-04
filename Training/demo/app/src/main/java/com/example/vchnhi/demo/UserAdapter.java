package com.example.vchnhi.demo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.vchnhi.demo.model.Person;

import java.util.ArrayList;


public class UserAdapter extends ArrayAdapter<Person> {
    private Context context;
    private int resource;
    private ArrayList<Person> objects;

    public UserAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Person> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource,parent,false);

        TextView tvHoTen;
        TextView tvDiaChi;

        tvHoTen = (TextView) convertView.findViewById(R.id.tvRowHoTen);
        tvDiaChi = (TextView) convertView.findViewById(R.id.tvRowDiaChi);

        Person u = objects.get(position);
        tvHoTen.setText(u.getHoTen());
        tvDiaChi.setText(u.getDiaChi());

        return convertView;
    }
}
