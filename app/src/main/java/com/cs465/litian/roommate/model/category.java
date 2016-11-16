package com.cs465.litian.roommate.model;

/**
 * Created by litia on 11/15/2016.
 */

public class category {
    private String name;
    private int img;
    public category(String name, int img){
        this.name = name;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public int getImg() {
        return img;
    }
}
