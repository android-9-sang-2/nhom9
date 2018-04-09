package com.example.chanh.toeic.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
    public List<Questions> layDanhSachCauHoi(){
        // db.rawQuery()
        List<Questions> list = new ArrayList<Questions>();
        String sql = "select * from " + DBManager.TB_CH;
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
//    public  int upDateTraLoi(int id,String traLoi){
//        ContentValues values = new ContentValues();
//        values.put("traLoi",traLoi);
//        return db.update(DBManager.TB_CH,values,"id= ?", new String[]{String.valueOf(id)});
//    }

}
