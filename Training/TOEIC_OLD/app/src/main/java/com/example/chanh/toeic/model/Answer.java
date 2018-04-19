package com.example.chanh.toeic.model;

public class Answer {
    String traloi;
    int vitri;

    public Answer(String traloi, int vitri) {
        this.traloi = traloi;
        this.vitri = vitri;
    }

    public String getTraloi() {
        return traloi;
    }

    public void setTraloi(String traloi) {
        this.traloi = traloi;
    }

    public int getVitri() {
        return vitri;
    }

    public void setVitri(int vitri) {
        this.vitri = vitri;
    }
}
