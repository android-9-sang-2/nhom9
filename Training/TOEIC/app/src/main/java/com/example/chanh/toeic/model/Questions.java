package com.example.chanh.toeic.model;

/**
 * Created by Pino on 3/26/2018.
 */

public class Questions {
    int id;
    String cauHoi;
    String dapAn;
    String ans_A;
    String ans_B;
    String ans_C;
    String ans_D;
    int num_practic;
    String part;
    String img;
    String audio;
    String huongDan;
    String traloi=null;
    public String getHuongDan() {
        return huongDan;
    }

    public void setHuongDan(String huongDan) {
        this.huongDan = huongDan;
    }
    public String getTraloi() {
        return traloi;
    }

    public void setTraloi(String traloi) {
        this.traloi = traloi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCauHoi() {
        return cauHoi;
    }

    public void setCauHoi(String cauHoi) {
        this.cauHoi = cauHoi;
    }

    public String getDapAn() {
        return dapAn;
    }

    public void setDapAn(String dapAn) {
        this.dapAn = dapAn;
    }

    public String getAns_A() {
        return ans_A;
    }

    public void setAns_A(String ans_A) {
        this.ans_A = ans_A;
    }

    public String getAns_B() {
        return ans_B;
    }

    public void setAns_B(String ans_B) {
        this.ans_B = ans_B;
    }

    public String getAns_C() {
        return ans_C;
    }

    public void setAns_C(String ans_C) {
        this.ans_C = ans_C;
    }

    public String getAns_D() {
        return ans_D;
    }

    public void setAns_D(String ans_D) {
        this.ans_D = ans_D;
    }

    public int getNum_practic() {
        return num_practic;
    }

    public void setNum_practic(int num_practic) {
        this.num_practic = num_practic;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }



}
