package com.example.mymediacareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;

public class settingsPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner backgroundSpinner;
    TableLayout settingsTable;
    String currentItem;
    String background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);
        Bundle extras = getIntent().getExtras();
        settingsTable = (TableLayout)findViewById(R.id.settingsTable);
        if(extras!=null){
            background = extras.getString("background");
            setBackground.table(background,settingsTable);
        }

        backgroundSpinner = findViewById(R.id.backgroundList);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.backgroundColours, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        backgroundSpinner.setAdapter(adapter);
        backgroundSpinner.setOnItemSelectedListener(this);

        Button editName = (Button) findViewById(R.id.editButtonName);
        editName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(settingsPage.this, editPage.class);
                i.putExtra("Type", "name");
                startActivity(i);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        currentItem= backgroundSpinner.getSelectedItem().toString();
        if(currentItem.equals("White")) {
            settingsTable.setBackgroundResource(R.color.white);
        }
        else if(currentItem.equals("Blue")) {
            settingsTable.setBackgroundResource(R.color.blue);
        }
        else if(currentItem.equals("Yellow")) {
            settingsTable.setBackgroundResource(R.color.yellow);
        }
        else if(currentItem.equals("Green")) {
            settingsTable.setBackgroundResource(R.color.green);
        }

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent i = new Intent(settingsPage.this, homePage.class);
        i.putExtra("background", this.currentItem);
        startActivity(i);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}