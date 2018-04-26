package com.example.chanh.toeic.Tips;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TipSource {

    SQLiteDatabase db;
    DBHelper dbHelper;
   // DataBaseHelper dbHelper;

    public TipSource(Context context){
        dbHelper = new DBHelper(context);
        dbHelper.createDatabase();
        db = dbHelper.openDatabase();// mo csdl


    }

    public List<Tips> layDanhSachTip(int indexPart){

        List<Tips> list = new ArrayList<Tips>();
        String sql = "select * from "+DBHelper.TB_Tips+" where indexPart='"+indexPart+"'";
        Cursor c = db.rawQuery(sql,null);
        c.moveToFirst();
        while (!c.isAfterLast()){ // khong phai cuoi cung
            Tips item = new Tips();
            item.setId(c.getInt(0));
            item.setIndexPart(c.getInt(1));
            item.setTitleTip(c.getString(3));
            item.setContentTip(c.getString(2));
            list.add(item);
            c.moveToNext();
        }

        return list;
    }

    public List<Tips> layContentTip(int indexTip){

        List<Tips> list = new ArrayList<Tips>();
        String sql = "select * from "+DBHelper.TB_Tips+" where indexTip='"+indexTip+"'";
        Cursor c = db.rawQuery(sql,null);
        c.moveToFirst();
        while (!c.isAfterLast()){ // khong phai cuoi cung
            Tips item = new Tips();
            item.setId(c.getInt(0));
            item.setIndexPart(c.getInt(1));
            item.setContentTip(c.getString(2));
            item.setTitleTip(c.getString(3));
            list.add(item);
            c.moveToNext();
        }

        return list;
    }

    public Cursor getTipsList(int indexPart){
        String sql = "select * from "+DBHelper.TB_Tips+" where indexPart='"+indexPart+"'";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        //lay từ trên xuống
        return cursor;
    }

    public Tips fetchTipByID(int indexTip){
        String sql = "select * from "+DBHelper.TB_Tips+" where indexTip='"+indexTip+"'";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return new Tips(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getString(3));
    }




}
