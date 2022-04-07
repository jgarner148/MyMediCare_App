package com.example.mymediacareapp;

import android.content.Context;

public class user {
    private String name;
    private String age;
    private String address;
    private String phNumber;
    private String contactDetails;
    private String contactMethod;
    private String color;

    /**
     * Constructor for user class
     * @param userData String array containing user data
     */
    public user(String[] userData) {
        this.name = userData[0];
        this.age = userData[1];
        this.address = userData[2];
        this.phNumber = userData[3];
        this.contactDetails = userData[4];
        this.contactMethod = userData[5];
        this.color = userData[6];
    }

    /**
     * Getter for name
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for name
     * @param newname new name
     * @param context context
     */
    public void setName(String newname, Context context, String currentUsername) {
        DataBaseHelper db = new DataBaseHelper(context);
        db.updateString(newname,this.name,"name", currentUsername);
        this.name = newname;
    }

    /**
     * Getter for age
     * @return age
     */
    public String getAge() {
        return this.age;
    }

    /**
     * Setter for age
     * @param newAge new age
     * @param context context
     */
    public void setAge(String newAge, Context context, String currentUsername) {
        DataBaseHelper db = new DataBaseHelper(context);
        db.updateString(newAge,this.age,"age", currentUsername);
        this.age = newAge;
    }

    /**
     * Getter for address
     * @return address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Setter for address
     * @param newAddress new address
     * @param context context
     */
    public void setAddress(String newAddress, Context context, String currentUsername) {
        DataBaseHelper db = new DataBaseHelper(context);
        db.updateString(newAddress,this.address,"address", currentUsername);
        this.address = newAddress;
    }

    /**
     * Getter for phone number
     * @return phone number
     */
    public String getPhNumber() {
        return this.phNumber;
    }

    /**
     * Setter for phone number
     * @param newPhNumber new phone number
     * @param context context
     */
    public void setPhNumber(String newPhNumber, Context context, String currentUsername) {
        DataBaseHelper db = new DataBaseHelper(context);
        db.updateString(newPhNumber,this.phNumber,"phNumber", currentUsername);
        this.phNumber = newPhNumber;
    }

    /**
     * Getter for contact details
     * @return contact details
     */
    public String getContactDetails() {
        return this.contactDetails;
    }

    /**
     * Setter for contact details
     * @param newContactDetails new contact details
     * @param context context
     */
    public void setContactDetails(String newContactDetails, Context context, String currentUsername) {
        DataBaseHelper db = new DataBaseHelper(context);
        db.updateString(newContactDetails,this.contactDetails,"contacDetails", currentUsername);
        this.contactDetails = newContactDetails;
    }

    /**
     * Getter for contact method
     * @return contact method
     */
    public String getContactMethod() {
        return this.contactMethod;
    }

    /**
     * Setter for contact method
     * @param newContactMethod new contact method
     * @param context context
     */
    public void setContactMethod(String newContactMethod, Context context, String currentUsername) {
        DataBaseHelper db = new DataBaseHelper(context);
        db.updateString(newContactMethod,this.contactMethod,"contactMethod", currentUsername);
        this.contactMethod = newContactMethod;
    }

    /**
     * Getter for color
     * @return color
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Setter for color
     * @param newColor new color
     * @param context context
     */
    public void setColor(String newColor, Context context, String currentUsername) {
        DataBaseHelper db = new DataBaseHelper(context);
        db.updateString(newColor,this.color,"color", currentUsername);
        this.color = newColor;
    }
}
