package com.example.mymediacareapp;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.Toast;

public class setBackground {

    public static void table(String background, TableLayout currentTable){
        if(background.equals("white")) {
            currentTable.setBackgroundResource(R.color.white);
        }
        else if(background.equals("blue")) {
            currentTable.setBackgroundResource(R.color.blue);
        }
        else if(background.equals("yellow")) {
            currentTable.setBackgroundResource(R.color.yellow);
        }
        else if(background.equals("green")) {
            currentTable.setBackgroundResource(R.color.green);
        }

    }
}
