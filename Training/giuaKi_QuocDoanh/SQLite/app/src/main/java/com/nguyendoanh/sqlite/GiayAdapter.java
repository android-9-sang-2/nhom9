package com.nguyendoanh.sqlite;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nguyendoanh on 4/17/2018.
 */

public class GiayAdapter extends BaseAdapter {
    private Activity context;
    private int layout;
    private List<Giay> giayList;
    Button btnXoa;
    Activity contextUpdate;
    //public GiayAdapter(Activity contextUpdate, ArrayList<Giay> list){

//    }




    public GiayAdapter(Activity context, int layout, List<Giay> giayList) {
        this.context = context;
        this.layout = layout;
        this.giayList = giayList;
    }

    @Override
    public int getCount() {
        return giayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView txtTen,txtMota;
        ImageView imgHinh;
        ImageButton imgedit,imgdelete;

    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if(view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtTen = (TextView) view.findViewById(R.id.textviewTenCustom);
            holder.txtMota = (TextView) view.findViewById(R.id.textviewMoTaCustom);
            holder.imgHinh = (ImageView) view.findViewById(R.id.imageHinhCustom);
            holder.imgedit = (ImageButton) view.findViewById(R.id.imgEdit);
            holder.imgdelete = (ImageButton) view.findViewById(R.id.imgremove);
            view.setTag(holder);
        }else {
            holder = (ViewHolder)view.getTag();
        }
        final Giay giay = giayList.get(i);
        holder.txtTen.setText(giay.getTen());
        holder.txtMota.setText(giay.getMoTa());
        // Chuyen byte -> sang bitmap
        byte[] hinhAnh = giay.getHinh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        holder.imgHinh.setImageBitmap(bitmap);

        holder.imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,UpdateActivity.class);
                intent.putExtra("ID",giay.getId());
                context.startActivity(intent);
            }
        });

        holder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa giày này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        delete(giay.getId());
                    }


                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }


        });
        return view;


    }
    private void delete(int id) {
        MainActivity.database.DEL_GIAY(id);
        Cursor cursor = MainActivity.database.GetData("SELECT * FROM Giay");
        giayList.clear();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            giayList.add(new Giay(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getBlob(3)));
            cursor.moveToNext();
        }
        notifyDataSetChanged();
    }
}
