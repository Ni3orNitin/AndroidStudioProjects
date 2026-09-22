package com.nitin.registerandlogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {

    private static final String Database_name = "one.db";

    private static final int Database_version = 1;

    public DBhelper(@Nullable Context context) {
        super(context, Database_name, null, Database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       // String creatable = "create table user(id integer primary key autoincrement,name text,pass text,email text,number text)";
        //db.execSQL(creatable);
        db.execSQL("create table user(id integer primary key autoincrement,name text,pass text,email text,number text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists user");
        onCreate(db);

    }

    public void registerdb1(String name,String pass,String email,String number){
        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name",name);
        cv.put("pass",pass);
        cv.put("email",email);
        cv.put("number",number);
        db1.insert("user",null,cv);
        db1.close();

    }
}
