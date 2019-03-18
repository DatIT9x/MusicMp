package com.example.musicmp3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "THONGTIN.db";
    public static final String TABLE_NAME = "THONGTIN";
    public static final String COLUMN_1 = "email";
    public static final String COLUMN_2 = "pass";
    public static final String COLUMN_3 = "cpass";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_NAME + "(Email text primary key ,pass text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
//        onCreate(db);
    }

    public Boolean insert(String email, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("pass", pass);
//        contentValues.put("cpass", cpass);
        long id = db.insert("THONGTIN", null, contentValues);
        if(id==-1) return false;
        else return true;
    }




    //checking if email exists
    public Boolean checkemail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from THONGTIN where email=?",new String[]{email});
        if (cursor.getCount()>0) return false;
        else return true;

    }

    //checking email and password
    public Boolean emailpassword(String email,String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from THONGTIN where email=? and pass=?", new String[]{email,pass});
        if(cursor.getCount()>0) return true;
        else return false;
    }
}


