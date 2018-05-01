package com.example.chanh.toeic9.model;

public class Question {
    String indexPart;
    String indexTestSet;
    String indexQuestionGroup;
    String indexQuestion;
    String contentQuestion;
    String answerA;
    String answerB;
    String answerC;
    String answerD;
    String correctAnswer;
    String image;
    String note;

    public Question(String indexPart, String indexTestSet, String indexQuestionGroup, String indexQuestion, String contentQuestion, String answerA, String answerB, String answerC, String answerD, String correctAnswer, String image, String note) {
        this.indexPart = indexPart;
        this.indexTestSet = indexTestSet;
        this.indexQuestionGroup = indexQuestionGroup;
        this.indexQuestion = indexQuestion;
        this.contentQuestion = contentQuestion;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.correctAnswer = correctAnswer;
        this.image = image;
        this.note = note;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public String getIndexPart() {
        return indexPart;
    }

    public void setIndexPart(String indexPart) {
        this.indexPart = indexPart;
    }

    public String getIndexTestSet() {
        return indexTestSet;
    }

    public void setIndexTestSet(String indexTestSet) {
        this.indexTestSet = indexTestSet;
    }

    public String getIndexQuestionGroup() {
        return indexQuestionGroup;
    }

    public void setIndexQuestionGroup(String indexQuestionGroup) {
        this.indexQuestionGroup = indexQuestionGroup;
    }

    public String getIndexQuestion() {
        return indexQuestion;
    }

    public void setIndexQuestion(String indexQuestion) {
        this.indexQuestion = indexQuestion;
    }

    public String getContentQuestion() {
        return contentQuestion;
    }

    public void setContentQuestion(String contentQuestion) {
        this.contentQuestion = contentQuestion;
    }


    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
