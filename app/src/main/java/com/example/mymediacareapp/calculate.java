package com.example.mymediacareapp;

public class calculate {
    public static String temp(int result){
        String riskLevel= "";
        if(result<=37){
            riskLevel = "Normal";
        }
        else if(result>37 && result<38){
            riskLevel = "Low Risk";
        }
        else{
            riskLevel = "High Risk";
        }

        return riskLevel;
    }

    public static String blood(String result){
        String riskLevel= "";
        String[] parts = riskLevel.split("/");
        int lowBlood = Integer.parseInt(parts[0]);
        int highBlodd = Integer.parseInt(parts[1]);
        if(lowBlood>110 || highBlodd>180){
            riskLevel = "High Risk";
        }
        else if((lowBlood>80 && lowBlood<110) || (highBlodd>120 && highBlodd<180)){
            riskLevel = "Low Risk";
        }
        else{
            riskLevel = "Normal";
        }

        return riskLevel;
    }

    public static String heart(int result){
        String riskLevel= "";
        if(result==72){
            riskLevel = "Normal";
        }
        else if(result<160){
            riskLevel = "Low Risk";
        }
        else{
            riskLevel = "High Risk";
        }

        return riskLevel;
    }
}
