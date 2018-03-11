package com.example.vchnhi.demo.model;

/**
 * Created by Võ Chánh Đại on 005, 05-02-2018.
 */

public class Person {
    //1.khai bai bien
    private String hoTen, diaChi, tinhTrang;
    //constructor
    public Person() {
    }

    public Person(String hoTen, String diaChi, String tinhTrang) {
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.tinhTrang = tinhTrang;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    @Override
    public String toString() {
        return "Person{" +
                "hoTen='" + hoTen + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", tinhTrang='" + tinhTrang + '\'' +
                '}';
    }
}
