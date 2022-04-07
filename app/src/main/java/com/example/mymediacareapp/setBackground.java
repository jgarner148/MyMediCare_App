package com.example.mymediacareapp;

import android.widget.TableLayout;

/**
 * Class for setting the background of a table layout.
 */
public class setBackground {

    /**
     * Sets the background of a table layout.
     * @param background The background to set.
     * @param currentTable The table layout to set the background of.
     */
    public static void table(String background, TableLayout currentTable){
        if(background.equals("white")) {
            currentTable.setBackgroundResource(R.color.white); //Sets the background to white.
        }
        else if(background.equals("blue")) {
            currentTable.setBackgroundResource(R.color.blue); //Sets the background to blue.
        }
        else if(background.equals("yellow")) {
            currentTable.setBackgroundResource(R.color.yellow); //Sets the background to yellow.
        }
        else if(background.equals("green")) {
            currentTable.setBackgroundResource(R.color.green); //Sets the background to green.
        }

    }
}
