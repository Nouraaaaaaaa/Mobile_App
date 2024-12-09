package com.example.roomify;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    public DBhelper( Context context) {
        super(context, "Login.db", null,2);

    }

    @Override
    public void onCreate(SQLiteDatabase Mydb) {
        Mydb.execSQL("create Table users(username Text PRIMARY KEY,password Text)");
        Mydb.execSQL("CREATE TABLE notes(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, des TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase Mydb, int oldVersion, int newVersion) {
        Mydb.execSQL("drop Table if exists users");
        Mydb.execSQL("DROP TABLE IF EXISTS notes");
        onCreate(Mydb);
    }
    public boolean insertData(String username , String password)
    {
        SQLiteDatabase Mydb =  this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long result = Mydb.insert("users", null, contentValues);
        if (result == -1)
        {
            return false;
        }
        else{
            return true;
        }
    }
    public boolean checkuser(String username)
    {
        SQLiteDatabase Mydb = this.getWritableDatabase();
        Cursor cursor = Mydb.rawQuery("select * from users where username = ?", new String[] {username});
        if(cursor.getCount() > 0)
        {
            return true;
        }
        return false;
    }
    public boolean checkPassword(String username,String password) {
        SQLiteDatabase Mydb = this.getWritableDatabase();
        Cursor cursor = Mydb.rawQuery("select * from users where username = ? and password =? ", new String[]{username, password});
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }
    public long insertNote(String title, String des) {
        SQLiteDatabase Mydb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("des", des);
        return Mydb.insert("notes", null, values);
    }
    public Cursor getNotes() {
        SQLiteDatabase Mydb = this.getReadableDatabase();
        return Mydb.rawQuery("SELECT * FROM notes", null);
    }
    public boolean deleteNote(int id) {
        SQLiteDatabase Mydb = this.getWritableDatabase();
        return Mydb.delete("notes", "id=?", new String[]{String.valueOf(id)}) > 0;
    }

    // Update a note by ID
    public boolean updateNote(int id, String newContent, String newDatetime) {
        SQLiteDatabase Mydb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("content", newContent);
        values.put("datetime", newDatetime);
        return Mydb.update("notes", values, "id=?", new String[]{String.valueOf(id)}) > 0;
    }
}
