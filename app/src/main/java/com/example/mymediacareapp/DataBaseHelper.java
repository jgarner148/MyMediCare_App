package com.example.mymediacareapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    
    public static final String tableName = "USERS_TABLE";
    public static final String COL_NAME = "name";
    public static final String COL_USERNAME = "Username";
    public static final String COL_PASSWORD = "password";
    public static final String COL_ID = "ID";
    public static final String COL_AGE = "age";
    public static final String COL_ADDRESS = "address";
    public static final String COL_PH_NUMBER = "phNumber";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "users.db", null, 1);
    }

    @SuppressLint("SQLiteString")
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + tableName + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                                        COL_USERNAME + " STRING, " +
                                                                        COL_PASSWORD + " STRING, " +
                                                                        COL_NAME + " STRING, " +
                                                                        COL_AGE + " INT, " +
                                                                        COL_ADDRESS + " STRING, " +
                                                                        COL_PH_NUMBER + " STRING)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(String username, String password, String name, int age, String address, String phNumber){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_USERNAME, username);
        cv.put(COL_PASSWORD, password);
        cv.put(COL_NAME, name);
        cv.put(COL_AGE, age);
        cv.put(COL_ADDRESS, address);
        cv.put(COL_PH_NUMBER,phNumber);

        long insert = db.insert(tableName, null, cv);
        db.close();
        if(insert==-1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean checkUsername(String testingUsername){
        String query = "SELECT * FROM " + tableName + " WHERE " + COL_USERNAME + " = '" + testingUsername + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkPassword(String testingUsername, String testingPassword){
        String query = "SELECT " + COL_PASSWORD+  " FROM " + tableName + " WHERE " + COL_USERNAME + " = '" + testingUsername + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        String userPassword = "";
        if(cursor.moveToFirst()){
            userPassword = cursor.getString(0);
        }

        if (userPassword.equals(testingPassword)) {
            return true;
        }
        else{
            return false;
        }
    }

    public void updateString(String newString, String oldString, String col){
        String query = "UPDATE " + tableName + " SET " + COL_NAME + " = '" + newString  + "' WHERE " + col + " = '" + oldString + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
    }


}
