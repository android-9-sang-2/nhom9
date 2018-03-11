package com.example.vchnhi.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.vchnhi.demo.model.Person;

import java.util.ArrayList;

public class LinearActivity extends AppCompatActivity {
    //khai bao bien toan cuc
    private EditText etHoTen;
    private EditText etDiaChi;
    private RadioGroup rgTinhTrang;
    private RadioButton rbBinhThuong;
    private RadioButton rbKhuyetTat;
    private ListView lvDanhSach;
    private Button btnThem;
    private Button btnThoat;
    private ArrayList<Person> data = new ArrayList<>();
    private UserAdapter userAdapter;

    private void setcontrol() {
        etHoTen = (EditText) findViewById(R.id.etHoTen); /*ep kieu*/
        etDiaChi = (EditText) findViewById(R.id.etDiaChi);
        rgTinhTrang = findViewById(R.id.rgTinhTrang);
        rbBinhThuong = findViewById(R.id.rbBinhThuong);
        rbKhuyetTat = findViewById(R.id.rbKhuyetTat);
        lvDanhSach = findViewById(R.id.lvDanhSach);
        btnThem = findViewById(R.id.btnThem);
        btnThoat = findViewById(R.id.btnThoat);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear);
        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
        setcontrol();
        final ArrayList<Person> arr = new ArrayList<>();
        final UserAdapter adapter = new UserAdapter(LinearActivity.this, R.layout.listview_row, arr);
        lvDanhSach.setAdapter(adapter);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person person = new Person();
                person.setHoTen(etHoTen.getText().toString());
                person.setDiaChi(etDiaChi.getText().toString());
                if (rbBinhThuong.isChecked())
                    person.setTinhTrang(rbBinhThuong.getText().toString());
                else
                    person.setTinhTrang(rbKhuyetTat.getText().toString());
                Toast.makeText(getApplicationContext(), person.toString(), Toast.LENGTH_SHORT).show();
                arr.add(person);
                adapter.notifyDataSetChanged();
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}