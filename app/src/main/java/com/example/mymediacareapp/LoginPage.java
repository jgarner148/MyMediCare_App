package com.example.mymediacareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        Button createAccountBtn = (Button)findViewById(R.id.LogInPageSignUpButton);
        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginPage.this, signUpPage.class));
            }
        });

        Button signInButton = (Button) findViewById(R.id.LogInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText usernameInput = (EditText) findViewById(R.id.logInUsernameInput);
                String inputtedUsername = usernameInput.getText().toString();

                EditText passwordInput = (EditText) findViewById(R.id.logInPasswordInput);
                String inputtedPassword = passwordInput.getText().toString();

                DataBaseHelper dataBaseHelper = new DataBaseHelper(LoginPage.this);
                if(inputtedUsername.equals("")){
                    Toast.makeText(LoginPage.this, "Please Enter a Username", Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean doesExist = dataBaseHelper.checkUsername(inputtedUsername);
                    if(doesExist){
                        //run check on password
                        boolean correctPassword = dataBaseHelper.checkPassword(inputtedUsername,inputtedPassword);
                        if(correctPassword){
                            startActivity(new Intent(LoginPage.this, homePage.class));
                        }
                        else{
                            Toast.makeText(LoginPage.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(LoginPage.this, "Invalid Username", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }


}