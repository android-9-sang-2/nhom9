package com.example.chanh.toeic09;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class WordDetail extends AppCompatActivity {
    TextView tvword,tvmeaning,tvsentence,tvmeansentence;
    Vocabulary vocabulary;
    String word,meaning,sentence,meansentence;
    NotificationCompat.Builder notification;
    CheckBox checkboxd;
    private static final int uniqueID = 45612;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_detail);
        //Nhan du lieu tu Intent
        Intent intent = getIntent();
        Vocabulary vocabulary = new Vocabulary();
        vocabulary = (Vocabulary) intent.getSerializableExtra("word");
//        Toast.makeText(this, vocabulary.getMean(), Toast.LENGTH_SHORT).show();
        Anhxa();
        checkboxd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                notification.setSmallIcon(R.drawable.ic_action_notify);
//                notification.setTicker("Notification Vocabulary");
//                notification.setWhen(System.currentTimeMillis());
//                notification.setContentTitle("Doanh oi sao dep trai qua");
//                notification.setContentText("Hi hi do ngok");
                // Chuyen qua class Vocabulary
              //  Intent intent = new Intent(WordDetail.this,WordDetail.class);
              //  PendingIntent pendingIntent = PendingIntent.getActivity(WordDetail.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
             //   notification.setContentIntent(pendingIntent);
                //
//                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                nm.notify(uniqueID,notification.build());
            }
        });
        tvword.setText(vocabulary.getWord());
        tvmeaning.setText(vocabulary.getMean());
        tvsentence.setText(vocabulary.getSentences());
        tvmeansentence.setText(vocabulary.getMeanSentences());
//        word = vocabulary.getWord().toString();
//        tvmeaning = vocabulary.getMean().toString();
//        tvsentence = vocabulary.getSentences().toString();
//        tvmeansentence = vocabulary.getMeanSentences().toString();
//          word = vocabulary.getWord();
//          meaning = vocabulary.getMean();
//          sentence = vocabulary.getSentences();
//          meansentence = vocabulary.getMeanSentences();
//          //Get data hien thi len textview
//          tvword.setText(word);
//          tvmeaning.setText(meaning);
//          tvsentence.setText(sentence);
//          tvmeansentence.setText(meansentence);
    }


    private void Anhxa() {
        tvword = (TextView) findViewById(R.id.tvword);
        tvmeaning = (TextView) findViewById(R.id.tvmeaning);
        tvsentence = (TextView) findViewById(R.id.tvsentence);
        tvmeansentence = (TextView) findViewById(R.id.tvmeansentence);
        checkboxd = (CheckBox) findViewById(R.id.checkbox);
    }

}
