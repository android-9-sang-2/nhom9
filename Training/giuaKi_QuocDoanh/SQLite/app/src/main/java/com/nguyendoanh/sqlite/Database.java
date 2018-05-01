package com.nguyendoanh.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

/**
 * Created by nguyendoanh on 4/16/2018.
 */

public class Database extends SQLiteOpenHelper {

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    // tạo ra câu sql để truy vấn ko trả về
    public  void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    // Dùng con trỏ để get data , return những gì nó lấy được
    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return  database.rawQuery(sql,null);
    }
    // Tạo ra
    public void INSERT_GIAY(String ten, String mota,  byte[] hinh){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO Giay VALUES(null,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,ten);// Bỏ vào chấm hỏi
        statement.bindString(2,mota);
        statement.bindBlob(3,hinh); // Nhận vào mảng byte
        statement.executeInsert(); // Thực hiện insert

    }
    public void UPDATE_GIAY(String ten,String mota,byte[] hinh,int id ){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Ten",ten);
        contentValues.put("Mota",mota);
        contentValues.put("HinhAnh",hinh);
        database.update("Giay",contentValues,"id=?",new String[]{id+ ""});
    }
    public void DEL_GIAY(int id){
        SQLiteDatabase database = getWritableDatabase();
        database.delete("Giay","id=?",new String[]{ id+""});
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
