package com.example.chanh.toeic.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.chanh.toeic.model.Diem;

import java.util.ArrayList;
import java.util.List;

public class DiemDBSoure {
    SQLiteDatabase db;
    DBManager manager;

    public DiemDBSoure(Context context){
        manager = new DBManager(context);
        manager.createDatabase();
        db = manager.openDatabase();// mo csdl


    }
    public List<Diem> layDanhSachCauHoi(){
        // db.rawQuery()
        List<Diem> list = new ArrayList<Diem>();
        String sql = "select * from " + DBManager.TB_DIEM;
        Cursor c = db.rawQuery(sql,null);
        c.moveToFirst();
        while (!c.isAfterLast()){ // khong phai cuoi cung
            Diem item = new Diem();
            item.setId(c.getInt(0));
            item.setName(c.getString(1));
            item.setScore(c.getString(2));
            item.setType(c.getInt(3));
            list.add(item);
            c.moveToNext();
        }

        return list;
    }

    public void insertScrore(String name,String diem,int type){
        db = manager.getWritableDatabase();
        ContentValues values = new ContentValues(); // luu 1 doi tuong tam truoc khi luu
        values.put("Name",name);
        values.put("Diem",diem);
        values.put("Type",type);
        db.insert(DBManager.TB_DIEM,null,values);
        db.close();

    }
}
