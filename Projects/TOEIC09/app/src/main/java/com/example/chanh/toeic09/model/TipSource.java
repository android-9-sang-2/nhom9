package com.example.chanh.toeic09.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.chanh.toeic09.data.DBManager;

import java.util.ArrayList;
import java.util.List;

public class TipSource {

//    SQLiteDatabase db;
//    DBHelper dbHelper;
   // DataBaseHelper dbHelper;
    DBManager dbManager;

    public TipSource(Context context){
//        dbHelper = new DBHelper(context);
//        dbHelper.createDatabase();
//        db = dbHelper.openDatabase();// mo csdl
        this.dbManager = new DBManager(context, "toeic81");
//        dbManager.getWritableDatabase();
//        dbManager.getPart();
        //lan dau tien goi getWritableDatabase se thuc hien onCreate ben DBManager.
//        SQLiteDatabase db = dbManager.getWritableDatabase();
//        GridView_Works();

//        parts = dbManager.getPartArray();


    }

    public List<Tips> layDanhSachTip(String indexPart){

        return dbManager.layDanhSachTip(indexPart);
    }

    public List<Tips> layContentTip(String indexTip){

       return dbManager.layContentTip(indexTip);
    }

    public Cursor getTipsList(String indexPart){
       return dbManager.getTipsList(indexPart);
    }

    public Tips fetchTipByID(String indexTip){
       return dbManager.fetchTipByID(indexTip);
    }




}
