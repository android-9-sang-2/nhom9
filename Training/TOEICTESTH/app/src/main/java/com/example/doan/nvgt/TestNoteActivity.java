package com.example.doan.nvgt;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TestNoteActivity extends AppCompatActivity {

   // Button btn;
    private  ListView lvTest;
    int count;

    private static final int MENU_ITEM_VIEW = 111;
    private static final int MENU_ITEM_EDIT = 222;
    private static final int MENU_ITEM_CREATE = 333;
    private static final int MENU_ITEM_DELETE = 444;
    private static final int MY_REQUEST_CODE = 1000;

    private final List<Note> noteList = new ArrayList<Note>();
    private ArrayAdapter<Note> listViewAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
    ///    getSupportActionBar().setDisplayHomeAsUpEnabled(true); //back arrow
        //nhan du lieu
        Intent intent = getIntent();

        int logo=intent.getIntExtra("Logo",0);
        final String testname=intent.getStringExtra("TestName");
        //thiet lap truyen noi dung cho listview
       // count = 15;
        lvTest = findViewById(R.id.lvTest);
        ListViewAdapter listViewAdapter = new ListViewAdapter(this, testname, logo, count);
        lvTest.setAdapter(listViewAdapter);
        //set the title de lam gi
        //setTitle(testname);
        /*lvTest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(TestNoteActivity.this,TestPageActivity.class);
                intent.putExtra("testname",testname + " " + (i+1));
                startActivity(intent);
            }
        });*/

        MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.createDefaultNotesIfNeed();

        List<Note> list=  db.getAllNotes();
        this.noteList.addAll(list);
        this.listViewAdapter = new ArrayAdapter<Note>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, this.noteList);

        // Đăng ký Adapter cho ListView.
        this.lvTest.setAdapter(this.listViewAdapter);

        // Đăng ký Context menu cho ListView.
        registerForContextMenu(this.lvTest);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    public void onCreateContextMenu(ContextMenu menu, View view,
                                    ContextMenu.ContextMenuInfo menuInfo)    {

        super.onCreateContextMenu(menu, view, menuInfo);
        menu.setHeaderTitle("Select The Action");

        // groupId, itemId, order, title
        menu.add(0, MENU_ITEM_VIEW , 0, "View Note");
        menu.add(0, MENU_ITEM_CREATE , 1, "Create Note");
        menu.add(0, MENU_ITEM_EDIT , 2, "Edit Note");
        menu.add(0, MENU_ITEM_DELETE, 4, "Delete Note");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        //AdapterView.AdapterContextMenuInfo info =(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        AdapterView.AdapterContextMenuInfo
                info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Note selectedNote = (Note) this.lvTest.getItemAtPosition(info.position);

        if(item.getItemId() == MENU_ITEM_VIEW){// cái này chỉ show ra dưới dạng tông báo dnagj text, ko phải hiện lên layput.
            Toast.makeText(getApplicationContext(),selectedNote.getNoteContent(),Toast.LENGTH_LONG).show();
        }
        else if(item.getItemId() == MENU_ITEM_CREATE){// menu tạo thêm một item khác, hàm tạo nằm ở addedit.. cái này chỉ gọi thôi.
            Intent intent = new Intent(this, AddEditNoteActivity.class);

            // Start AddEditNoteActivity, có phản hồi.
            this.startActivityForResult(intent, MY_REQUEST_CODE);
        }
        else if(item.getItemId() == MENU_ITEM_EDIT ){// sửa một item nào đó, sau mỗi thao tác đều phải lưu lại bằng lệnh startActivity..
            Intent intent = new Intent(this, AddEditNoteActivity.class);
            intent.putExtra("note", selectedNote);

            // Start AddEditNoteActivity, có phản hồi.
            this.startActivityForResult(intent,MY_REQUEST_CODE);
        }
        else if(item.getItemId() == MENU_ITEM_DELETE){// Xóa Activity hiện thông báo có muốn xóa ko, nếu có thì thực thi deleteNote còn
            // Hỏi trước khi xóa.
            new AlertDialog.Builder(this)// hàm alert xuất hiện ra thông báo!
                    .setMessage(selectedNote.getNoteTitle()+". Are you sure to delete this item?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            deleteNote(selectedNote);
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }
        else {
            return false;
        }
        return true;
    }

    // Người dùng đồng ý xóa một Note.
    private void deleteNote(Note note)  {
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        db.deleteNote(note);
        this.noteList.remove(note);// xoa node sau đó reset lại database.
        // Refresh ListView., sau khi refest lại database vẫn phải refest listview.
        this.listViewAdapter.notifyDataSetChanged();
    }


    // Khi AddEditNoteActivity hoàn thành, nó gửi phản hồi lại.
    // (Nếu bạn đã start nó bằng cách sử dụng startActivityForResult()  )
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == MY_REQUEST_CODE ) {
            boolean needRefresh = data.getBooleanExtra("needRefresh",true);
            // Refresh ListView
            if(needRefresh) {
                this.noteList.clear();
                MyDatabaseHelper db = new MyDatabaseHelper(this);
                List<Note> list=  db.getAllNotes();
                this.noteList.addAll(list);
                // Thông báo dữ liệu thay đổi (Để refresh ListView).
                this.listViewAdapter.notifyDataSetChanged();
            }
        }
    }
}
