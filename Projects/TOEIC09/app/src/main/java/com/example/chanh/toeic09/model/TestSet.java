package com.example.chanh.toeic09.model;

import java.io.Serializable;

public class TestSet implements Serializable {
    String indexPart;
    String indexTestSet;
    String audio;

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

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }
}

