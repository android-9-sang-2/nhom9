package com.example.chanh.toeic.Tips;

public class Tips {
    private int id;
    private int indexPart;
    private String  titleTip;
    private String contentTip;

    public Tips(int id, int indexPart, String titleTip, String contentTip) {
        this.id = id;
        this.indexPart = indexPart;
        this.titleTip = titleTip;
        this.contentTip = contentTip;
    }

    public Tips(int indexPart, String titleTip, String contentTip) {
        this.indexPart = indexPart;
        this.titleTip = titleTip;
        this.contentTip = contentTip;
    }

    public Tips() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndexPart() {
        return indexPart;
    }

    public void setIndexPart(int indexPart) {
        this.indexPart = indexPart;
    }

    public String getTitleTip() {
        return titleTip;
    }

    public void setTitleTip(String titleTip) {
        this.titleTip = titleTip;
    }

    public String getContentTip() {
        return contentTip;
    }

    public void setContentTip(String contentTip) {
        this.contentTip = contentTip;
    }
}
