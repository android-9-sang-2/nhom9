package com.example.chanh.toeic.Activity;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.chanh.toeic.Fragment.ListPart5Fragment;
import com.example.chanh.toeic.R;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ListPart5Fragment listPart5Fragment = new ListPart5Fragment();
        FragmentManager manager=getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.framlayout,listPart5Fragment,listPart5Fragment.getTag()).commit();
    }
}
