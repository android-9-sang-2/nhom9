package com.pino.toeictest;

/**
 * Created by Pino on 3/8/2018.
 */

public class testing {
    private String testname;
    private String tongdiem;
    private String listening;
    private String reading;

    public testing(String testname, String tongdiem, String listening, String reading) {
        this.testname = testname;
        this.tongdiem = tongdiem;
        this.listening = listening;
        this.reading = reading;
    }

    public String getTestname() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname = testname;
    }

    public String getTongdiem() {
        return tongdiem;
    }

    public void setTongdiem(String tongdiem) {
        this.tongdiem = tongdiem;
    }

    public String getListening() {
        return listening;
    }

    public void setListening(String listening) {
        this.listening = listening;
    }

    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }
}
