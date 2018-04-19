package com.example.chanh.toeic.model;

import java.io.Serializable;

public class QuestionGroup implements Serializable { // seria.. truyen doi tuong
    int indexPart;
    int indexTestSet;
    int indexQuestionGroup;
    String content;

    public int getIndexPart() {
        return indexPart;
    }

    public void setIndexPart(int indexPart) {
        this.indexPart = indexPart;
    }

    public int getIndexTestSet() {
        return indexTestSet;
    }

    public void setIndexTestSet(int indexTestSet) {
        this.indexTestSet = indexTestSet;
    }

    public int getIndexQuestionGroup() {
        return indexQuestionGroup;
    }

    public void setIndexQuestionGroup(int indexQuestionGroup) {
        this.indexQuestionGroup = indexQuestionGroup;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
