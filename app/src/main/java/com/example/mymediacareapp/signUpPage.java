package com.example.mymediacareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class signUpPage extends AppCompatActivity {
    //declare variables
    String contactPreference = "email";
    String contactDetails = "";
    boolean selectedContact = false;
    String phone;
    String email;

    /**
     * Method called on creation of the activity
     * @param savedInstanceState - Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        //Getting the on screen elements from the layout
        Button contactButton = (Button)findViewById(R.id.selectContactButton);

        RadioGroup selectMethodGroup = findViewById(R.id.selectContactMethodGroup);
        selectMethodGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /**
             * Method called when the radio button is changed
             * @param radioGroup - RadioGroup
             * @param i - int
             */
            @SuppressLint("SetTextI18n")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){//switch statement to get the selected radio button
                    case R.id.emailRadio:
                        contactPreference = "email";
                        break;
                    case R.id.smsRadio:
                        contactPreference = "sms";
                        break;
                }
                if(selectedContact){
                    Toast.makeText(signUpPage.this, "Please reselect your contact", Toast.LENGTH_SHORT).show();
                    contactButton.setText("Select Contact");
                    contactButton.setBackgroundResource(R.color.redred);
                    //selectedContact=false;

                }
            }
        });


        contactButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Method called when the contact button is clicked
             * @param view - View
             */
            @Override
            public void onClick(View view) {
                Intent in;
                if(contactPreference.equals("sms")){
                    in=new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI); //Selecting the contact if the contact preference is sms
                }
                else{
                    in=new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Email.CONTENT_URI); //Selecting the contact if the contact preference is email
                }
                startActivityForResult(in, 1); //Getting the contact
                if(selectedContact){
                    contactButton.setText(R.string.change);
                    contactButton.setBackgroundResource(R.color.grey);
                }
            }
        });


        Button signUpButton = (Button)findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Method called when the sign up button is clicked
             * @param view - View
             */
            @Override
            public void onClick(View view) {
                boolean anyEmpty = false;
                //Getting the values of the input fields and checking if they are empty
                //Setting the boolean to true if any of the fields are empty
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

                if(contactPreference.equals("sms")){
                    contactDetails = phone;
                }
                else{
                    contactDetails = email;
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(signUpPage.this); //Creating a new database helper
                boolean doesExist = dataBaseHelper.checkUsername(inputtedUsername); //Checking if the username already exists
                if(anyEmpty){
                    Toast.makeText(signUpPage.this, "ERROR: Some input boxes blank", Toast.LENGTH_SHORT).show();
                }
                else if(!selectedContact){
                    Toast.makeText(signUpPage.this, "ERROR: Contact not selected", Toast.LENGTH_SHORT).show();
                }
                else if(doesExist){
                    Toast.makeText(signUpPage.this, "ERROR: Username already in use", Toast.LENGTH_SHORT).show();
                }
                else if(Integer.parseInt(inputtedAge)<18){
                    Toast.makeText(signUpPage.this, "You must be over 18 to use this app due to GDPR", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean success = dataBaseHelper.addOne(inputtedUsername, inputtedPassword, inputtedName, inputtedAge, inputtedAddress, inputtedPhonenum, contactDetails,contactPreference,"white"); //Adding the user to the database
                    if(success){
                        Toast.makeText(signUpPage.this, "Now Sign in", Toast.LENGTH_SHORT).show(); //If the user was successfully added to the database, then the user is prompted to sign in
                        startActivity(new Intent(signUpPage.this, LoginPage.class)); //Starting the login page
                    }
                    else{
                        Toast.makeText(signUpPage.this, "Error when making account", Toast.LENGTH_SHORT).show(); //If the user was not successfully added to the database, then the user is prompted to try again
                    }
                }

            }
        });

    }

    /**
     * This method is called when the user selects a contact from the contact list
     * @param requestCode The request code
     * @param resultCode The result code
     * @param data The data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                    contactPicked(data);
                    break; } }
        else {
            Toast.makeText(this, "Failed To pick contact", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     *Method getting the contact picked
     * @param data
     */
    private void contactPicked(Intent data) {
        Cursor cursor = null;
        try {
            String phoneNo = null;
            Uri uri = data.getData();
            cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            if(contactPreference.equals("sms")){
                int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER); //Getting the phone number if sms is selected
                phone = cursor.getString(phoneIndex);
            }
            else{
                int emailIndex = cursor.getColumnIndex (ContactsContract.CommonDataKinds.Email.ADDRESS); //Getting the email if email is selected
                email = cursor.getString (emailIndex);
            }
            selectedContact = true;
        } catch (Exception e) {
            Toast.makeText(this, "Failed To pick contact", Toast.LENGTH_SHORT).show(); //if the contact was not picked
        }
    }
}