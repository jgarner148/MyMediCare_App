package com.example.mymediacareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class homePage extends AppCompatActivity {
    //Setting up class variables
    TableLayout currentTable; //The on screen table layout
    String background; //The tables background colour
    String username = ""; //The current users username

    /**
     * Method called on activity creation
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Bundle extras = getIntent().getExtras();
        //If statement checking if there is information passed through from the previous activity
        if(extras!=null){
            currentTable = (TableLayout)findViewById(R.id.homepageTable);
            background = extras.getString("background"); //Setting the table background from the previous activity
            setBackground.table(background,currentTable);
            username=extras.getString("username"); //Setting the current username from the previous activity
        }

        //Getting on screen elements
        Button SettingsButton = (Button)findViewById(R.id.settingsButton);
        SettingsButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Method called when the settings button is pressed
             * @param view View
             */
            @Override
            public void onClick(View view) {
                //Assigning information to and then starting the settings page activity
                Intent i = new Intent(homePage.this, settingsPage.class);
                i.putExtra("background", background);
                i.putExtra("username", username);
                startActivity(i);
            }
        });

        Button resultButton = (Button) findViewById(R.id.goToEnterResultButton);
        resultButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Method called when the enter result button is pressed
             * @param view View
             */
            @Override
            public void onClick(View view) {
                //Assigning information to and then starting the enter result page activity
                Intent i = new Intent(homePage.this, enterResultPage.class);
                i.putExtra("background", background);
                i.putExtra("username", username);
                startActivity(i);
            }
        });

        Button signOutButton = (Button) findViewById(R.id.signOutButton);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Method called when the sign out button is pressed
             * @param view View
             */
            @Override
            public void onClick(View view) {
                //Starting the log in page activity
                Intent i = new Intent(homePage.this, LoginPage.class);
                startActivity(i);
            }
        });
    }

    /**
     * Method called when the user presses the android back button
     */
    @Override
    public void onBackPressed() {
        Toast.makeText(homePage.this, "Press SIGN OUT to exit application", Toast.LENGTH_SHORT).show();
    }
}