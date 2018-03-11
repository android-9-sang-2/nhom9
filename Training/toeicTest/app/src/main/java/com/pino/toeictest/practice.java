package com.pino.toeictest;

/**
 * Created by Pino on 3/7/2018.
 */

public class practice {
    private String title;
    private String diem;
    private int hinh;

    public practice(String title, String diem, int hinh) {
        this.title = title;
        this.diem = diem;
        this.hinh = hinh;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDiem(String diem) {
        this.diem = diem;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }

    public String getTitle() {
        return title;
    }

    public String getDiem() {
        return diem;
    }

    public int getHinh() {
        return hinh;
    }
}
