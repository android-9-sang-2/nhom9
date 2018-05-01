package com.nguyendoanh.sqlite;

import android.content.Intent;
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

public class ThemGiayActivity extends AppCompatActivity {
    final  String DATABASE_NAME ="";
    Button btnAdd,btnCancel;
    EditText edTen, edMota;
    ImageView imgHinh;
    ImageButton ibtnCamera,ibtnFolder;
    int REQUEST_CODE_CAMERA = 123;
    int REQUEST_CODE_FOLDER = 456;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_giay);
        Anhxa();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override // chuyen data imageview -> mang  byte[]
            public void onClick(View view) {
                // Chuyển imageView thành mảng byte để lưu
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgHinh.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray  = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                byte[] hinhAnh = byteArray.toByteArray();
                //Nhận 2 trường edittext
                MainActivity.database.INSERT_GIAY(
                        edTen.getText().toString().trim(),
                        edMota.getText().toString().trim(),
                        hinhAnh
                );
                Toast.makeText(ThemGiayActivity.this,"Đã thêm thành công",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ThemGiayActivity.this,MainActivity.class));
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThemGiayActivity.this,MainActivity.class));
            }
        });
        // Set sự kiện Button Click Camera
        ibtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // Mở camera
                startActivityForResult(intent,REQUEST_CODE_CAMERA);
            }
        });
        //Set sự kiện Button Click Folder
        ibtnFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                //Lấy hình ->setType kiểu dữ liệu nào , mở, và chỉ nhận file chứa hình
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_FOLDER);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Kiểm tra
        if(requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null){
            // Đổ dữ liệu ra -> Trả về kiểu bitmap
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgHinh.setImageBitmap(bitmap);
        }

        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null){
            // Lấy đường dẫn từ bộ nhớ ra
            Uri uri = data.getData();
            try {
                // Mở cái chỗ để đọc dữ liệu mình ra
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                // Chuyển kiểu dữ liệu về bitmap sau đó -> decode dữ liệu theo stream
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                // Có hình rồi thì gọi setImageBitmap
                    imgHinh.setImageBitmap(bitmap);

            }catch (FileNotFoundException e) {
                e.printStackTrace();
        }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void Anhxa() {
        btnAdd      = (Button) findViewById(R.id.buttonAdd);
        btnCancel   = (Button) findViewById(R.id.buttonHuy);
        edMota      = (EditText) findViewById(R.id.editTextMoTa);
        edTen       = (EditText) findViewById(R.id.editTextTenGiay);
        imgHinh     = (ImageView) findViewById(R.id.imageviewHinh);
        ibtnCamera  = (ImageButton) findViewById(R.id.imagebuttonCamera);
        ibtnFolder  = (ImageButton) findViewById(R.id.imagebuttonFolder);

    }
}
