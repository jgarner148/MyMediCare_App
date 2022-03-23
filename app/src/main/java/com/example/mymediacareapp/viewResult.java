package com.example.mymediacareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class viewResult extends AppCompatActivity {
    TableLayout currentTable;
    String background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_result);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            currentTable = (TableLayout)findViewById(R.id.viewResultTable);
            background = extras.getString("background");
            setBackground.table(background,currentTable);
        }

        String result = extras.getString("userInput");
        String risk = extras.getString("riskLevel");

        TextView resultText = (TextView) findViewById(R.id.resultText);
        resultText.setText(result);

        TextView riskText = (TextView) findViewById(R.id.riskText);
        riskText.setText(risk);

        if(risk.equals("High Risk")){
            Button contactButton = (Button) findViewById(R.id.contactButton);
            contactButton.setVisibility(View.VISIBLE);
        }

        Button exit = (Button) findViewById(R.id.exitButton);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(viewResult.this, homePage.class);
                i.putExtra("background", background);
                startActivity(i);
            }
        });
    }

}