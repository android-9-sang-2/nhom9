package com.example.chanh.vietnamese.data;

public class Question {
    public String selectedAnswer;
    private String index, question, A, B, C, correctAnswer;

    public Question() {
    }

    public Question(String index, String question, String a, String b, String c, String correctAnswer) {
        this.index = index;
        this.question = question;
        A = a;
        B = b;
        C = c;
        this.correctAnswer = correctAnswer;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
