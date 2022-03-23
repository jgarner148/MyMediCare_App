package com.example.mymediacareapp;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.Toast;

public class setBackground {

    public static void table(String background, TableLayout currentTable){
        if(background.equals("White")) {
            currentTable.setBackgroundResource(R.color.white);
        }
        else if(background.equals("Blue")) {
            currentTable.setBackgroundResource(R.color.blue);
        }
        else if(background.equals("Yellow")) {
            currentTable.setBackgroundResource(R.color.yellow);
        }
        else if(background.equals("Green")) {
            currentTable.setBackgroundResource(R.color.green);
        }

    }
}
