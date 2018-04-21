package com.pino.giuaki;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class UpdateActivity extends Activity {
    final String DATA_NAME = "mydb.sqlite";
    public static Database database;
    ImageButton ibUpDateCapture,ibUpdateUpload;
    Button btnThem2,btnHuy2;
    EditText edUpdateGia,edUpdateTen;
    ImageView imreview2;
    final int REQUEST_CODE_CAMERA = 123;
    final int REQUEST_CODE_FOLDER=345;
    int id = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        AnhXa();
        layThongTin();
        ibUpDateCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // mo camera
                startActivityForResult(intent,REQUEST_CODE_CAMERA);
            }
        });
        ibUpdateUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK); // mo bo nho lay hinh
                intent.setType("image/*"); // chi hien hinh anh
                startActivityForResult(intent,REQUEST_CODE_FOLDER); // =>> xin quyen androidmainfres...
            }
        });
        btnThem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
        btnHuy2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateActivity.this,MainActivity.class));
            }
        });

    }


    private void layThongTin(){
        Intent intent = getIntent();
        id = intent.getIntExtra("ID",-1);
        database =  new Database(this,"mydb.sqlite",null,1);
        Cursor cursor = database.GetData("SELECT * FROM MonAn where Id ='" +id+"'" );
        cursor.moveToFirst();
        String ten = cursor.getString(1);
        String gia = cursor.getString(2);
        byte[] hinhAnh = cursor.getBlob(3);
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);

        edUpdateTen.setText(ten);
        edUpdateGia.setText(gia);
        imreview2.setImageBitmap(bitmap);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode== REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data!=null ) { // du lieu ok, requestcode = click ok
            // chuyen hinh anh ve kieu bitmap
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imreview2.setImageBitmap(bitmap);
        }
        if(requestCode==REQUEST_CODE_FOLDER && resultCode==RESULT_OK && data!=null){
            // LAY duong dan
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imreview2.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void AnhXa(){
        btnThem2 = (Button) findViewById(R.id.btnThem2);
        btnHuy2 = (Button) findViewById(R.id.btnHuy2);
        edUpdateGia = (EditText) findViewById(R.id.edUpdateGia);
        edUpdateTen = (EditText) findViewById(R.id.edUpdateTen);
        imreview2 = (ImageView) findViewById(R.id.imreview2);
        ibUpDateCapture = (ImageButton) findViewById(R.id.ibUpDateCapture);
        ibUpdateUpload = (ImageButton) findViewById(R.id.ibUpdateUpload);

    }
    private void update(){
        String ten = edUpdateTen.getText().toString().trim();
        String gia = edUpdateGia.getText().toString().trim();
        BitmapDrawable bitmapDrawable = (BitmapDrawable) imreview2.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap(); // chuyen ve kieu hinh anh
        ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream(); // chuyen ve kieu byte
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayInputStream);
        byte[] hinhAnh = byteArrayInputStream.toByteArray();
        MainActivity.database.UPDATE_MONAN(ten,gia,hinhAnh,id);
        Toast.makeText(UpdateActivity.this, "Sửa thành công!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(UpdateActivity.this,MainActivity.class));
    }
}
