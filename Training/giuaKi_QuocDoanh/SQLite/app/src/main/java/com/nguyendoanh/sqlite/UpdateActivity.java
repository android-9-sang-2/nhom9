package com.nguyendoanh.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class UpdateActivity extends AppCompatActivity {
    final String DATABASE_NAME ="QuanLy.sqlite";
    public static Database database;
    ImageButton imgbUpdate;
    Button btnChup,btnChon,btnLuu,btnHuy;
    ImageView imageView2;
    EditText editupdateten,editupdatemota;
    final int REQUEST_CODE_CAMERA = 123;
    final int REQUEST_CODE_FOLDER = 456;
    int id = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        AnhXa();
        layThongTin();
        btnChup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);  // Mo camera
                startActivityForResult(intent,REQUEST_CODE_CAMERA);
            }
        });
        btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_FOLDER);

            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateActivity.this,MainActivity.class));
            }
        });

    }


    private void update() {
        String ten = editupdateten.getText().toString().trim();
        String mota = editupdatemota.getText().toString().trim();
        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView2.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap(); // Chuyển về kiểu hình ảnh
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // Chuyển về kiểu byte
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
        byte[] hinh = byteArrayOutputStream.toByteArray();
        MainActivity.database.UPDATE_GIAY(ten,mota,hinh,id);
        Toast.makeText(UpdateActivity.this,"Cập nhật thành Công",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(UpdateActivity.this,MainActivity.class));
    }

    private void layThongTin() {
        Intent intent = getIntent();
        id = intent.getIntExtra("ID",-1);
        database = new Database(this,"QuanLy.sqlite",null,1);
        Cursor cursor = database.GetData("SELECT * FROM Giay where Id='"+id+"'");
        cursor.moveToFirst();
        String ten = cursor.getString(1);
        String mota = cursor.getString(2);
        byte[] hinh = cursor.getBlob(3);
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinh,0,hinh.length);
        editupdateten.setText(ten);
        editupdatemota.setText(mota);
        imageView2.setImageBitmap(bitmap);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CAMERA && requestCode == RESULT_OK && data != null) { // Du lieu ok , requestcode = click ok
            // Chuyen hinh anh ve kieu bitmap
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView2.setImageBitmap(bitmap);
        }
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            InputStream inputStream = null;
            try {
                inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView2.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    private void AnhXa() {
        btnLuu = (Button) findViewById(R.id.buttonLuu);
        btnHuy = (Button) findViewById(R.id.buttonCancel);
        imageView2 = (ImageView) findViewById(R.id.imageview2);
        editupdateten = (EditText) findViewById(R.id.editTextUpdateGiay);
        editupdatemota = (EditText) findViewById(R.id.edittextUpdateMoTa);
        btnChon = (Button) findViewById(R.id.btnChonhinh);
        btnChup = (Button) findViewById(R.id.btnChuphinh);
    }
}
