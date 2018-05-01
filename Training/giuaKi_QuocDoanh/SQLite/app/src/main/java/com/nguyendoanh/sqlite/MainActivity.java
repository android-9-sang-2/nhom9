package com.nguyendoanh.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnThem;
    ListView lvGiay;
    ArrayList<Giay> arrayGiay;
    GiayAdapter adapter;
    final String DATABASE_NAME ="Giay.sqlite";
    // khai bao bien nay de class nay co the goi class khac
     public static Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnThem = (Button) findViewById(R.id.buttonThem);
        lvGiay = (ListView) findViewById(R.id.listviewGiay);
        arrayGiay = new ArrayList<>();
        adapter = new GiayAdapter(this,R.layout.dong_giay,arrayGiay);
        lvGiay.setAdapter(adapter);

        // Khởi tạo database
        database = new Database(this,"QuanLy.sqlite",null,1);
        // Tạo bảng
        database.QueryData("CREATE TABLE IF NOT EXISTS Giay(Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten VARCHAR(150),MoTa VARCHAR(250),HinhAnh BLOB)");
        //Get Data
        Cursor cursor = database.GetData("SELECT * FROM Giay");
        while (cursor.moveToNext()){
            arrayGiay.add(new Giay(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getBlob(3)));
        }
        adapter.notifyDataSetChanged();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ThemGiayActivity.class));
            }
        });

    }
}
