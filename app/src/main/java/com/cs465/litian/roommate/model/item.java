package com.cs465.litian.roommate.model;

/**
 * Created by litia on 11/13/2016.
 */

public class item {
    private String name;
    private int status;

    public item(String name, int status){
        this.name = name;
        this.status = status;
    }
    public String getName(){
        return name;
    }
    public int getStatus() {
        return status;
    }
}
