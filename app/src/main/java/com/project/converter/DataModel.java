package com.project.converter;

public class DataModel {
    private String title;
//    private String desc;
    private int img;

    public DataModel(String title, int img) {
        this.title = title;
//        this.desc = desc;
        this.img = img;
    }


    public String getTitle() {
        return title;
    }

//    public String getDesc() {
//        return desc;
//    }

    public int getImg() {
        return img;
    }
}
