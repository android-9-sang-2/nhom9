package com.example.doan.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageButton;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    GridView gridview;

    public static String[] osNameList = {
            "Question and Response",
            "Photograps",
            "List",
            "Text Completion",
            "Conversations",
            "Incomplete Sentences",
            "Talk Shorts"
           ,
    };
    public static int[] osImages = {
            R.drawable.info,
            R.drawable.landscape,
            R.drawable.list,
            R.drawable.notebook,
            R.drawable.speechbubble2,
            R.drawable.success,
            R.drawable.talkshort,

           };

    private  ImageButton selectedImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridview = (GridView) findViewById(R.id.customgrid);
        gridview.setAdapter(new CustomAdapter(this, osNameList, osImages));
        selectedImage= (ImageButton) findViewById(R.id.os_images);
        /*selectedImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PhotoActivity.class);
                startActivity(intent);
            }
        });*/

    }

}

