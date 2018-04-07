package com.example.doan.demo;

/**
 * Created by Doan on 3/10/2018.
 */

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class PhotoActivity extends AppCompatActivity
{

    public static final String[] titles = new String[] { "photograp1",
            "photograp1", "photograp1", "photograp1","photograp1" };

    public static final String[] descriptions = new String[] {
            "1/1",
            "2/3", "3/3",
            "4/5","2/6" };

    public static final Integer[] images = { R.drawable.landscape,
            R.drawable.landscape, R.drawable.landscape, R.drawable.landscape, R.drawable.landscape };

    ListView listView;
    List<RowItem> rowItems;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photograp);

        rowItems = new ArrayList<RowItem>();
        for (int i = 0; i < titles.length; i++) {
            RowItem item = new RowItem(images[i], titles[i], descriptions[i]);
            rowItems.add(item);
        }

        listView = (ListView) findViewById(R.id.list);
        CustomListViewAdapter adapter = new CustomListViewAdapter(this,
                R.layout.customphoto, rowItems);
        listView.setAdapter(adapter);

    }


}