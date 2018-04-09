package com.example.chanh.toeic.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chanh.toeic.R;
import com.example.chanh.toeic.TestListActivity;
import com.example.chanh.toeic.data.DiemDBSoure;
import com.example.chanh.toeic.model.Questions;

import java.util.ArrayList;

public class chamDiem_P5Activity extends Activity {
    ArrayList<Questions> questionsArrayList = new ArrayList<Questions>();
    int khongTrl=0,numTrue=0,numFalse=0,tongDiem=0;
    TextView txtTrue2P5,txtFalse2P5,txtNo2P5,txtTotal2P5;
    Button btAgainP5,btExitP5,btSaveP5;
    DiemDBSoure diemDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cham_diem__p5);
        Intent intent = getIntent();
        diemDB = new DiemDBSoure(chamDiem_P5Activity.this);

        questionsArrayList = (ArrayList<Questions>) intent.getExtras().getSerializable("arr_Quest");
        begin();
        checkKetQua();
        txtNo2P5.setText(""+khongTrl);
        txtTrue2P5.setText(""+numTrue);
        txtFalse2P5.setText(""+numFalse);
        txtTotal2P5.setText(""+(numTrue*5));
        btExitP5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(chamDiem_P5Activity.this);
                builder.setIcon(R.drawable.ic_exit);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn thoát hay không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish(); // thoat activity
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();

            }
        });
        btSaveP5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(chamDiem_P5Activity.this);
                LayoutInflater inflater = chamDiem_P5Activity.this.getLayoutInflater();
                View view = inflater.inflate(R.layout.savediem_alertdialog,null);
                builder.setView(view);
                TextView txtDiemP5 = (TextView) view.findViewById(R.id.txtDiemP5);
                final int numTotal = numTrue*5;
                txtDiemP5.setText(numTotal+" diem");
//                builder.setTitle("Lưu điểm");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        diemDB.insertScrore(null,String.valueOf(numTotal),1);
                        Toast.makeText(chamDiem_P5Activity.this, "Save done!", Toast.LENGTH_SHORT).show();
                        finish();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // tu dong
                    }
                });
                AlertDialog b = builder.create();
                b.show();
            }
        });
    }

    //anhxa
    public void begin(){
        txtFalse2P5 = (TextView) findViewById(R.id.txtFalseP52P5);
        txtTrue2P5 = (TextView) findViewById(R.id.txtTrue2P5);
        txtNo2P5 = (TextView) findViewById(R.id.txtNo2P5);
        txtTotal2P5 = (TextView) findViewById(R.id.txtTotal2P5);
        btAgainP5 = (Button) findViewById(R.id.btAgainP5);
        btExitP5 = (Button) findViewById(R.id.btExitP5);
        btSaveP5 = (Button) findViewById(R.id.btSaveP5);

    }

    // check ket qua
    public void checkKetQua(){
        for(int i = 0; i<questionsArrayList.size();i++){
            if(questionsArrayList.get(i).getTraloi().equals("")==true){//chua tra loi
                khongTrl++;
            }else if(questionsArrayList.get(i).getTraloi().equals(questionsArrayList.get(i).getDapAn())==true){//so sanh dap an
                numTrue++;
            } else {
                numFalse++;
            }
        }
    }
}
