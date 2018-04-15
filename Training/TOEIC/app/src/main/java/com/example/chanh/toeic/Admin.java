package com.example.chanh.toeic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class Admin extends AppCompatActivity {

    ListView lvHinhAnh;
    ArrayList<HinhAnh> mangHinhAnh = new ArrayList<HinhAnh>();
    Button btnSave;
    ImageView imgHinh;
    EditText editTenHinh;
    int REQUEST_CODE_IMAGE = 1;
    HinhAnhAdapter adapter = null;

    DatabaseReference mData;
    FirebaseStorage storage = FirebaseStorage.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        mData = FirebaseDatabase.getInstance().getReference();
        final StorageReference storageRef = storage.getReferenceFromUrl("gs://toeic-4227e.appspot.com");

        AnhXa();

        adapter = new HinhAnhAdapter(this, R.layout.dong_hinh_anh, mangHinhAnh);
        lvHinhAnh.setAdapter(adapter);

        LoadData();

        imgHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_IMAGE);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                StorageReference mountainsRef = storageRef.child("image" + calendar.getTimeInMillis() + ".png");


                imgHinh.setDrawingCacheEnabled(true);
                imgHinh.buildDrawingCache();
                Bitmap bitmap = imgHinh.getDrawingCache();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] data = baos.toByteArray();

                UploadTask uploadTask = mountainsRef.putBytes(data);
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        Toast.makeText(Admin.this, "Lỗi!!!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Toast.makeText(Admin.this, "Lưu hình thành công.", Toast.LENGTH_SHORT).show();
                        Log.d("AAAA", downloadUrl + "");

                        //Tạo note trên phần database
                        HinhAnh hinhAnh = new HinhAnh(editTenHinh.getText().toString(), String.valueOf(downloadUrl));
                        mData.child("HinhAnh").push().setValue(hinhAnh, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                if (databaseError == null){
                                    Toast.makeText(Admin.this, "Lưu dữ liệu thành công", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(Admin.this, "Lỗi lưu dữ liệu!!!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    private void LoadData(){
        mData.child("HinhAnh").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                HinhAnh hinhAnh = dataSnapshot.getValue(HinhAnh.class);
                mangHinhAnh.add(new HinhAnh(hinhAnh.TenHinh, hinhAnh.LinkHinh));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_IMAGE && resultCode == RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgHinh.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void AnhXa(){
        btnSave     = (Button) findViewById(R.id.buttonSave);
        imgHinh     = (ImageView) findViewById(R.id.imageViewHinh);
        editTenHinh = (EditText) findViewById(R.id.editTextTenHinh);
        lvHinhAnh   = (ListView) findViewById(R.id.listViewHinhAnh);
    }
}
