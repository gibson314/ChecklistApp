package com.cs465.litian.roommate.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by litia on 11/14/2016.
 */

public class ItemDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "roommate.db";

    private static final String SQL_CREATE_LIST = "CREATE TABLE " + "LIST" + " (" +
            "itemID" + " INTEGER PRIMARY KEY AUTOINCREMENT" +
            ","+
            "itemName" + " TEXT"+
            ","+
            "itemCategory" + " TEXT" +
            ","+
            "itemStatus" + "TEXT" +
            " )";


    private static final String SQL_CREATE_CHORES = "CREATE TABLE " + "CHORES" + " (" +
            "itemID" + " INTEGER PRIMARY KEY AUTOINCREMENT" +
            ","+
            "itemName" + " TEXT"+
            ","+
            "personAssigned" + " TEXT" +

            " )";

    public ItemDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("SDF", SQL_CREATE_LIST);
        try{
            db.execSQL(SQL_CREATE_LIST);
            db.execSQL(SQL_CREATE_CHORES);
        }catch (Throwable e){
            e.printStackTrace();
        }
        Log.d("debug", "create table!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}