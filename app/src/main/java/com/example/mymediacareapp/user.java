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

    public user(String[] userData) {
        this.name = userData[0];
        this.age = userData[1];
        this.address = userData[2];
        this.phNumber = userData[3];
        this.contactDetails = userData[4];
        this.contactMethod = userData[5];
        this.color = userData[6];
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newname, Context context) {
        DataBaseHelper db = new DataBaseHelper(context);
        db.updateString(newname,this.name,"name");
        this.name = newname;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String newAge, Context context) {
        DataBaseHelper db = new DataBaseHelper(context);
        db.updateString(newAge,this.age,"age");
        this.age = newAge;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String newAddress, Context context) {
        DataBaseHelper db = new DataBaseHelper(context);
        db.updateString(newAddress,this.address,"address");
        this.address = newAddress;
    }

    public String getPhNumber() {
        return this.phNumber;
    }

    public void setPhNumber(String newPhNumber, Context context
    ) {
        DataBaseHelper db = new DataBaseHelper(context);
        db.updateString(newPhNumber,this.phNumber,"phNumber");
        this.phNumber = newPhNumber;
    }

    public String getContactDetails() {
        return this.contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getContactMethod() {
        return this.contactMethod;
    }

    public void setContactMethod(String contactMethod) {
        this.contactMethod = contactMethod;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String newColor, Context context) {
        DataBaseHelper db = new DataBaseHelper(context);
        db.updateString(newColor,this.color,"color");
        this.color = newColor;
    }
}
