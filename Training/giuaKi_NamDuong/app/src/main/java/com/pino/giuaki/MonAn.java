package com.pino.giuaki;

public class MonAn {
    private int ID;
    private String Ten;
    private String Gia;
    private byte[] Hinh;

    public MonAn(int id, String ten, String gia, byte[] hinh) {
        ID = id;
        Ten = ten;
        Gia = gia;
        Hinh = hinh;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getGia() {
        return Gia;
    }

    public void setGia(String gia) {
        this.Gia = gia;
    }

    public byte[] getHinh() {
        return Hinh;
    }

    public void setHinh(byte[] hinh) {
        Hinh = hinh;
    }
}
