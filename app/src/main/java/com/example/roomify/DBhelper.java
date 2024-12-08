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
        super(context, "Login.db", null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase Mydb) {
        Mydb.execSQL("create Table users(username Text PRIMARY KEY,password Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase Mydb, int oldVersion, int newVersion) {
        Mydb.execSQL("drop Table if exists users");
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
        Cursor cursor = Mydb.rawQuery("select * from users where username = ? and passwrod=? ", new String[]{username, password});
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }
}
