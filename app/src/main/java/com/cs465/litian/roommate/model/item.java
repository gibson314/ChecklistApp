package com.cs465.litian.roommate.model;

/**
 * Created by litia on 11/13/2016.
 */

public class item {
    private String name;
    private int status;
    private String category;

    public item(String name, String category, int status){
        this.name = name;
        this.status = status;
        this.category = category;
    }
    public String getName(){
        return name;
    }
    public int getStatus() {
        return status;
    }
    public String getCategory() {return category;}

}
