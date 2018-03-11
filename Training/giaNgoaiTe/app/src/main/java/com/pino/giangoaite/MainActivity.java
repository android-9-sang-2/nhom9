package com.pino.giangoaite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    DatabaseReference mData;
    EditText edUSD;
    EditText edVang;
    EditText edEro;
    public static void update(){

    }
    Switch swupdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edUSD = (EditText) findViewById(R.id.edUSD);
        edVang = (EditText) findViewById(R.id.edVang);
        edEro = (EditText) findViewById(R.id.edEro);


        swupdate = (Switch) findViewById(R.id.swupdate);
        edUSD.setEnabled(false);
        edVang.setEnabled(false);
        edEro.setEnabled(false);

        mData=FirebaseDatabase.getInstance().getReference();
        // mData.child("USD").setValue("22.680VNĐ");
        mData.child("USD").addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {
                // new co du lieu
                edUSD.setText(dataSnapshot.getValue().toString());
            }

            public void onCancelled(DatabaseError databaseError) {
            }

        });
        mData.child("Gold").addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {
                // new co du lieu
                edVang.setText(dataSnapshot.getValue().toString());
            }

            public void onCancelled(DatabaseError databaseError) {
            }

        });
        mData.child("Euro").addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {
                // new co du lieu
                edEro.setText(dataSnapshot.getValue().toString());
            }

            public void onCancelled(DatabaseError databaseError) {
            }

        });

        swupdate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton compoundButton, boolean on) {

                if (!swupdate.isChecked()) {
                            String str = edUSD.getText().toString();
                            mData.child("USD").setValue(str);
                            edUSD.setEnabled(false);

                            String str2 = edVang.getText().toString();
                            mData.child("Gold").setValue(str2);
                            edVang.setEnabled(false);

                            String str3 = edEro.getText().toString();
                            mData.child("Euro").setValue(str3);
                            edEro.setEnabled(false);

                        } else {
                            edUSD.setEnabled(true);
                            edVang.setEnabled(true);
                            edEro.setEnabled(true);
                        }
                    }


                });

//        mData.child("USD").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // new co du lieu
//                edUSD.setText(dataSnapshot.getValue().toString());
//                swupdate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton compoundButton, boolean on) {
//
//                        if(!swupdate.isChecked()) {
//                            String str = edUSD.getText().toString();
//                            mData.child("USD").setValue(str);
//                            edUSD.setEnabled(false);
//                        }
//                        else {
//                            edUSD.setEnabled(true);
//                        }
//                    }
//                });
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });


    }
}
