package com.example.chanh.toeic09;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.chanh.toeic09.activity.MainActivity;
import com.example.chanh.toeic09.activity.WordDetail;
import com.example.chanh.toeic09.data.DBManager;
import com.example.chanh.toeic09.model.Vocabulary;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by nguyendoanh on 5/11/2018.
 */

public class Notification_receive extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent repeating_intent = new Intent(context,WordDetail.class);
//        Intent repeating_intent = new Intent(context,MainActivity.class);
        DBManager dbManager = new DBManager(context, "toeic81");
        ArrayList<Vocabulary> arr = dbManager.getVocArrayChecked();
        //chon ngau nhien 1 tu duoc danh dau de hien thi
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(arr.size());
        Vocabulary vocabulary = arr.get(index);
        repeating_intent.putExtra("word", vocabulary);

        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,100,repeating_intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Notification Vocabulary")
                .setContentText(vocabulary.getWord())
                .setAutoCancel(true);
        notificationManager.notify(100,builder.build());
    }
}
