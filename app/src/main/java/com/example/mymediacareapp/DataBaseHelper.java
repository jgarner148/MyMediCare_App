package com.example.mymediacareapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    //Creating the title values for the Database
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

    /**
     * Constructor for the database helper
     * @param context Current activity
     */
    public DataBaseHelper(@Nullable Context context) {
        super(context, "users.db", null, 1);
    }

    /**
     * Method to create the database or check if the database exists
     * @param db The database
     */
    @SuppressLint("SQLiteString")
    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQL statement to be used to create the database
        String createTableStatement = "CREATE TABLE " + tableName + " (" + COL_USERNAME + " STRING PRIMARY KEY, " +
                                                                        COL_PASSWORD + " STRING, " +
                                                                        COL_NAME + " STRING, " +
                                                                        COL_AGE + " STRING, " +
                                                                        COL_ADDRESS + " STRING, " +
                                                                        COL_PH_NUMBER + " STRING," +
                                                                        COL_CONTACT + " STRING," +
                                                                        COL_CONTACT_METHOD + " STRING," +
                                                                        COL_COLOR + " STRING)";
        db.execSQL(createTableStatement); //Executing the SQL statement to create the database
    }

    /**
     * Method to upgrade the database
     * @param sqLiteDatabase The database
     * @param i int
     * @param i1 int
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * Method for adding a new row to the table
     * @param username username to be added
     * @param password password to be added
     * @param name name to be added
     * @param age age to be added
     * @param address address to be added
     * @param phNumber phone number to be added
     * @param contact contact to be added
     * @param method contact method to be added
     * @param color background colour to be added
     * @return boolean stating if the addition was successful or not
     */
    public boolean addOne(String username, String password, String name, String age, String address, String phNumber, String contact, String method, String color){
        SQLiteDatabase db = this.getWritableDatabase();//Getting the database
        ContentValues cv = new ContentValues(); //Setting up the content values

        //Putting all the taken in information into the content values
        cv.put(COL_USERNAME, username);
        cv.put(COL_PASSWORD, password);
        cv.put(COL_NAME, name);
        cv.put(COL_AGE, age);
        cv.put(COL_ADDRESS, address);
        cv.put(COL_PH_NUMBER, phNumber);
        cv.put(COL_CONTACT, contact);
        cv.put(COL_CONTACT_METHOD, method);
        cv.put(COL_COLOR, color);

        long insert = db.insert(tableName, null, cv); //Inserting the information into the database
        db.close();
        if(insert==-1){
            return false; //returns false if unsuccessful
        }
        else {
            return true; //returns true if successful
        }
    }

    /**
     * Method for checking if a given username exists
     * @param testingUsername username being checked
     * @return boolean true if username exists and false if it does not
     */
    public boolean checkUsername(String testingUsername){
        String query = "SELECT * FROM " + tableName + " WHERE " + COL_USERNAME + " = '" + testingUsername + "'"; //Query for checking for the username
        SQLiteDatabase db = this.getReadableDatabase(); //Getting the database
        Cursor cursor = db.rawQuery(query, null); //Running the query
        if(cursor.moveToFirst()){
            return true; //Returns true if it exists
        }
        else{
            return false; //Returns false if it doesn't exist
        }
    }

    /**
     * Method for checking if an inputted password is correct for the given username
     * @param testingUsername Inputted username
     * @param testingPassword Inputted password
     * @return boolean returns true if password is correct and false if it is not
     */
    public boolean checkPassword(String testingUsername, String testingPassword){
        String query = "SELECT " + COL_PASSWORD+  " FROM " + tableName + " WHERE " + COL_USERNAME + " = '" + testingUsername + "'"; //Query for checking password
        SQLiteDatabase db = this.getReadableDatabase(); //Getting the database
        Cursor cursor = db.rawQuery(query, null); //Running the query
        String userPassword = ""; //Declaring the user password String
        if(cursor.moveToFirst()){
            userPassword = cursor.getString(0); //Setting the correct password to the user password string
        }

        if (userPassword.equals(testingPassword)) {
            return true; //If they are the same true is returned
        }
        else{
            return false; //If they aren't the same false is returned
        }
    }

    /**
     * Method for updating a value in a selected column
     * @param newString The new string value
     * @param oldString Teh old string value
     * @param col The column being updated
     */
    public void updateString(String newString, String oldString, String col){
        String query = "UPDATE " + tableName + " SET " + col + " = '" + newString  + "' WHERE " + col + " = '" + oldString + "'";  //Query for updating the value
        SQLiteDatabase db = this.getWritableDatabase(); //Getting the database
        db.execSQL(query); //Running the query
    }

    /**
     * Method for getting all of an accounts information using a username
     * @param username the username of the account the information is being gotten from
     * @return A string array containing all teh users information
     */
    public String[] getFromUsername(String username){
        String query = "SELECT " + COL_NAME + ", " + COL_AGE + ", " + COL_ADDRESS + ", " + COL_PH_NUMBER + ", " + COL_CONTACT + ", " + COL_CONTACT_METHOD + ", " + COL_COLOR + " FROM " + tableName + " WHERE " + COL_USERNAME + " = '" + username + "'"; //The query for getting the account information
        SQLiteDatabase db = this.getReadableDatabase(); //Getting the database
        Cursor cursor = db.rawQuery(query, null); //Running the query
        String[] result = new String[7]; //Setting up array to be returned
        if(cursor.moveToFirst() && cursor.getCount()>=1) {//Checking there is information in the string
            //For loop that assigns all the information returned by the query into the array
            for (int i = 0; i <= 6; i++) {
                result[i] = cursor.getString(i);
            }
        }
        return result; //returning the array of information
    }


}
