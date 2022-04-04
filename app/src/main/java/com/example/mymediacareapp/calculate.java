package com.example.mymediacareapp;

/**
 * Class used to calculate risk levels of inputted information
 */
public class calculate {
    /**
     * Method for calculating the risk level of a temperature
     * @param result The inputted temperature
     * @return The risk level
     */
    public static String temp(float result){
        String riskLevel= "";
        if(result<=37){
            riskLevel = "Normal"; //Returns normal if the result is less than 37
        }
        else if(result>37 && result<=38){
            riskLevel = "Low Risk"; //returns low risk if the result is between 37 and 38
        }
        else{
            riskLevel = "High Risk"; //Returns high risk if the result is greater than 38
        }

        return riskLevel;
    }

    /**
     * Method for calculating the risk level of a blood pressure
     * @param result The inputted blood pressure
     * @return The risk level
     */
    public static String blood(String result){
        String riskLevel= "";
        //Splitting the reading into high and low values
        String[] parts = result.split("/");
        int lowBlood = Integer.parseInt(parts[0]);
        int highBlodd = Integer.parseInt(parts[1]);
        if(lowBlood>110 || highBlodd>180){
            riskLevel = "High Risk"; //Returns high risk if the low value is less than 110 or the high value is less than 180
        }
        else if((lowBlood>80 && lowBlood<110) || (highBlodd>120 && highBlodd<180)){
            riskLevel = "Low Risk"; //Returns low risk if the low value is between 80 and 110 or the high value is between 120 and 180
        }
        else{
            riskLevel = "Normal"; //Any other result returns normal
        }

        return riskLevel;
    }

    /**
     * Method for calculating the risk level of a heart rate
     * @param result The inputted heart rate
     * @return The risk level
     */
    public static String heart(int result){
        String riskLevel= "";
        if(result==72){
            riskLevel = "Normal"; //returns normal if the heart rate equals 72
        }
        else if(result<160){
            riskLevel = "Low Risk"; //Returns low risk if the result is les than 160
        }
        else{
            riskLevel = "High Risk"; //Returns high risk if the heart rate is greater than 160
        }

        return riskLevel;
    }
}
