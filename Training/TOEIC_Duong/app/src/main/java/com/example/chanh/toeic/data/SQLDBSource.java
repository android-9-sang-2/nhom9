package com.example.chanh.toeic.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.chanh.toeic.model.QuestionGroup;


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

    public List<QuestionGroup> laydanhSachQuestionGroup(int indexPart,int indexTestSet){
        List<QuestionGroup> list = new ArrayList<>();
        String sql = "select * from "+DBManager.TB_QuestionGroup+" where indexPart='"+indexPart+"' and indexTestSet='"+indexTestSet+"'";
        Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            QuestionGroup item = new QuestionGroup();
            item.setIndexPart(cursor.getInt(0));
            item.setIndexTestSet(cursor.getInt(1));
            item.setIndexQuestionGroup(cursor.getInt(2));
            item.setContent(cursor.getString(3));
            list.add(item);
            cursor.moveToNext();
        }
        return list;

    }





}
