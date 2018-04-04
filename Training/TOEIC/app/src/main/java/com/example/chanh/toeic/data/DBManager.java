package com.example.chanh.toeic.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.chanh.toeic.model.Questions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pino on 3/26/2018.
 */

public class DBManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="test.sqlite";
    public static final String TB_CH="question";

    private Context context;
    private SQLiteDatabase database;
    String duongDanDB="";

    private static int VERSION=1;
    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context=context;
        duongDanDB = context.getFilesDir().getParent() +"/databases/" +DATABASE_NAME;
                // getParen > nam thu muc com.....
        // data/data/com.example.chanh.toeic/databases
    }

    @Override
    public void onCreate(SQLiteDatabase db) { // khoi tao bang

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase openDatabase() {
        return SQLiteDatabase.openDatabase(duongDanDB,null,SQLiteDatabase.OPEN_READWRITE); // mo database

    }

    public void copyDatabase(){
        try {
            InputStream is = context.getAssets().open(DATABASE_NAME); // lay luong du lieu file, duong dan, assests
            OutputStream os = new FileOutputStream(duongDanDB); // noi copy vao
            byte[] buffer = new byte[1024]; // luu du lieu 1024 - don vi 1 KB
            int length = 0; // xem dung luong file
            while ((length = is.read(buffer)) >0 ){ // doc du lieu
                os.write(buffer,0,length); // ghi tu 0MB toi het length
            }
            os.flush(); // day du lieu qua
            os.close(); // dong du lieu
            is.close(); // dong luong
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void createDatabase(){
        boolean kt = kiemTraDB(); // kiem tra co chua
        if(kt){
            Log.d("Result","Da co database");

        } else {
            Log.d("Result","Chua co database, copy du lieu");
            this.getWritableDatabase(); // ko co se tao db nhung k co du lieu
            copyDatabase();

        }

    }

    public boolean kiemTraDB(){
        SQLiteDatabase checkDB = null;

        try {
            // neu co file thi k copy
            checkDB = SQLiteDatabase.openDatabase(duongDanDB,null,SQLiteDatabase.OPEN_READONLY);

        }catch (Exception e){
            // neu khac null
            e.printStackTrace();

        }
        if (checkDB!=null){ // mo duoc thi dong ket noi
            checkDB.close();
        }
        return checkDB !=null ? true : false ; // co file tra  ve true



    }
//    public void openDatabase(){
//        String dpPath=context.getDatabasePath(DATABASE_NAME).getPath();
//        if(database != null && database.isOpen()){
//            return;
//        }
//        database = SQLiteDatabase.openDatabase(dpPath,null,SQLiteDatabase.OPEN_READWRITE);
//
//    }
//    public void closeDatabase(){
//        if (database!=null) database.close();
//    }
//    public List<Part2CH> getListCauHoi(){
//        Part2CH part2CH = null;
//        List <Part2CH> part2CHList = new ArrayList<>();
//        openDatabase();
//        Cursor cursor = database.rawQuery("SELECT * FROM question",null);
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()){
//            part2CH = new Part2CH(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getInt(7),cursor.getString(8),cursor.getString(9),cursor.getString(10));
//            part2CHList.add(part2CH);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        closeDatabase();
//        return part2CHList;
//    }
}
