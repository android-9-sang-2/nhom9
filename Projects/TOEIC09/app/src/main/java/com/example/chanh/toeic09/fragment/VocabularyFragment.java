package com.example.chanh.toeic09.fragment;


import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.chanh.toeic09.Notification_receive;
import com.example.chanh.toeic09.R;
import com.example.chanh.toeic09.activity.MainActivity;
import com.example.chanh.toeic09.activity.TipsListActivity;
import com.example.chanh.toeic09.activity.VocabularyActivity;
import com.example.chanh.toeic09.activity.WordDetail;
import com.example.chanh.toeic09.adapter.GridViewAdapterTip;
import com.example.chanh.toeic09.adapter.VocabularyAdapter;
import com.example.chanh.toeic09.data.DBManager;
import com.example.chanh.toeic09.model.Vocabulary;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.NOTIFICATION_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class VocabularyFragment extends Fragment {
    ListView listView;
    VocabularyAdapter adapter;
    DatabaseReference mdata;
    NotificationCompat.Builder notification;
    ImageButton imgbtnRemind;
    AlarmManager alarmManager;

    public VocabularyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Vocabulary"); // thay doi title action bar
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vocabulary, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // xu ly trong nay
        imgbtnRemind = (ImageButton) getActivity().findViewById(R.id.imgbtnRemind);
        alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
        imgbtnRemind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                //calendar.set(Calendar.HOUR_OF_DAY,23);
                //calendar.set(Calendar.MINUTE,0);
                calendar.set(Calendar.SECOND,10);

                Intent intent = new Intent(getActivity(),Notification_receive.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
//                Calendar calendar = Calendar.getInstance();
//                //calendar.set(Calendar.HOUR_OF_DAY,23);
//                //calendar.set(Calendar.MINUTE,0);
//                calendar.set(Calendar.SECOND,10);
//                Intent intent = new Intent(getActivity(),Notification_receive.class);
////                DBManager dbManager = new DBManager(getContext(), "toeic81");
////                ArrayList<Vocabulary> arr = dbManager.getVocArrayChecked();
////                //chon ngau nhien 1 tu duoc danh dau de hien thi
////                Random randomGenerator = new Random();
////                int index = randomGenerator.nextInt(arr.size());
////                Vocabulary vocabulary = arr.get(index);
//                PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
//                AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
//                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
            }
        });


        listView = (ListView) getActivity().findViewById(R.id.ListViewVocabulary);
        mdata = FirebaseDatabase.getInstance().getReference();
        final ArrayList<Vocabulary> arrVoc;
        final DBManager dbManager = new DBManager(this.getContext(), "toeic81");
        arrVoc = dbManager.getVocArray();
        adapter = new VocabularyAdapter(getContext(), 0, arrVoc);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final ArrayList<Vocabulary> arrVoc2;
                arrVoc2 = dbManager.getVocArray();
                Intent intent = new Intent(getActivity() , WordDetail.class);
                Vocabulary vocabulary;
                vocabulary = arrVoc2.get(position);
                intent.putExtra("word", vocabulary);
                startActivity(intent);
            }
        });

    }
}
