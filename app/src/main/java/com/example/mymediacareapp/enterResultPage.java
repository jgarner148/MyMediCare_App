package com.example.mymediacareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class enterResultPage extends AppCompatActivity {
    private RadioGroup radioGroup;
    private String selectedOption = "Temp";
    TableLayout currentTable;
    String background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_result_page);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            currentTable = (TableLayout)findViewById(R.id.enterResultTable);
            background = extras.getString("background");
            setBackground.table(background,currentTable);
        }

        TextView bloodInputHelp = (TextView) findViewById(R.id.inputHelpText);
        EditText userResultInput = (EditText) findViewById(R.id.userResultInput);

        Button calcButton = (Button) findViewById(R.id.calcButton);
        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userResult = userResultInput.getText().toString();
                if(userResult.equals("")){
                    Toast.makeText(enterResultPage.this, "Please make sure to enter a result first", Toast.LENGTH_SHORT).show();
                }
                else if(selectedOption.equals("Temp")){
                    String risk = calculate.temp(Integer.parseInt(userResult));
                    Intent i = new Intent(enterResultPage.this, viewResult.class);
                    i.putExtra("userInput", userResult);
                    i.putExtra("riskLevel", risk);
                    i.putExtra("background", background);
                    startActivity(i);
                }
                else if(selectedOption.equals("Blood")){
                    String risk = calculate.blood(userResult);
                    Intent i = new Intent(enterResultPage.this, viewResult.class);
                    i.putExtra("userInput", userResult);
                    i.putExtra("riskLevel", risk);
                    i.putExtra("background", background);
                    startActivity(i);
                }
                else if(selectedOption.equals("Heart")){
                    String risk = calculate.heart(Integer.parseInt(userResult));
                    Intent i = new Intent(enterResultPage.this, viewResult.class);
                    i.putExtra("userInput", userResult);
                    i.putExtra("riskLevel", risk);
                    i.putExtra("background", background);
                    startActivity(i);
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
                        bloodInputHelp.setVisibility(View.GONE);
                        break;
                    case R.id.bloodRadio:
                        selectedOption = "Blood";
                        bloodInputHelp.setVisibility(View.VISIBLE);
                        break;
                    case R.id.heartRadio:
                        selectedOption = "Heart";
                        bloodInputHelp.setVisibility(View.GONE);
                        break;
                }
            }
        });
    }
}