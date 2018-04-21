package com.pino.giuaki;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class Database extends SQLiteOpenHelper {
    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    public void INSERT_MONAN(String ten,String gia,byte[] hinh){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO MonAn VALUES(null,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,ten) ; // bo vao cham hoi
        statement.bindString(2,gia);
        statement.bindBlob(3,hinh); // nhan vao mang byte
        statement.executeInsert(); // thuc hien insert
    }
    public void UPDATE_MONAN(String ten,String gia,byte[] hinh,int id){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Ten",ten);
        contentValues.put("Gia",gia);
        contentValues.put("HinhAnh",hinh);
        database.update("MonAn",contentValues,"id = ?",new String[]{id + ""});
    }
    public void DEL_MONAN(int id){
        SQLiteDatabase database = getWritableDatabase();
        database.delete("MonAn","Id = ?",new String[]{id+""});
    }
    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
