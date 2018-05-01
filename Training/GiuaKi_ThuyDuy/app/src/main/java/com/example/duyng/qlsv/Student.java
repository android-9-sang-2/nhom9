package com.example.duyng.qlsv;

public class Student {
    private int student_id;
    private String name;
    private String email;
    private int age;

    public Student(int student_id, String name, String email, int age) {
        this.student_id = student_id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public Student(String name, String email, int age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public Student() {
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
