package com.example.tiku8;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Sqlite extends SQLiteOpenHelper {
    public Sqlite(@Nullable Context context) {
        super(context, "user", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(id integer primary key autoincrement,name,age)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void add(Student student){
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("image",student.getImage());
        values.put("name",student.getName());
        values.put("age",student.getAge());
        writableDatabase.insert("user",null,values);

    }
    public void delete(String name){
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.delete("user","name=?",new String[]{name});


    }
    public void update(String name,String age){
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("age",age);
        writableDatabase.update("user",values,"name=?",new String[]{name});

    }

}
