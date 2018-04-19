package com.example.chanh.toeic.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.chanh.toeic.model.ListPart5;
import com.example.chanh.toeic.model.Questions;

import java.util.ArrayList;
import java.util.List;

public class SQLDBSource {
    SQLiteDatabase db;
    DBManager manager;

    public SQLDBSource(Context context){
        manager = new DBManager(context);
        manager.createDatabase();
        db = manager.openDatabase();// mo csdl


    }
    public List<Questions> layDanhSachCauHoi(int num_practice,String part){
        // db.rawQuery()
        List<Questions> list = new ArrayList<Questions>();
        String sql = "select * from "+DBManager.TB_CH+" where num_practic='"+num_practice+"' and part='"+part+"'";
        Cursor c = db.rawQuery(sql,null);

        //        String[] column = {"id","cauHoi","dapAn","ans_A","ans_B","ans_C","ans_D","part","traLoi"};
//        Cursor c = db.query(DBManager.TB_CH,column,null,null,null,null,null);
        c.moveToFirst();
        while (!c.isAfterLast()){ // khong phai cuoi cung
            Questions item = new Questions();
            item.setId(c.getInt(0));
            item.setCauHoi(c.getString(1));
            item.setDapAn(c.getString(2));
            item.setAns_A(c.getString(3));
            item.setAns_B(c.getString(4));
            item.setAns_C(c.getString(5));
            item.setAns_D(c.getString(6));
            item.setTraloi("");
            item.setHuongDan(c.getString(11));

            list.add(item);
            c.moveToNext();
        }

        return list;
    }

    public ArrayList<ListPart5> layDanhSachList(int part){
        List<ListPart5> list = new ArrayList<ListPart5>();
        String sql = "select num_practic from "+DBManager.TB_CH+" where part = '"+part+"' group by num_practic";
        Cursor c = db.rawQuery(sql,null);
        c.moveToFirst();
        while (!c.isAfterLast()){ // khong phai cuoi cung
            ListPart5 item = new ListPart5();
            item.setName(c.getString(0));
            list.add(item);
            c.moveToNext();
        }

        return (ArrayList<ListPart5>) list;
    }


}
