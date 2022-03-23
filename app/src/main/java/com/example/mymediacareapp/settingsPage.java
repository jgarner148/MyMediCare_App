package com.example.mymediacareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;

public class settingsPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner backgroundSpinner;
    TableLayout settingsTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);
        backgroundSpinner = findViewById(R.id.backgroundList);
        settingsTable = (TableLayout) findViewById(R.id.settingsTable);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.backgroundColours, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        backgroundSpinner.setAdapter(adapter);
        backgroundSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String currentItem = backgroundSpinner.getSelectedItem().toString();
        Toast.makeText(settingsPage.this, currentItem, Toast.LENGTH_SHORT).show();
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
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}