package com.example.mymediacareapp;

import androidx.appcompat.app.AppCompatActivity;

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

    String contactPreference = "email";
    String contactDetails = "";
    boolean selectedContact = false;
    String phone;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        RadioGroup selectMethodGroup = findViewById(R.id.selectContactMethodGroup);
        selectMethodGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case R.id.emailRadio:
                        contactPreference = "email";
                        break;
                    case R.id.smsRadio:
                        contactPreference = "sms";
                        break;
                }
            }
        });

        Button contactButton = (Button)findViewById(R.id.selectContactButton);
        contactButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                contactDetails = "07951681608";
                selectedContact=true;
                //Intent in = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                //startActivityForResult(in, 1);
                contactButton.setText(R.string.changeContact);
                contactButton.setBackgroundResource(R.color.grey);
            }
        });


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

                if(contactPreference.equals("sms")){
                    contactDetails = phone;
                }
                else{
                    contactDetails = email;
                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(signUpPage.this);
                boolean doesExist = dataBaseHelper.checkUsername(inputtedUsername);
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
                    boolean success = dataBaseHelper.addOne(inputtedUsername, inputtedPassword, inputtedName, inputtedAge, inputtedAddress, inputtedPhonenum, contactDetails,contactPreference,"white");
                    if(success){
                        Toast.makeText(signUpPage.this, "Now Sign in", Toast.LENGTH_SHORT).show();
                        Toast.makeText(signUpPage.this, contactDetails, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(signUpPage.this, LoginPage.class));
                    }
                    else{
                        Toast.makeText(signUpPage.this, "Error when making account", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
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
    private void contactPicked(Intent data) {
        Cursor cursor;
        try {
            Uri uri = data.getData ();
            cursor = getContentResolver ().query (uri, null, null,null,null);
            cursor.moveToFirst ();
            int phoneIndex = cursor.getColumnIndex (ContactsContract.CommonDataKinds.Phone.NUMBER);
            int emailIndex = cursor.getColumnIndex (ContactsContract.CommonDataKinds.Email.ADDRESS);
            phone = cursor.getString (phoneIndex);
            email = cursor.getString (emailIndex);
            selectedContact = true;
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }
}