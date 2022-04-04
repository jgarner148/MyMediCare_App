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
    //Setting up class variables
    private RadioGroup radioGroup; //The on screen radio group for selecting result type
    private String selectedOption = "Temp"; //The currently selected option in the radio group
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
        setContentView(R.layout.activity_enter_result_page);
        Bundle extras = getIntent().getExtras();
        //If statement checking if there is information passed through from the previous activity
        if(extras!=null){
            currentTable = (TableLayout)findViewById(R.id.enterResultTable);
            background = extras.getString("background"); //Setting the table background from the previous activity
            setBackground.table(background,currentTable);
            username=extras.getString("username"); //Setting the current username from the previous activity
        }

        //Getting on screen elements
        TextView bloodInputHelp = (TextView) findViewById(R.id.inputHelpText);
        EditText userResultInput = (EditText) findViewById(R.id.userResultInput);

        Button calcButton = (Button) findViewById(R.id.calcButton);
        calcButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Method called when the calculate button is pressed
             * @param view View
             */
            @Override
            public void onClick(View view) {
                String userResult = userResultInput.getText().toString();//Getting the inputted information
                //Passing background, username and user input data to the next activity
                Intent i = new Intent(enterResultPage.this, viewResult.class);
                i.putExtra("background", background);
                i.putExtra("username",username);
                i.putExtra("userInput", userResult);
                if(userResult.equals("")){ //Checking the user has inputted a result
                    Toast.makeText(enterResultPage.this, "Please make sure to enter a result first", Toast.LENGTH_SHORT).show();
                }
                else if(selectedOption.equals("Temp")){//Method when the user inputs the temperature result
                    String risk = calculate.temp(Float.parseFloat(userResult)); //Calculating risk
                    //Passing risk information to the next activity
                    i.putExtra("riskLevel", risk);
                    i.putExtra("type","Temp");
                    startActivity(i);
                }
                else if(selectedOption.equals("Blood")){ //Method when the user inputs a blood pressure result
                    String risk = calculate.blood(userResult);//Calculating risk
                    //Passing risk information to the next activity
                    i.putExtra("riskLevel", risk);
                    i.putExtra("type","Blood");
                    startActivity(i);
                }
                else if(selectedOption.equals("Heart")){//Method when the user inputs a heart rate result
                    String risk = calculate.heart(Integer.parseInt(userResult));//Calculating risk
                    //Passing risk information to the next activity
                    i.putExtra("riskLevel", risk);
                    i.putExtra("type","Heart");
                    startActivity(i);
                }
            }
        });

        radioGroup = findViewById(R.id.testTypeRadioGroup); //Getting on screen radio group
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /**
             * Method called when the user clicks a radio button
             * @param radioGroup The radio group
             * @param i Int
             */
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.tempRadio: //Method when the temp button is selected
                        selectedOption = "Temp";
                        bloodInputHelp.setVisibility(View.GONE); //Hiding blood pressure tip
                        break;
                    case R.id.bloodRadio: //Method when the blood pressure button is selected
                        selectedOption = "Blood";
                        bloodInputHelp.setVisibility(View.VISIBLE); //Showing blood pressure tip
                        break;
                    case R.id.heartRadio: //Method when the heart rate button is selected
                        selectedOption = "Heart";
                        bloodInputHelp.setVisibility(View.GONE); //Hiding blood pressure tip
                        break;
                }
            }
        });
    }

    /**
     * Method called when the user presses the android back button
     */
    @Override
    public void onBackPressed() {
        Intent i = new Intent(enterResultPage.this, homePage.class);
        i.putExtra("background", background);
        i.putExtra("username", username);
        startActivity(i);
    }

}