package com.example.chanh.toeic09.activity;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.chanh.toeic09.DataVocabulary;
import com.example.chanh.toeic09.R;
import com.example.chanh.toeic09.adapter.VocabularyAdapter;
import com.example.chanh.toeic09.model.Vocabulary;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class VocabularyActivity extends AppCompatActivity {
    ListView listView;
    VocabularyAdapter adapter;
    DatabaseReference mdata;
    NotificationCompat.Builder notification;
    ImageButton imgbtnRemind;
    String word,mean;
    Vocabulary vocabulary;
    AlarmManager alarmManager;
    private static final int uniqueID = 45612;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);
        Anhxa();
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        getTime();
        imgbtnRemind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notification.setSmallIcon(R.drawable.ic_action_notify);
                notification.setTicker("Notification Vocabulary");
                notification.setWhen(System.currentTimeMillis());
                notification.setContentTitle("Doanh oi sao dep trai qua");
                notification.setContentText("Hi hi do ngok");
                // Chuyen qua class Vocabulary
                Intent intent = new Intent(VocabularyActivity.this,MainActivity.class);
                //PendingIntent pendingIntent = PendingIntent.getActivity(VocabularyActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(VocabularyActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,10000,pendingIntent);
//                notification.setContentIntent(pendingIntent);
                //
                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                nm.notify(uniqueID,notification.build());
            }
        });
        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);
        listView = (ListView) findViewById(R.id.ListViewVocabulary);
        mdata = FirebaseDatabase.getInstance().getReference();
        final ArrayList<Vocabulary> arrVoc = new ArrayList<>();
        Intent intent = getIntent();
        Vocabulary vocabulary = new Vocabulary();
        vocabulary = (Vocabulary) intent.getSerializableExtra("word");
        word = intent.getStringExtra("word");
        mean = intent.getStringExtra("mean");
//        final String word = intent.getStringExtra("word");
//        final String meaning = intent.getStringExtra("meaning");
        mdata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.child("vocabulary").getChildren()){
                    Vocabulary vo = ds.getValue(Vocabulary.class);
                    arrVoc.add(vo);
                }
                adapter = new VocabularyAdapter(getApplicationContext(), 0, arrVoc);
                listView.setAdapter(adapter);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(VocabularyActivity.this,WordDetail.class);
                Vocabulary vocabulary = new Vocabulary();
                vocabulary = arrVoc.get(position);
                intent.putExtra("word",vocabulary);
////                intent.putExtra("indexTestSet",String.valueOf(i+1));
////                intent.putExtra("audio",indexPart);
//                intent.putExtra("meaning",meaning);
                startActivity(intent);
            }
        });


//        getData();

//        adapter = new VocabularyAdapter(this, 0, arrVoc);
//        listView.setAdapter(adapter);
    }


    private void Anhxa() {

        imgbtnRemind = (ImageButton) findViewById(R.id.imgbtnRemind);
    }
    private void getTime(){
        Date currentTime = Calendar.getInstance().getTime();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                notification.setSmallIcon(R.drawable.ic_action_notify);
                notification.setTicker("Notification Vocabulary");
                notification.setWhen(System.currentTimeMillis());
                notification.setContentTitle("Doanh oi sao dep trai qua");
                notification.setContentText("Hi hi do ngok");
                // Chuyen qua class Vocabulary
                Intent intent = new Intent(VocabularyActivity.this,VocabularyActivity.class);
                //PendingIntent pendingIntent = PendingIntent.getActivity(VocabularyActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(VocabularyActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,10000,pendingIntent);
                notification.setContentIntent(pendingIntent);
                //
                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                nm.notify(uniqueID,notification.build());
            }
        },1000*10);
    }

//    public void getData(){
//        DataVocabulary.getData().arrVoc = new ArrayList<>();
//    }

}
