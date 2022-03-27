package com.example.mymediacareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signUpPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        Button signUpButton = (Button)findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean anyEmpty = false;
                EditText usernameInput = (EditText) findViewById(R.id.usernameCreateInput);
                String inputtedUsername = usernameInput.getText().toString();
                if(inputtedUsername.equals("")){
                    anyEmpty=true;
                }

                EditText passwordInput = (EditText) findViewById(R.id.passwordCreateInput);
                String inputtedPassword = passwordInput.getText().toString();
                if(inputtedPassword.equals("")){
                    anyEmpty=true;
                }

                EditText nameInput = (EditText) findViewById(R.id.nameCreateInput);
                String inputtedName = nameInput.getText().toString();
                if(inputtedName.equals("")){
                    anyEmpty=true;
                }

                EditText ageInput = (EditText) findViewById(R.id.ageCreateInput);
                String inputtedAge = ageInput.getText().toString();
                if(inputtedAge.equals("")){
                    anyEmpty=true;
                }

                EditText addressInput = (EditText) findViewById(R.id.addressCreateInput);
                String inputtedAddress = addressInput.getText().toString();
                if(inputtedAddress.equals("")){
                    anyEmpty=true;
                }

                EditText phInput = (EditText) findViewById(R.id.phoneNumberCreateInput);
                String inputtedPhonenum = phInput.getText().toString();
                if(inputtedPhonenum.equals("")){
                    anyEmpty=true;
                }

                //Check to see if username is already in use
                DataBaseHelper dataBaseHelper = new DataBaseHelper(signUpPage.this);
                boolean doesExist = dataBaseHelper.checkUsername(inputtedUsername);
                if(anyEmpty){
                    Toast.makeText(signUpPage.this, "ERROR: Some input boxes blank", Toast.LENGTH_SHORT).show();
                }
                else if(doesExist){
                    Toast.makeText(signUpPage.this, "ERROR: Username already in use", Toast.LENGTH_SHORT).show();
                }
                else {
                    int inputtedAgeAsInt = Integer.parseInt(inputtedAge);
                    boolean success = dataBaseHelper.addOne(inputtedUsername, inputtedPassword, inputtedName, inputtedAgeAsInt, inputtedAddress, inputtedPhonenum);
                    Toast.makeText(signUpPage.this, "Success = " + success, Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}