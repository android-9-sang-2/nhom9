package com.pino.toeictest;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class pictureQuestionsActivity extends AppCompatActivity {
    TextView txtBeginTime,txtEndTime;
    ImageView hinhp1;
    SeekBar skListen;
    ImageButton btnPrev,btnNext,btnSkip,btnPlay;
    ArrayList<picture_questions> arrayPicture_questions;
    int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_questions);
        AnhXa();
        AddFile();
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = MediaPlayer.create(pictureQuestionsActivity.this,arrayPicture_questions.get(position).getFilenghe());
                mediaPlayer.start();
                hinhp1.setImageResource(arrayPicture_questions.get(position).getHinh());


            }
        });

    }

    private void AddFile() {
        arrayPicture_questions = new ArrayList<>();
        arrayPicture_questions.add(new picture_questions(R.drawable.demo,R.raw.test1p1));
        arrayPicture_questions.add(new picture_questions(R.drawable.part2,R.raw.t2p1));
    }


    private void AnhXa() {
        txtBeginTime = (TextView) findViewById(R.id.txtBeginTime);
        txtEndTime = (TextView) findViewById(R.id.txtEndTime);
        skListen = (SeekBar) findViewById(R.id.skListen);
        btnPrev = (ImageButton) findViewById(R.id.btnPrev);
        btnNext = (ImageButton) findViewById(R.id.btnNext);
        btnSkip = (ImageButton) findViewById(R.id.btnSkip);
        btnPlay = (ImageButton) findViewById(R.id.btnPlay);


    }
}
