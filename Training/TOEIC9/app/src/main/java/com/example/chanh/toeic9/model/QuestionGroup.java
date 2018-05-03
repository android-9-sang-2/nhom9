package com.example.chanh.toeic9.model;

import java.io.Serializable;

//public class QuestionGroup implements Serializable { // seria.. truyen doi tuong
public class QuestionGroup{ // seria.. truyen doi tuong
    String indexPart;
    String indexTestSet;
    String indexQuestionGroup;
    String content;

    public QuestionGroup(String indexPart, String indexTestSet, String indexQuestionGroup, String content) {
        this.indexPart = indexPart;
        this.indexTestSet = indexTestSet;
        this.indexQuestionGroup = indexQuestionGroup;
        this.content = content;

    }

    public QuestionGroup() {
    }

    public String getIndexPart() {
        return indexPart;
    }

    public void setIndexPart(String indexPart) {
        this.indexPart = indexPart;
    }

    public String getIndexTestSet() {
        return indexTestSet;
    }

    public void setIndexTestSet(String indexTestSet) {
        this.indexTestSet = indexTestSet;
    }

    public String getIndexQuestionGroup() {
        return indexQuestionGroup;
    }

    public void setIndexQuestionGroup(String indexQuestionGroup) {
        this.indexQuestionGroup = indexQuestionGroup;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
