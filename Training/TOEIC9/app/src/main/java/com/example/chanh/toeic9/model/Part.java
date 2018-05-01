package com.example.chanh.toeic9.model;

public class Part {
    private String name;
    private String icon;
    private String indexPart;

     //path to cc

    public Part() {
    }

    public Part(String indexPart, String name, String icon) {
        this.indexPart = indexPart;
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIndexPart() {
        return indexPart;
    }

    public void setIndexPart(String indexPart) {
        this.indexPart = indexPart;
    }

    //    @Override
//    public String toString() {
//        return "ID:" +getId() + " \n" +"Name: "+getName() +"\n" ;
//    }
}
