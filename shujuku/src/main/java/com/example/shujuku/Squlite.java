package com.example.shujuku;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Squlite extends SQLiteOpenHelper {
    public Squlite(@Nullable Context context) {
        super(context, "user", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(id integer primary key autoincrement,name,age,sex)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void add(Student student){
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",student.getName());
        values.put("age",student.getAge());
        values.put("sex",student.getSex());
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
    public void quary(Student student){
        SQLiteDatabase writableDatabase = getWritableDatabase();
        Cursor user = writableDatabase.query("user", null, null, null, null, null, null);
        ArrayList<Student> students = new ArrayList<>();
        while (user.moveToNext()){
            int name = user.getColumnIndex("name");
            int age = user.getColumnIndex("age");
            int sex = user.getColumnIndex("sex");

            students.add(new Student(name,age,sex));
        }

    }
}
