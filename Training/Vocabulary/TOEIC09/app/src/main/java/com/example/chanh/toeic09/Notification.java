package com.example.chanh.toeic09;//package com.example.chanh.toeic09;
//
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.content.Intent;
//import android.support.v4.app.NotificationCompat;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.text.Html;
//import android.view.View;
//import android.widget.ImageButton;
//
//public class Notification extends AppCompatActivity {
//    NotificationCompat.Builder notification;
//    ImageButton imgbtnRemind;
//    private static final int uniqueID = 45612;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_notification);
//        Anhxa();
//        notification = new NotificationCompat.Builder(this);
//        notification.setAutoCancel(true);
//    }
//
//    private void Anhxa() {
//        imgbtnRemind = (ImageButton) findViewById(R.id.imgbtnRemind);
//    }
//    public void  ButtonClick(View view){
//        //Xay dung notification
//        //set hinh small icon
//        notification.setSmallIcon(R.drawable.ic_action_notify);
//        notification.setTicker("Notification Vocabulary");
//        notification.setWhen(System.currentTimeMillis());
//        notification.setContentTitle("Here is Doanh");
//        notification.setContentText("Nhac nho co 1 tu vung moi");
//        // Chuyen qua class Vocabulary
//        Intent intent = new Intent(this,VocabularyActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
//        notification.setContentIntent(pendingIntent);
//        //
//        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        nm.notify(uniqueID,notification.build());
//    }
//}

import android.content.Context;import android.app.NotificationChannel;
        import android.app.NotificationManager;
        import android.app.PendingIntent;
        import android.content.Context;
        import android.content.Intent;
        import android.graphics.Color;
        import android.provider.Settings;
        import android.support.v4.app.NotificationCompat;

public class Notification {
    private Context mContext;
    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;
    public static final String NOTIFICATION_CHANNEL_ID = "10001";

    public Notification(Context context) {
        mContext = context;
    }

    public void createNotification(String title, String shortMess, String message, Class activityClass)
    {
        /**Creates an explicit intent for an Activity in your app**/
        Intent resultIntent = new Intent(mContext, activityClass);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(mContext,
                0,
                resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder = new NotificationCompat.Builder(mContext);
        mBuilder.setSmallIcon(R.drawable.ic_action_notify);
        mBuilder.setContentTitle(title)
                .setContentText(shortMess)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(message))
                .setAutoCancel(true)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setContentIntent(resultPendingIntent);

        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
        {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            assert mNotificationManager != null;
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }
        assert mNotificationManager != null;
        mNotificationManager.notify(0 , mBuilder.build());
    }
}