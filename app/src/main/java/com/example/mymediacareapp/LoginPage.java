package com.example.mymediacareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    /**
     * Method called when the activity is created
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

       //Getting the button from the layout
        Button createAccountBtn = (Button)findViewById(R.id.LogInPageSignUpButton);
        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Method called when the button is clicked
             * @param view the view
             */
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginPage.this, signUpPage.class)); //Starting the sign up page
            }
        });

        //Getting the button from the layout
        Button signInButton = (Button) findViewById(R.id.LogInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Method called when the button is clicked
             * @param view the view
             */
            @Override
            public void onClick(View view) {
                //Getting the on screen elements from the layout
                EditText usernameInput = (EditText) findViewById(R.id.logInUsernameInput);
                String inputtedUsername = usernameInput.getText().toString();

                EditText passwordInput = (EditText) findViewById(R.id.logInPasswordInput);
                String inputtedPassword = passwordInput.getText().toString();

                DataBaseHelper dataBaseHelper = new DataBaseHelper(LoginPage.this);//Creating a new database helper
                if(inputtedUsername.equals("")){
                    Toast.makeText(LoginPage.this, "Please Enter a Username", Toast.LENGTH_SHORT).show();//Showing a toast if the user has not entered a username
                }
                else{
                    boolean doesExist = dataBaseHelper.checkUsername(inputtedUsername);
                    if(doesExist){
                        //run check on password
                        boolean correctPassword = dataBaseHelper.checkPassword(inputtedUsername,inputtedPassword);
                        if(correctPassword){
                            String[] result = dataBaseHelper.getFromUsername(inputtedUsername);
                            String background = result[6];  //Getting the background from the inputted username
                            //Creating a new intent to start the home page
                            Intent i = new Intent(LoginPage.this, homePage.class);
                            i.putExtra("username",inputtedUsername); //Putting the username into the intent
                            i.putExtra("background",background); //Putting the background into the intent
                            startActivity(i); //Starting the home page
                        }
                        else{
                            Toast.makeText(LoginPage.this, "Invalid Password", Toast.LENGTH_SHORT).show(); //Showing a toast if the password is incorrect
                        }
                    }
                    else{
                        Toast.makeText(LoginPage.this, "Invalid Username", Toast.LENGTH_SHORT).show(); //Showing a toast if the username is incorrect
                    }
                }

            }
        });
    }

    public void onBackPressed() {
        finishAffinity();//Closes the app
    }


}