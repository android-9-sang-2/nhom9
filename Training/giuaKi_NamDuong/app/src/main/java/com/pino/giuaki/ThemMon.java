package com.pino.giuaki;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ThemMon extends AppCompatActivity {
    Button btnThem,btnHuy;
    ImageButton ibCapture,ibUpload;
    ImageView imreview;
    EditText edTen,edGia;
    int REQUEST_CODE_CAMERA = 123;
    int REQUEST_CODE_FOLDER = 456;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_mon);
        btnThem = (Button) findViewById(R.id.btnThem);
        btnHuy = (Button) findViewById(R.id.btnHuy);
        ibCapture = (ImageButton) findViewById(R.id.ibCapture);
        ibUpload = (ImageButton) findViewById(R.id.ibUpload);
        imreview = (ImageView) findViewById(R.id.imreview);
        edTen = (EditText) findViewById(R.id.edTen);
        edGia = (EditText) findViewById(R.id.edGia);
        ibCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // mo camera
                startActivityForResult(intent,REQUEST_CODE_CAMERA);
            }
        });
        ibUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK); // mo bo nho lay hinh
                intent.setType("image/*"); // chi hien hinh anh
                startActivityForResult(intent,REQUEST_CODE_FOLDER); // =>> xin quyen androidmainfres...
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // chuyen data imageview -> byte[]
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imreview.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap(); // chuyen ve kieu hinh anh
                ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream(); // chuyen ve kieu byte
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayInputStream);
                byte[] hinhAnh = byteArrayInputStream.toByteArray();
                MainActivity.database.INSERT_MONAN(
                        edTen.getText().toString().trim(),
                        edGia.getText().toString().trim(),
                        hinhAnh


                );
                Toast.makeText(ThemMon.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ThemMon.this,MainActivity.class));
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThemMon.this,MainActivity.class));
            }
        });

    }
    // do Hinh ra tu ibCapture

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CODE_FOLDER && resultCode==RESULT_OK && data!=null){
            // LAY duong dan
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imreview.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(requestCode== REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data!=null ) { // du lieu ok, requestcode = click ok
            // chuyen hinh anh ve kieu bitmap
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imreview.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
