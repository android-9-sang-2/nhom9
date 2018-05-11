package com.example.chanh.toeic09.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.chanh.toeic09.R;
import com.example.chanh.toeic09.data.DBManager;
import com.example.chanh.toeic09.model.Vocabulary;

public class WordDetail extends AppCompatActivity {
    TextView tvword,tvmeaning,tvsentence,tvmeansentence;
    Vocabulary vocabulary;
    String word,meaning,sentence,meansentence;
    NotificationCompat.Builder notification;
    CheckBox checkbox;
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
        tvword.setText(vocabulary.getWord());
        tvmeaning.setText(vocabulary.getMean());
        tvsentence.setText(vocabulary.getSentences());
        tvmeansentence.setText(vocabulary.getMeanSentences());
        if(vocabulary.flag.equalsIgnoreCase("1")){
            checkbox.setChecked(true);
        }
        else{
            checkbox.setChecked(false);
        }
        final String w = vocabulary.getWord();
        checkbox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                if (((CheckBox) v).isChecked()) {
                    Log.d("coclick", "aa");
                    final DBManager dbManager = new DBManager(WordDetail.this, "toeic81");
                    String word = tvword.toString();
                    dbManager.UpdateVoc(w, "1");
                }
                else{
                    Log.d("boclick", "aa");
                    final DBManager dbManager = new DBManager(WordDetail.this, "toeic81");
                    String word = tvword.toString();

                    dbManager.UpdateVoc(w, "0");
                }
                //case 2

            }
        });
    }


    private void Anhxa() {
        tvword = (TextView) findViewById(R.id.tvword);
        tvmeaning = (TextView) findViewById(R.id.tvmeaning);
        tvsentence = (TextView) findViewById(R.id.tvsentence);
        tvmeansentence = (TextView) findViewById(R.id.tvmeansentence);
        checkbox = (CheckBox) findViewById(R.id.checkbox);
    }

}
