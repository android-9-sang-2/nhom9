package com.example.chanh.vietnamese;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chanh.vietnamese.data.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvTest;
    TextView tvScore;
    Button btnSubmit;
    Button btnAdmin;
    Button btnRefresh;
    TextView tvAnswer;
    DatabaseReference mData;
    ArrayList<Question> arr;
    int count_correct_ans;
    int count_question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init
        lvTest = findViewById(R.id.lvTest);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnAdmin = findViewById(R.id.btnAdmin);
        btnRefresh = findViewById(R.id.btnRefresh);
        tvScore = findViewById(R.id.tvScore);
        count_correct_ans=0;
        //get Data
        mData = FirebaseDatabase.getInstance().getReference();
        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                count_question= (int) dataSnapshot.getChildrenCount();
                arr = new ArrayList<>();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    Question question = ds.getValue(Question.class);
                    arr.add(question);
                }
                final ListViewAdapter adapter;
                adapter = new ListViewAdapter(MainActivity.this, R.layout.listview_row, arr);
                lvTest.setAdapter(adapter);
                btnSubmit.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        count_correct_ans=0;
                        for(int i = 0; i< adapter.selectedAnswers.size(); i++){
                            if(adapter.selectedAnswers.get(i).equalsIgnoreCase(arr.get(i).getCorrectAnswer())){
                                count_correct_ans+=1;
//                                adapter.getView();
                                arr.get(i).setQuestion(arr.get(i).getQuestion() + "(Correct)");
                            }
                            else arr.get(i).setQuestion(arr.get(i).getQuestion() + "(Incorrect. Correct answer is "+ arr.get(i).getCorrectAnswer()+")");
                        }
                        tvScore.setText("Score: " + String.valueOf(count_correct_ans) + "/" + String.valueOf(count_question) );
                        btnSubmit.setEnabled(false);
                    }
                });
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //------------ket thuc lay du lieu------
        btnAdmin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Admin.class);
                startActivity(intent);
            }
        });
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

    }

}
