package com.nguyendoanh.mynavigation;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    NotificationCompat.Builder notification;
    ImageButton imgbutton;
    private static final int uniqueID = 45612;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);
//        imgbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Xay dung notification
////        //set hinh small icon
//        notification.setSmallIcon(R.drawable.ic_launcher_background);
//        notification.setTicker("Notification Vocabulary");
//        notification.setWhen(System.currentTimeMillis());
//        notification.setContentTitle("Here is Doanh");
//        notification.setContentText("Nhac nho co 1 tu vung moi");
//        Intent intent = new Intent(this,MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
//        notification.setContentIntent(pendingIntent);
//        //
//        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//         nm.notify(uniqueID,notification.build());
//            }
//        });
    }

    private void Anhxa() {
        imgbutton = (ImageButton) findViewById(R.id.imgbutton);
    }
    public void  ButtonClick(View view){
        //Xay dung notification
        //set hinh small icon
        notification.setSmallIcon(R.drawable.ic_action_icon);
        notification.setTicker("Notification Vocabulary");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Here is Doanh");
        notification.setContentText("Nhac nho co 1 tu vung moi");
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);
        //
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
         nm.notify(uniqueID,notification.build());
    }


}
