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
    public static final String COL_AGE = "age";
    public static final String COL_ADDRESS = "address";
    public static final String COL_PH_NUMBER = "phNumber";
    public static final String COL_CONTACT = "contacDetails";
    public static final String COL_CONTACT_METHOD = "contactMethod";
    public static final String COL_COLOR = "color";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "users.db", null, 1);
    }

    @SuppressLint("SQLiteString")
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + tableName + " (" + COL_USERNAME + " STRING PRIMARY KEY, " +
                                                                        COL_PASSWORD + " STRING, " +
                                                                        COL_NAME + " STRING, " +
                                                                        COL_AGE + " STRING, " +
                                                                        COL_ADDRESS + " STRING, " +
                                                                        COL_PH_NUMBER + " STRING," +
                                                                        COL_CONTACT + " STRING," +
                                                                        COL_CONTACT_METHOD + " STRING," +
                                                                        COL_COLOR + " STRING)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(String username, String password, String name, String age, String address, String phNumber, String contact, String method, String color){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_USERNAME, username);
        cv.put(COL_PASSWORD, password);
        cv.put(COL_NAME, name);
        cv.put(COL_AGE, age);
        cv.put(COL_ADDRESS, address);
        cv.put(COL_PH_NUMBER, phNumber);
        cv.put(COL_CONTACT, contact);
        cv.put(COL_CONTACT_METHOD, method);
        cv.put(COL_COLOR, color);

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
        String query = "UPDATE " + tableName + " SET " + col + " = '" + newString  + "' WHERE " + col + " = '" + oldString + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
    }

    public String[] getFromUsername(String username){
        String query = "SELECT " + COL_NAME + ", " + COL_AGE + ", " + COL_ADDRESS + ", " + COL_PH_NUMBER + ", " + COL_CONTACT + ", " + COL_CONTACT_METHOD + ", " + COL_COLOR + " FROM " + tableName + " WHERE " + COL_USERNAME + " = '" + username + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        String[] result = new String[7];
        if(cursor.moveToFirst() && cursor.getCount()>=1) {
            for (int i = 0; i <= 6; i++) {
                result[i] = cursor.getString(i);
            }
        }
        return result;
    }


}
