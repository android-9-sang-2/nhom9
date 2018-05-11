package com.example.chanh.toeic09.model;

import java.io.Serializable;

public class Vocabulary implements Serializable{
    private String word;
    private String mean;
    private String sentences;
    private String meanSentences;
    public String flag;

    public Vocabulary() {
    }

    public Vocabulary(String word, String mean, String sentences, String meanSentences) {
        this.word = word;
        this.mean = mean;
        this.sentences = sentences;
        this.meanSentences = meanSentences;
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

    public String getSentences() {
        return sentences;
    }

    public void setSentences(String sentences) {
        this.sentences = sentences;
    }

    public String getMeanSentences() {
        return meanSentences;
    }

    public void setMeanSentences(String meanSentences) {
        this.meanSentences = meanSentences;
    }
}
