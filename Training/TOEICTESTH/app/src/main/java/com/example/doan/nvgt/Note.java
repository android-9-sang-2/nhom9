package com.example.doan.nvgt;

/**
 * Created by Doan on 4/3/2018.
 */

import java.io.Serializable;

// class khai báo các thuộc tính cần của một lớp đôi tượng cần tác động, mỗi đối tượng trong csdl cần có 1 class tương tự
public class Note implements Serializable {

    private int noteId;
    private String noteTitle;
    private String noteContent;

    public Note()  {

    }

    public Note(  String noteTitle, String noteContent) {
        this.noteTitle= noteTitle;
        this.noteContent= noteContent;
    }

    public Note(int noteId, String noteTitle, String noteContent) {
        this.noteId= noteId;
        this.noteTitle= noteTitle;
        this.noteContent= noteContent;
    }

    public int getNoteId() {
        return noteId;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }
    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }


    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }


    @Override
    public String toString()  {
        return this.noteTitle;
    }

}