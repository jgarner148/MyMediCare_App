package com.example.mymediacareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class enterResultPage extends AppCompatActivity {
    private RadioGroup radioGroup;
    private String selectedOption = "Temp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_result_page);

        EditText userResultInput = (EditText) findViewById(R.id.userResultInput);

        Button calcButton = (Button) findViewById(R.id.calcButton);
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userResult = userResultInput.getText().toString();
                if(selectedOption.equals("Temp")){
                    String risk = calculate.temp(Integer.parseInt(userResult));
                    Intent i = new Intent(enterResultPage.this, viewResult.class);
                    i.putExtra("userInput", userResult);
                    i.putExtra("riskLevel", risk);
                    startActivity(i);
                    //Toast.makeText(enterResultPage.this, risk, Toast.LENGTH_SHORT).show();
                }
                else if(selectedOption.equals("Blood")){
                    String risk = calculate.blood(userResult);
                    Toast.makeText(enterResultPage.this, risk, Toast.LENGTH_SHORT).show();
                }
                else if(selectedOption.equals("Heart")){
                    String risk = calculate.heart(Integer.parseInt(userResult));
                    Toast.makeText(enterResultPage.this, risk, Toast.LENGTH_SHORT).show();
                }
            }
        });

        radioGroup = findViewById(R.id.testTypeRadioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.tempRadio:
                        selectedOption = "Temp";
                        break;
                    case R.id.bloodRadio:
                        selectedOption = "Blood";
                        break;
                    case R.id.heartRadio:
                        selectedOption = "Heart";
                        break;
                }
            }
        });
    }
}