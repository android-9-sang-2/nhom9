package com.example.chanh.toeic09.model;

public class Vocabulary {
    private String word;
    private String mean;

    public Vocabulary() {
    }

    public Vocabulary(String word, String mean) {
        this.word = word;
        this.mean = mean;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

}
