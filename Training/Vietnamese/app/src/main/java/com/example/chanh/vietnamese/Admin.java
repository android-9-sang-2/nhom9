package com.example.chanh.vietnamese;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.chanh.vietnamese.data.Question;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Admin extends AppCompatActivity {
    Button btnAdd, btnDelete, btnUpdate;
    Switch swichUpdate;
    EditText Question, A, B, C, correctAnswer, etDelete;
    Spinner dropdownQuestion;
    ArrayList<String> arr_question_name;
    ArrayList<Question> arr_question;
    DatabaseReference mData;
    String last_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        dropdownQuestion = findViewById(R.id.dropdownQuestion);
        swichUpdate =(Switch) findViewById(R.id.switchSelection);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete= findViewById(R.id.btnDelete);
        btnUpdate= findViewById(R.id.btnUpdate);
        Question = findViewById(R.id.etQuestion);
        A = findViewById(R.id.etA);
        B = findViewById(R.id.etB);
        C = findViewById(R.id.etC);
//        etDelete= findViewById(R.id.etdelete);
        correctAnswer = findViewById(R.id.etcorrectAnswer);
        mData = FirebaseDatabase.getInstance().getReference();
        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                count_correct_ans= dataSnapshot.getChildrenCount();
                arr_question_name = new ArrayList<>();
                arr_question = new ArrayList<>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String data = ds.getKey() + "_" + ds.child("question").getValue();
                    arr_question_name.add(data);
                    Question question = ds.getValue(Question.class);
                    arr_question.add(question);
                    Log.d("daivcccc", data);
                    last_id = ds.getKey();
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Admin.this, android.R.layout.simple_dropdown_item_1line , arr_question_name);
                dropdownQuestion.setAdapter(arrayAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        dropdownQuestion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,  int position, long id) {
                String name = (String) parent.getItemAtPosition(position);
                String[] parts = name.split("_");
                String index = parts[0];
                Log.d("aka", index);
                Question selectedQuestion=new  Question();
                for(Question q: arr_question){
                    Log.d("aka2", q.getIndex());
                    if(q.getIndex().equalsIgnoreCase(index)){
                        selectedQuestion=q;
                        break;
                    }
                }
                if(swichUpdate.isChecked()){
                    Question.setText(selectedQuestion.getQuestion());
                    A.setText(selectedQuestion.getA());
                    B.setText(selectedQuestion.getB());
                    C.setText(selectedQuestion.getC());
                    correctAnswer.setText(selectedQuestion.getCorrectAnswer());
                    Log.d("daiadai", "sot=mthing");
                }
//                Question tmp = mData   .child((String) parent.getItemAtPosition(position);
//                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(Question.getText().toString().equalsIgnoreCase("")
                            || A.getText().toString().equalsIgnoreCase("")
                            || B.getText().toString().equalsIgnoreCase("")
                            || C.getText().toString().equalsIgnoreCase("")
                            || correctAnswer.getText().toString().equalsIgnoreCase("")
                            || (!correctAnswer.getText().toString().toUpperCase().equalsIgnoreCase("A")
                            &&!correctAnswer.getText().toString().toUpperCase().equalsIgnoreCase("B")
                            &&!correctAnswer.getText().toString().toUpperCase().equalsIgnoreCase("C")
                    )
                            )
                    {
                        Toast.makeText(Admin.this, "Add failed\n Check all fields carefully", Toast.LENGTH_SHORT).show();
                    }
                else {
                    Question question = new Question();
                    question.setQuestion(Question.getText().toString());
                    question.setA(A.getText().toString());
                    question.setB(B.getText().toString());
                    question.setC(C.getText().toString());
                    question.setIndex(String.valueOf(Integer.valueOf(last_id)+1));
                    question.setCorrectAnswer(correctAnswer.getText().toString().toUpperCase());
                    mData.child(String.valueOf(Integer.valueOf(last_id)+1)).setValue(question);
                    Question.setText("");
                    A.setText("");
                    B.setText("");
                    C.setText("");
                    correctAnswer.setText("");
                    Toast.makeText(Admin.this, "Successfully added", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = dropdownQuestion.getSelectedItem().toString();
                String[] parts = name.split("_");
                String index = parts[0];
                mData.child(String.valueOf(Integer.valueOf(index))).removeValue();
                Toast.makeText(Admin.this, "Delete successfully ", Toast.LENGTH_SHORT).show();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Question.getText().toString().equalsIgnoreCase("")
                        || A.getText().toString().equalsIgnoreCase("")
                        || B.getText().toString().equalsIgnoreCase("")
                        || C.getText().toString().equalsIgnoreCase("")
                        || correctAnswer.getText().toString().equalsIgnoreCase("")
                        || (!correctAnswer.getText().toString().toUpperCase().equalsIgnoreCase("A")
                            &&!correctAnswer.getText().toString().toUpperCase().equalsIgnoreCase("B")
                            &&!correctAnswer.getText().toString().toUpperCase().equalsIgnoreCase("C")
                             )
                        )
                {
                    Toast.makeText(Admin.this, "Update failed\n Check all fields carefully", Toast.LENGTH_SHORT).show();
                }
                else{
                    //dua theo id ma update
                    String name = dropdownQuestion.getSelectedItem().toString();
                    String[] parts = name.split("_");
                    String index = parts[0];
                    //chuan bi dl
                    Question question = new Question();
                    question.setQuestion(Question.getText().toString());
                    question.setA(A.getText().toString());
                    question.setB(B.getText().toString());
                    question.setC(C.getText().toString());
                    question.setCorrectAnswer(correctAnswer.getText().toString().toUpperCase());
                    question.setIndex(index);

                    mData.child(index).setValue(question);
                    Toast.makeText(Admin.this, "Update successfully ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        swichUpdate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){ //modify mode
                    btnAdd.setVisibility(View.INVISIBLE);
                    btnDelete.setVisibility(View.VISIBLE);
                    btnUpdate.setVisibility(View.VISIBLE);
                    dropdownQuestion.setVisibility(View.VISIBLE);

                    //get value
                    String name = dropdownQuestion.getSelectedItem().toString();
                    String[] parts = name.split("_");
                    String index = parts[0];
                }
                else{
                    btnAdd.setVisibility(View.VISIBLE);
                    btnDelete.setVisibility(View.INVISIBLE);
                    btnUpdate.setVisibility(View.INVISIBLE);
                    dropdownQuestion.setVisibility(View.INVISIBLE);
                    Question.setText("");
                    A.setText("");
                    B.setText("");
                    C.setText("");
                    correctAnswer.setText("");
                }
            }
        });

    }
}
