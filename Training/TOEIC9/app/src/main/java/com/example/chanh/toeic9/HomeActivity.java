package com.example.chanh.toeic9;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import android.util.Log;

import com.example.chanh.toeic9.model.GridViewAdapter;
import com.example.chanh.toeic9.model.Part;
import com.example.chanh.toeic9.data.DBManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;

public class HomeActivity extends AppCompatActivity {
    GridView gvHome;
    String[] partNames = new String[7];
    String[] icons = new String[7];
    Part[] parts = new Part[7];
    DatabaseReference mData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d("oncreate", "oncreate");
        final DBManager dbManager = new DBManager(this, "toeihc81");
//        dbManager.getWritableDatabase();
//        dbManager.getPart();
        //lan dau tien goi getWritableDatabase se thuc hien onCreate ben DBManager.
        SQLiteDatabase db = dbManager.getWritableDatabase();
//        GridView_Works();

        parts = dbManager.getPartArray();
        if(parts[0]!=null){
            Log.d("try2", parts[0].getName());
            gvHome = findViewById(R.id.gvHome);
            GridViewAdapter gridViewAdapter = new GridViewAdapter(this, parts);
            gvHome.setAdapter(gridViewAdapter);
            gvHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(HomeActivity.this,TestSetActivity.class);
                    intent.putExtra("indexPart", String.valueOf(position+1));
                    startActivity(intent);
                }
            });
        }
        else{
            Intent i = getBaseContext().getPackageManager()
                    .getLaunchIntentForPackage( getBaseContext().getPackageName() );
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        }

    }

    private static boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }
}
