package com.example.chanh.toeic9.data;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.chanh.toeic9.model.Part;
import com.example.chanh.toeic9.model.Question;
import com.example.chanh.toeic9.model.QuestionGroup;
import com.example.chanh.toeic9.model.Score;
import com.example.chanh.toeic9.model.TestSet;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class DBManager extends SQLiteOpenHelper {
    DatabaseReference mData;
//    Part[] parts = new Part[7];
    //firebase

//    ArrayList<Part> parts = new ArrayList<Part>();

    //-----------

    public static final String DATABASE_NAME ="toeic";
    private static final String TABLE_NAME ="Parts";

    private Context context;
    public DBManager(Context context, String name) {
        super(context, name,null, 1);
        this.context = context;
    }
    public DBManager(Context context) {
        super(context, DATABASE_NAME,null, 1);
        this.context = context;
    }

    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        DatabaseReference mData;
        Log.d("toiday", "a");
        String sqlQuery="";
        //1. Tao bang Parts
        sqlQuery ="CREATE TABLE Part (indexPart TEXT NOT NULL, name TEXT NOT NULL, icon TEXT, PRIMARY KEY(indexPart))";
//        sqlQuery ="CREATE TABLE Parts (indexPart INTEGER NOT NULL, name TEXT NOT NULL, icon TEXT, PRIMARY KEY(indexPart))";
        db.execSQL(sqlQuery);
        //  2. Tao bang TestSet
        sqlQuery = "CREATE TABLE TestSet(indexPart TEXT NOT NULL, indexTestSet TEXT NOT NULL, audio TEXT ,PRIMARY KEY(indexTestSet,indexPart),FOREIGN KEY(indexPart) REFERENCES Part(indexPart))";
//        sqlQuery = "CREATE TABLE TestSet(indexPart INTEGER NOT NULL, indexTestSet INTEGER NOT NULL, audio TEXT ,PRIMARY KEY(indexTestSet,indexPart),FOREIGN KEY(indexPart) REFERENCES Part(indexPart))";
//        sqlQuery = "CREATE TABLE TestSet(ID_TestSet TEXT NOT NULL, index_number INTEGER NOT NULL, part INTEGER NOT NULL, audio_path TEXT, PRIMARY KEY(ID_TestSet), FOREIGN KEY(part) REFERENCES Parts(ID_Part));";
//        sqlQuery = "CREATE TABLE TestSet(ID_TestSet TEXT NOT NULL, index_number INTEGER NOT NULL, part INTEGER NOT NULL, audio_path TEXT, PRIMARY KEY(ID_TestSet), FOREIGN KEY(part) REFERENCES Parts(ID_Part));";
        db.execSQL(sqlQuery);
        //3. Tao bang QuestionGroup
        sqlQuery = "CREATE TABLE QuestionGroup (indexPart TEXT NOT NULL,indexTestSet TEXT NOT NULL,indexQuestionGroup TEXT NOT NULL,content TEXT,PRIMARY KEY(indexQuestionGroup,indexTestSet,indexPart))";
//        sqlQuery = "CREATE TABLE QuestionGroup (indexPart INTEGER NOT NULL,indexTestSet INTEGER NOT NULL,indexQuestionGroup INTEGER NOT NULL,content TEXT,PRIMARY KEY(indexQuestionGroup,indexTestSet,indexPart))";
//        sqlQuery = "CREATE TABLE QuestionGroup (ID_QuestionGroup INTEGER PRIMARY KEY AUTOINCREMENT, content TEXT, test_set TEXT, FOREIGN KEY(test_set) REFERENCES TestSet(ID_TestSet) )";
        db.execSQL(sqlQuery);
        //4. Tao bang Question
        sqlQuery = "CREATE TABLE Question (indexPart TEXT NOT NULL,indexTestSet TEXT NOT NULL,indexQuestionGroup TEXT NOT NULL,indexQuestion TEXT NOT NULL,contentQuestion TEXT,answerA TEXT NOT NULL,answerB TEXT NOT NULL,answerC TEXT NOT NULL,answerD TEXT,correctAnswer TEXT NOT NULL,image TEXT,note TEXT,PRIMARY KEY(indexPart,indexTestSet,indexQuestionGroup,indexQuestion))";
//        sqlQuery = "CREATE TABLE Question (indexPart INTEGER NOT NULL,indexTestSet INTEGER NOT NULL,indexQuestionGroup INTEGER NOT NULL,indexQuestion INTEGER NOT NULL,contentQuestion TEXT,A TEXT NOT NULL,B TEXT NOT NULL,C TEXT NOT NULL,D TEXT,correctAnswer TEXT NOT NULL,image TEXT,note TEXT,PRIMARY KEY(indexPart,indexTestSet,indexQuestionGroup,indexQuestion))";
        //sqlQuery = "CREATE TABLE Question(question TEXT, A TEXT, B TEXT, C TEXT, D TEXT, correct_answer TEXT, note TEXT, question_group TEXT NOT NULL, image_path TEXT, FOREIGN KEY(question_group) REFERENCES QuestionGroup(ID_QuestionGroup) )";
        //sqlQuery = "CREATE TABLE Question(question TEXT, A TEXT, B TEXT, C TEXT, D TEXT, correct_answer TEXT, note TEXT, question_group TEXT NOT NULL, image_path TEXT, FOREIGN KEY(question_group) REFERENCES QuestionGroup(ID_QuestionGroup) )";
        db.execSQL(sqlQuery);
        //lan dau tien cai dat thi ket noi den firebase
        //-Lay xuong so luong part, danh sach TestList
//        initIconPart();
//        UpdatePart(db,parts);
//        initDB(db);
        //Nguyendoanh Create Table Score
        sqlQuery = "CREATE TABLE Score (indexPart TEXT NOT NULL,indexTestSet TEXT NOT NULL, Score TEXT NOT NULL PRIMARY KEY(indexPart,indexTestSet,Score))";
        db.execSQL(sqlQuery);
        //
        mData = FirebaseDatabase.getInstance().getReference();
        Log.d("lan", "nay");
        mData.addValueEventListener(new ValueEventListener() {
            Part[] parts;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count = (int) dataSnapshot.child("part").getChildrenCount();
                parts = new Part[count];
                String path_to_icon;
                int i=0;
                //BANG PART
                for(DataSnapshot ds: dataSnapshot.child("part").getChildren()){
                    Part part = ds.getValue(Part.class); //buoc nay set duoc part_name va icon_pathONLINE de download thoi
//                    part.setId(Integer.valueOf(ds.getKey())); //buoc nay set duoc ID
                    path_to_icon = saveFile(part.getIcon(), "part" + String.valueOf(i+1) +".png", "image");  //buoc nay download
                    Log.d("pathtot", part.getIcon());
                    part.setIcon(path_to_icon);       //buoc nay set duoc part_name va icon_pathOFFLINE
                    parts[i]= part;

                    Log.d("thanhtam", part.getName());
                    i++;
                }
                UpdatePart(parts);

                //BANG TESTSET
                int count_testset = (int) dataSnapshot.child("testset").getChildrenCount();
                TestSet[] testSets = new TestSet[count_testset];
                i=0;
                String path_to_audio;
                for(DataSnapshot ds: dataSnapshot.child("testset").getChildren()){
                    TestSet testSet = ds.getValue(TestSet.class); //buoc nay set duoc part_name va icon_pathONLINE de download thoi
//                    part.setId(Integer.valueOf(ds.getKey())); //buoc nay set duoc ID
                    Log.d("cf",testSet.getAudio() );
                    if(!testSet.getAudio().toString().equalsIgnoreCase("")){
                        path_to_audio = saveFile(testSet.getAudio(), "Audio_testSet" + String.valueOf(i+1) +".mp3", "audio");  //buoc nay download
                        testSet.setAudio(path_to_audio);       //buoc nay set duoc part_name va icon_pathOFFLINE
                    }
                    testSets[i]= testSet;
                    i++;
                }
                UpdateTestSet(testSets);

                ///////HET BANG TESTSET

                //BANG QUESTIONGROUP
                int count_QUESTIONGROUP = (int) dataSnapshot.child("questiongroup").getChildrenCount();
                QuestionGroup[] questionGroups = new QuestionGroup[count_QUESTIONGROUP];
                i=0;
                for(DataSnapshot ds: dataSnapshot.child("questiongroup").getChildren()){
                    QuestionGroup questionGroup = ds.getValue(QuestionGroup.class); //buoc nay set duoc part_name va icon_pathONLINE de download thoi
//                    part.setId(Integer.valueOf(ds.getKey())); //buoc nay set duoc ID
//                    path_to_icon = saveImage(part.getIcon(), "part" + String.valueOf(i+1) +".png");  //buoc nay download
//                    part.setIcon(path_to_icon);       //buoc nay set duoc part_name va icon_pathOFFLINE
                    questionGroups[i]= questionGroup;
                    i++;
                }
                UpdateQuestionGroup(questionGroups);

                //HET BANG QUESTIONGROUP

                //BANG QUESTION
                int count_QUESTION = (int) dataSnapshot.child("question").getChildrenCount();
                Question[] questions= new Question[count_QUESTION];
                i=0;
                for(DataSnapshot ds: dataSnapshot.child("question").getChildren()){
                    Question question = ds.getValue(Question.class); //buoc nay set duoc part_name va icon_pathONLINE de download thoi
                    questions[i]= question;
                    i++;
                }
                UpdateQuestion(questions);
                //HET BANG QUESTION
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //tuong tu ben duoi
        Log.d("daiday", "dayladai");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public String saveFile(String path, String name, String fileType){ //save image from firebase to internal storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl(path);
        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir(fileType, Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory,name);
        String path_to_icon = mypath.getAbsolutePath();
        FileOutputStream fos = null;
        storageRef.getFile(mypath).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                Log.d("dai", taskSnapshot.toString());
            }
        });
        return path_to_icon;
    }
    public void UpdatePart(Part[] parts){
        SQLiteDatabase db = this.getWritableDatabase();
        for(Part p : parts){
            SQLiteStatement stmt = db.compileStatement("INSERT INTO Part (indexPart,name, icon) VALUES(?,?,?)");
            stmt.bindString(1, p.getIndexPart());
            stmt.bindString(2, p.getName());
            stmt.bindString(3, p.getIcon());
            stmt.execute();
        }
    }
    public void UpdateTestSet(TestSet[]testSets){
        SQLiteDatabase db = this.getWritableDatabase();
        for(TestSet ts : testSets){
            SQLiteStatement stmt = db.compileStatement("INSERT INTO TestSet (indexPart,indexTestSet, audio) VALUES(?,?,?)");
            stmt.bindString(1, ts.getIndexPart());
            stmt.bindString(2, ts.getIndexTestSet());
            stmt.bindString(3, ts.getAudio());
            stmt.execute();
        }
    }
    public void UpdateQuestionGroup(QuestionGroup[] questionGroups){
        SQLiteDatabase db = this.getWritableDatabase();
        for(QuestionGroup qg : questionGroups){
            SQLiteStatement stmt = db.compileStatement("INSERT INTO QuestionGroup (indexPart,indexTestSet, indexQuestionGroup, content) VALUES(?,?,?,?)");
            stmt.bindString(1, qg.getIndexPart());
            stmt.bindString(2, qg.getIndexTestSet());
            stmt.bindString(3, qg.getIndexQuestionGroup());
            stmt.bindString(4, qg.getContent());
            stmt.execute();
        }
    }
    public void UpdateQuestion(Question[] questions){
        SQLiteDatabase db = this.getWritableDatabase();
        for(Question q : questions){
            SQLiteStatement stmt = db.compileStatement("INSERT INTO Question VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
            stmt.bindString(1, q.getIndexPart());
            stmt.bindString(2, q.getIndexTestSet());
            stmt.bindString(3, q.getIndexQuestionGroup());
            stmt.bindString(4, q.getIndexQuestion());
            stmt.bindString(5, q.getContentQuestion());
            stmt.bindString(6, q.getAnswerA());
            stmt.bindString(7, q.getAnswerB());
            stmt.bindString(8, q.getAnswerC());
            stmt.bindString(9, q.getAnswerD());
            stmt.bindString(10, q.getCorrectAnswer());
            stmt.bindString(11, q.getImage());
            stmt.bindString(12, q.getNote());
            stmt.execute();
        }
    }
// Doanh dang o day
    public void InsertScore(String indexPart,String indexTestSet, String currentscore){ // NguyenDoanh
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCount= db.rawQuery("SELECT count(*),score from Score where indexPart=" + indexPart +" and indexTestSet = " + indexTestSet + " ", null);
        mCount.moveToFirst();
        int count= mCount.getInt(0);
        if(count == 0) {
            // Thuc hien insert vao bang neu chua co du lieu
            SQLiteStatement stmt = db.compileStatement("INSERT INTO Score (indexPart,indexTestSet, score) VALUES(?,?,?)");
            stmt.bindString(1, indexPart);
            stmt.bindString(2, indexTestSet);
            stmt.bindString(3, currentscore);
            stmt.execute();
        }
        else {
            int  score = Integer.valueOf(mCount.getString(1));
            int  currentscore1 = Integer.valueOf(currentscore);
            if(currentscore1 > score){
                SQLiteStatement stmt = db.compileStatement("UPDATE Score SET score = ? WHERE indexPart = ? AND indexTestSet = ? ");
                stmt.bindString(1, currentscore);
                stmt.bindString(2, indexPart);
                stmt.bindString(3, indexTestSet);
                stmt.execute();
            }
        }


    }





    //FUNCTION Lay danh sach cac Part --------> HomeActivity
    public Part[] getPartArray(){
        Log.d("getpart", "part");
        Part listPart[] = new Part[7];
        String selectQuery = "SELECT  * FROM Part";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        int i=0;
        if (cursor.moveToFirst()) {
            do {
                Part part = new Part();
                part.setIndexPart(cursor.getString(0));
                part.setName(cursor.getString(1));
                part.setIcon(cursor.getString(2));
                listPart[i] = part;
                i++;
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listPart;
    }
    public Part getPartDetail(String p){
        String selectQuery = "SELECT  * FROM Part WHERE indexPart=" + p;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Part part = new Part();
        if (cursor.moveToFirst()) {
            do {
                part.setIndexPart(cursor.getString(0));
                part.setName(cursor.getString(1));
                part.setIcon(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return part;
    }
    public TestSet[] getTestSetArray(String part){
        SQLiteDatabase db = this.getWritableDatabase();
//        testSets = new ArrayList<TestSet>();
        //dem so luong testset theo part
        Cursor mCount= db.rawQuery("SELECT count(*) from TestSet where indexPart=" + part, null);
        mCount.moveToFirst();
        int count= mCount.getInt(0);
        mCount.close();
        Log.d("VCC", String.valueOf(count));
        TestSet testSets[] = new TestSet[count];
        String selectQuery = "SELECT  * FROM TestSet WHERE indexPart="+part;
        Cursor cursor = db.rawQuery(selectQuery, null);
        int i=0;
        if (cursor.moveToFirst()) {
            do {
                TestSet testSet = new TestSet();
                testSet.setIndexPart(cursor.getString(0));
                testSet.setIndexTestSet(cursor.getString(1));
                testSet.setAudio(cursor.getString(2));
                testSets[i] = testSet;
                i++;
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return testSets;
    }
    public TestSet getTestSetDetail(String indexPart, String indexTestSet){
        String selectQuery = "SELECT  * FROM TestSet WHERE indexPart=" + indexPart + " AND indexTestSet="+ indexTestSet;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        TestSet testSet = new TestSet();
        if (cursor.moveToFirst()) {
            do {
                testSet.setIndexPart(cursor.getString(0));
                testSet.setIndexTestSet(cursor.getString(1));
                testSet.setAudio(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return testSet;
    }
    public int countQuestion(String indexPart, String indexTestSet ){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCount= db.rawQuery("SELECT count(*) from Question where indexPart=" + indexPart + " AND indexTestSet="+indexTestSet, null);
        mCount.moveToFirst();
        int count= mCount.getInt(0);
        mCount.close();
        db.close();
        return count;
    }
    public QuestionGroup[] getQuestionGroupArray(String indexPart, String indexTestSet){
        SQLiteDatabase db = this.getWritableDatabase();
//        testSets = new ArrayList<TestSet>();
        //dem so luong testset theo part
        Cursor mCount= db.rawQuery("SELECT count(*) from QuestionGroup where indexPart=" + indexPart + " AND indexTestSet=" + indexTestSet, null);
        mCount.moveToFirst();
        int count= mCount.getInt(0);
        mCount.close();
//        Log.d("VCC", String.valueOf(count));
        QuestionGroup questionGroups[] = new QuestionGroup[count];
        String selectQuery = "SELECT * from QuestionGroup where indexPart=" + indexPart + " AND indexTestSet=" + indexTestSet;
        Cursor cursor = db.rawQuery(selectQuery, null);
        int i=0;
        if (cursor.moveToFirst()) {
            do {
                QuestionGroup questiongroup = new QuestionGroup(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                );
//                testSet.setIndexPart(cursor.getString(0));
//                testSet.setIndexTestSet(cursor.getString(1));
//                testSet.setAudio(cursor.getString(2));
                questionGroups[i] = questiongroup;
                i++;
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return questionGroups;
    }

    public Question[] getQuestionArray(String indexPart, String indexTestSet, String indexQuestionGroup){
        SQLiteDatabase db = this.getWritableDatabase();
//        testSets = new ArrayList<TestSet>();
        //dem so luong testset theo part
        Cursor mCount= db.rawQuery("SELECT count(*) from Question where indexPart=" + indexPart + " AND indexTestSet=" + indexTestSet + " AND indexQuestionGroup=" + indexQuestionGroup, null);
        mCount.moveToFirst();
        int count= mCount.getInt(0);
        mCount.close();
//        Log.d("VCC", String.valueOf(count));
        Question questions[] = new Question[count];
        String selectQuery = "SELECT * from Question where indexPart=" + indexPart + " AND indexTestSet=" + indexTestSet + " AND indexQuestionGroup=" + indexQuestionGroup;
        Cursor cursor = db.rawQuery(selectQuery, null);
        int i=0;
        if (cursor.moveToFirst()) {
            do {
                Question question = new Question(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11)
                );
                questions[i] = question;
                i++;
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return questions;
    }
    public ArrayList<String> getCorrectAnswers(String indexPart, String indexTestSet){
        ArrayList<String> correctAnswers = new ArrayList<>();
        correctAnswers.add(""); //ko lay gia tri tai index 0
        SQLiteDatabase db = this.getWritableDatabase();

        String correct;

        String selectQuery = "SELECT correctAnswer from Question where indexPart=" + indexPart + " AND indexTestSet=" + indexTestSet;
        Cursor cursor = db.rawQuery(selectQuery, null);
        int i=0;
        if (cursor.moveToFirst()) {
            do {
                correct = cursor.getString(0);
                correctAnswers.add(correct);
                i++;
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return correctAnswers;
    }

}
