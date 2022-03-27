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
    TableLayout currentTable;
    String background = "white";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            currentTable = (TableLayout)findViewById(R.id.homepageTable);
            background = extras.getString("background");
            setBackground.table(background,currentTable);
        }


        Button SettingsButton = (Button)findViewById(R.id.settingsButton);
        SettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(homePage.this, settingsPage.class);
                i.putExtra("background", background);
                startActivity(i);
            }
        });

        Button resultButton = (Button) findViewById(R.id.goToEnterResultButton);
        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(homePage.this, enterResultPage.class);
                i.putExtra("background", background);
                startActivity(i);
            }
        });

        Button signOutButton = (Button) findViewById(R.id.signOutButton);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(homePage.this, LoginPage.class);
                i.putExtra("background", background);
                startActivity(i);
            }
        });
    }
}