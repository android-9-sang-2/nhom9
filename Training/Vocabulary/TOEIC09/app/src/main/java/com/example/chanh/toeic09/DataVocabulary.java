package com.example.chanh.toeic09;

import android.provider.ContactsContract;

import java.util.ArrayList;

public class DataVocabulary {
    public static DataVocabulary data;
    ArrayList<Vocabulary> arrVoc;
    static {
        data = new DataVocabulary();
    }
    public static DataVocabulary getData() {return data;}
}
