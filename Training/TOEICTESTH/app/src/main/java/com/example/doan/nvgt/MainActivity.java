package com.example.doan.nvgt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    GridView gvMain;
    String[] ten = new String[]{
            "Photographs", "Questions and Response", "Conversations", "Short Talks",
            "Incomplete Sentences", "Text Completion", "Reading", "Note"
    };
    int[] hinhanh = {
            R.drawable.photographs, R.drawable.questions, R.drawable.conversations, R.drawable.shorttalk,
            R.drawable.incomplete, R.drawable.textcompletion, R.drawable.read, R.drawable.ic_note2
    };
    List<String> ItemsList;
    TextView DisplaySelectedItem;
    String selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //main
        gvMain = (GridView) findViewById(R.id.gvMain);
        GridViewAdapter gridViewAdapter = new GridViewAdapter(this, ten, hinhanh);

       /* gvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //chuyen qua trang kia
                Intent intent = new Intent(MainActivity.this,TestNoteActivity.class);
                intent.putExtra("TestName",ten[7]);
                intent.putExtra("Logo",hinhanh[7]);
                startActivity(intent);
                //PASS INdex
                //startActivity(in);
            }
        });*/
        DisplaySelectedItem = (TextView) findViewById(R.id.textView);

        ItemsList = new ArrayList<String>(Arrays.asList(ten));

        gvMain.setAdapter(gridViewAdapter);

        gvMain.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
              //  String a = String.valueOf(position);
               // int b = position;
                int x = ten.length -1 ;
                if (x == position){
                    Intent intent = new Intent(MainActivity.this,TestNoteActivity.class);
                    intent.putExtra("TestName",ten[7]);
                    intent.putExtra("Logo",hinhanh[7]);
                    startActivity(intent);
                }

            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
       /* int id = item.getItemId();

        if (id == R.id.nav_practice) {
        } else if (id == R.id.nav_test) {

        } else if (id == R.id.nav_history) {

        } else if (id == R.id.nav_download) {

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_feedback) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class TextAdapter extends BaseAdapter {

        Context context;

        public TextAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {

            return ten.length;
        }

        @Override
        public Object getItem(int position) {

            return ten[position];
        }

        @Override
        public long getItemId(int position) {


            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            TextView text = new TextView(this.context);

            text.setText(ten[position]);

            text.setGravity(Gravity.CENTER);

            text.setBackgroundColor(Color.parseColor("#fbdcbb"));

            text.setLayoutParams(new GridView.LayoutParams(144, 144));


            return text;

        }
    }
}
