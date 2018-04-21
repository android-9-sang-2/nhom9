package com.pino.giuaki;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnAdd;
    public static Database database;
    ListView listView;
    ArrayList<MonAn> monAnArrayList;
    MonAnAdapter monAnAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        listView = (ListView) findViewById(R.id.listview);
        monAnArrayList = new ArrayList<>();
        monAnAdapter = new MonAnAdapter(this,R.layout.dong_monan,monAnArrayList);
        listView.setAdapter(monAnAdapter);


        //tao database
        database = new Database(this,"mydb.sqlite",null,1);
        database.QueryData("CREATE TABLE IF NOT EXISTS MonAn(ID INTEGER PRIMARY KEY AUTOINCREMENT,Ten VARCHAR(150),Gia VARCHAR(100),HinhAnh BLOB)");

        // get Data
        Cursor cursor = database.GetData("SELECT * FROM MonAn");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            monAnArrayList.add(new MonAn(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getBlob(3)
            ));
            cursor.moveToNext();
        }
//        layDanhSach();
        monAnAdapter.notifyDataSetChanged();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ThemMon.class));
            }
        });
    }
//    public List<MonAn> layDanhSach(){
//        // db.rawQuery()
//        List<MonAn> list = new ArrayList<MonAn>();
//        String sql = "select * from MonAn";
//        Cursor c = database.GetData(sql);
//        c.moveToFirst();
//        while (!c.isAfterLast()){ // khong phai cuoi cung
//            Log.e("Aaa" , "aaa");
//            MonAn item = new MonAn();
//            item.setID(c.getInt(0));
//            item.setTen(c.getString(1));
//            item.setGia(c.getString(2));
//            item.setHinh(c.getBlob(3));
//            list.add(item);
//            c.moveToNext();
//        }
//
//        return list;
//    }
}
