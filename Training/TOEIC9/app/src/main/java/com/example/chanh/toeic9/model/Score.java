package com.example.chanh.toeic9.model;

/**
 * Created by nguyendoanh on 5/4/2018.
 */

public class Score {
    String indexPart;
    String indexTestSet;
    int Score;

    public Score(String indexPart, String indexTestSet, int score) {
        this.indexPart = indexPart;
        this.indexTestSet = indexTestSet;
        Score = score;
    }

    public String getIndexPart() {
        return indexPart;
    }

    public String getIndexTestSet() {
        return indexTestSet;
    }

    public int getScore() {
        return Score;
    }

    public void setIndexPart(String indexPart) {
        this.indexPart = indexPart;
    }

    public void setIndexTestSet(String indexTestSet) {
        this.indexTestSet = indexTestSet;
    }

    public void setScore(int score) {
        Score = score;
    }

    public Score() {

    }


}
