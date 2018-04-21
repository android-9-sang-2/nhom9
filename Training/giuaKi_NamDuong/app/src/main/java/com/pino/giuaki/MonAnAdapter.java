package com.pino.giuaki;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MonAnAdapter extends BaseAdapter {
    private Activity context;
    private int layout;
    private List<MonAn> monAnList;
    public MonAnAdapter(Activity context, int layout, List<MonAn> monAnList) {
        this.context = context;
        this.layout = layout;
        this.monAnList = monAnList;
    }

    @Override
    public int getCount() {
        return monAnList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }



    @Override
    public long getItemId(int position) {
        return 0;
    }

    private  class ViewHolder{
        TextView tvTenMon,tvGia;
        ImageView imMonAn,edit,delete;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder.tvGia = (TextView) convertView.findViewById(R.id.tvGia);
            holder.tvTenMon = (TextView) convertView.findViewById(R.id.tvTenMon);
            holder.imMonAn = (ImageView) convertView.findViewById(R.id.imMonAn);
            holder.edit = (ImageView) convertView.findViewById(R.id.edit);
            holder.delete = (ImageView) convertView.findViewById(R.id.delete);
            convertView.setTag(holder);
        } else{
            holder = (ViewHolder) convertView.getTag();

        }
        final MonAn monAn = monAnList.get(position);
        holder.tvTenMon.setText(monAn.getTen());
        holder.tvGia.setText(monAn.getGia()+ " k/phần");

        // chuyen byte[] -> bimap
        byte[] hinhAnh = monAn.getHinh();
        final Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        holder.imMonAn.setImageBitmap(bitmap);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,UpdateActivity.class);
                intent.putExtra("ID",monAn.getID());
                context.startActivity(intent);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(R.drawable.ic_del);
                builder.setTitle("Xác nhận xoá");
                builder.setMessage("Bạn có muốn xoá món ăn này không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete(monAn.getID());
                    }
                });
                builder.setNegativeButton("Không?", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }

        });

        return convertView;
    }

    private void delete(int id) {
        MainActivity.database.DEL_MONAN(id);
        Cursor cursor = MainActivity.database.GetData("SELECT * FROM MonAn");
        monAnList.clear();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            monAnList.add(new MonAn(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getBlob(3)
            ));
            cursor.moveToNext();

        }
        notifyDataSetChanged();
    }
}
