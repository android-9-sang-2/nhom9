package com.example.chanh.toeic09;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;

import com.example.chanh.toeic09.activity.VocabularyActivity;

public class Notification extends AppCompatActivity {
    NotificationCompat.Builder notification;
    ImageButton imgbtnRemind;
    private static final int uniqueID = 45612;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Anhxa();
        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);
    }

    private void Anhxa() {
        imgbtnRemind = (ImageButton) findViewById(R.id.imgbtnRemind);
    }
    public void  ButtonClick(View view){
        //Xay dung notification
        //set hinh small icon
        notification.setSmallIcon(R.drawable.ic_action_notify);
        notification.setTicker("Notification Vocabulary");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Here is Doanh");
        notification.setContentText("Nhac nho co 1 tu vung moi");
        // Chuyen qua class Vocabulary
        Intent intent = new Intent(this,VocabularyActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);
        //
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqueID,notification.build());
    }
}
