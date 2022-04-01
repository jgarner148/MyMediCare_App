package com.example.mymediacareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class settingsPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner backgroundSpinner;
    TableLayout settingsTable;
    String currentItem;
    String background;
    String username;
    user currentUser;
    String contactDetails;
    boolean selectedContact = false;
    String contactPreference ="sms";
    String phone;
    String email;
    private static final int RESULT_PICK_CONTACT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        //Getting data from the previous activity
        Bundle extras = getIntent().getExtras();
        settingsTable = (TableLayout)findViewById(R.id.settingsTable);
        DataBaseHelper db = new DataBaseHelper(settingsPage.this);
        if(extras!=null){
            background = extras.getString("background");
            setBackground.table(background,settingsTable);
            username = extras.getString("username");
            currentUser = new user(db.getFromUsername(username)); }

        //Setting up the spinner
        backgroundSpinner = findViewById(R.id.backgroundList);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.backgroundColours, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        backgroundSpinner.setAdapter(adapter);
        backgroundSpinner.setOnItemSelectedListener(this);

        //Setting spinner first value
        if(background.equals("blue")){
            backgroundSpinner.setSelection(1);
        }
        else if(background.equals("yellow")){
            backgroundSpinner.setSelection(2);
        }
        else if(background.equals("green")){
            backgroundSpinner.setSelection(3);
        }

        //Setting the text of each text view
        TextView nameText = (TextView)findViewById(R.id.nameText);
        nameText.setText("Name: " + currentUser.getName());

        TextView ageText = (TextView)findViewById(R.id.AgeText);
        ageText.setText("Age: " + currentUser.getAge());

        TextView addressText = (TextView)findViewById(R.id.addressText);
        addressText.setText("Address: " + currentUser.getAddress());

        TextView numberText = (TextView)findViewById(R.id.numberText);
        numberText.setText("Phone Number: " + currentUser.getPhNumber());


        //Setting click actions for the NAME row
        Button editName = (Button) findViewById(R.id.editButtonName);
        Button saveButtonName = (Button)findViewById(R.id.saveButtonName);
        EditText editTextName = (EditText) findViewById(R.id.editTextName);
        editName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameText.setVisibility(View.GONE);
                editName.setVisibility(View.GONE);
                saveButtonName.setVisibility(View.VISIBLE);
                editTextName.setVisibility(View.VISIBLE);
                editTextName.setText(currentUser.getName()); }
        });
        saveButtonName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputtedName = editTextName.getText().toString();
                if(!inputtedName.equals("")) {
                    currentUser.setName(inputtedName, settingsPage.this);
                    nameText.setVisibility(View.VISIBLE);
                    nameText.setText("Name: " + currentUser.getName());
                    editName.setVisibility(View.VISIBLE);
                    saveButtonName.setVisibility(View.GONE);
                    editTextName.setVisibility(View.GONE);
                }
                else{
                    Toast.makeText(settingsPage.this, "Input can't be blank", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Setting click actions for the AGE row
        Button editAgeButton = (Button) findViewById(R.id.editButtonAge);
        Button saveButtonAge = (Button) findViewById(R.id.saveButtonAge);
        EditText editTextAge = (EditText) findViewById(R.id.editTextAge);
        editAgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ageText.setVisibility(View.GONE);
                editAgeButton.setVisibility(View.GONE);
                saveButtonAge.setVisibility(View.VISIBLE);
                editTextAge.setVisibility(View.VISIBLE);
                editTextAge.setText(currentUser.getAge()); }
        });
        saveButtonAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputtedAge = editTextAge.getText().toString();
                if(!inputtedAge.equals("") && Integer.parseInt(inputtedAge)>=18){
                    currentUser.setAge(inputtedAge,settingsPage.this);
                    ageText.setVisibility(View.VISIBLE);
                    ageText.setText("Age: " + currentUser.getAge());
                    editAgeButton.setVisibility(View.VISIBLE);
                    saveButtonAge.setVisibility(View.GONE);
                    editTextAge.setVisibility(View.GONE);
                }
                else if(!inputtedAge.equals("") && Integer.parseInt(inputtedAge)<18){
                    Toast.makeText(settingsPage.this, "You must be over 18 to use this app due to GDPR", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(settingsPage.this, "Input can't be blank", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Setting click actions for the ADDRESS row
        Button editAddressButton = (Button) findViewById(R.id.editButtonAddress);
        Button saveButtonAddress = (Button) findViewById(R.id.saveButtonAddress);
        EditText editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        editAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addressText.setVisibility(View.GONE);
                editAddressButton.setVisibility(View.GONE);
                saveButtonAddress.setVisibility(View.VISIBLE);
                editTextAddress.setVisibility(View.VISIBLE);
                editTextAddress.setText(currentUser.getAddress());
            }
        });
        saveButtonAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputtedAddress = editTextAddress.getText().toString();
                if(!inputtedAddress.equals("")){
                    currentUser.setAddress(inputtedAddress,settingsPage.this);
                    addressText.setVisibility(View.VISIBLE);
                    addressText.setText("Address: " + currentUser.getAddress());
                    editAddressButton.setVisibility(View.VISIBLE);
                    saveButtonAddress.setVisibility(View.GONE);
                    editTextAddress.setVisibility(View.GONE);
                }
                else{
                    Toast.makeText(settingsPage.this, "Input can't be blank", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Setting click actions for the PHONE NUMBER row
        Button editPHButton = (Button) findViewById(R.id.editButtonNumber);
        Button saveButtonPH = (Button) findViewById(R.id.saveButtonPH);
        EditText editTextPH = (EditText) findViewById(R.id.editTextPH);
        editPHButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberText.setVisibility(View.GONE);
                editPHButton.setVisibility(View.GONE);
                saveButtonPH.setVisibility(View.VISIBLE);
                editTextPH.setVisibility(View.VISIBLE);
                editTextPH.setText(currentUser.getPhNumber());
            }
        });
        saveButtonPH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputtedNumber = editTextPH.getText().toString();
                if(!inputtedNumber.equals("")){
                    currentUser.setPhNumber(inputtedNumber, settingsPage.this);
                    numberText.setVisibility(View.VISIBLE);
                    numberText.setText("Phone Number: " + currentUser.getPhNumber());
                    editPHButton.setVisibility(View.VISIBLE);
                    saveButtonPH.setVisibility(View.GONE);
                    editTextPH.setVisibility(View.GONE);
                }
                else{
                    Toast.makeText(settingsPage.this, "Input can't be blank", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Setting up two large edit buttons
        Button editpasswordButton = (Button) findViewById(R.id.editPasswordButton);
        Button editContactButton = (Button) findViewById(R.id.editContactButton);


        //Setting up click actions for the EDIT PASSWORD rows
        Button savePasswordButton = (Button) findViewById(R.id.savePasswordButton);
        EditText editPasswordOldInput = (EditText) findViewById(R.id.editPasswordOldInput);
        EditText editPasswordNewInput = (EditText) findViewById(R.id.editPasswordNewInput);
        TextView editPasswordOldText = (TextView) findViewById(R.id.editPasswordOldText);
        TextView editPasswordNewText = (TextView) findViewById(R.id.editPasswordNewText);
        editpasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editpasswordButton.setVisibility(View.GONE);
                editContactButton.setVisibility(View.GONE);
                editPasswordOldInput.setVisibility(View.VISIBLE);
                editPasswordOldText.setVisibility(View.VISIBLE);
                editPasswordNewInput.setVisibility(View.VISIBLE);
                editPasswordNewText.setVisibility(View.VISIBLE);
                savePasswordButton.setVisibility(View.VISIBLE);
            }
        });
        savePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldPassword = editPasswordOldInput.getText().toString();
                String newPassword = editPasswordNewInput.getText().toString();

                //Check old password is correct
                DataBaseHelper dataBaseHelper = new DataBaseHelper(settingsPage.this);
                boolean correctPassword = dataBaseHelper.checkPassword(username,oldPassword);
                //Set old Password
                if(correctPassword && !oldPassword.equals("") && !newPassword.equals("") && !oldPassword.equals(newPassword)){
                    dataBaseHelper.updateString(newPassword,oldPassword,"password");
                    Toast.makeText(settingsPage.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                    editpasswordButton.setVisibility(View.VISIBLE);
                    editContactButton.setVisibility(View.VISIBLE);
                    editPasswordOldInput.setVisibility(View.GONE);
                    editPasswordOldText.setVisibility(View.GONE);
                    editPasswordNewInput.setVisibility(View.GONE);
                    editPasswordNewText.setVisibility(View.GONE);
                    savePasswordButton.setVisibility(View.GONE);
                }
                else if(oldPassword.equals(newPassword)){
                    Toast.makeText(settingsPage.this, "The new password can't be the same as the old password", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(settingsPage.this, "Inputs can't be blank", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //Setting up click actions for the CHANGE CONTACT rows
        RadioGroup newContactMethodGroup = (RadioGroup) findViewById(R.id.newContactMethodGroup);
        Button selectNewContactButton = (Button) findViewById(R.id.selectNewContactButton);
        Button saveNewContactButton = (Button) findViewById(R.id.saveNewContactButton);
        editContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editpasswordButton.setVisibility(View.GONE);
                editContactButton.setVisibility(View.GONE);
                selectNewContactButton.setVisibility(View.VISIBLE);
                newContactMethodGroup.setVisibility(View.VISIBLE);
                saveNewContactButton.setVisibility(View.VISIBLE);
            }
        });
        selectNewContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(in, RESULT_PICK_CONTACT);
            }
            /**
            @Override
            public void onClick(View view) {
                Intent in = new Intent (Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                if(contactPreference.equals("sms")){
                    pickContact=new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                }
                else{
                    pickContact=new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Email.CONTENT_URI);
                }
                startActivityForResult(in, 1);
                if(selectedContact){
                    selectNewContactButton.setText("Change");
                    selectNewContactButton.setBackgroundResource(R.color.grey);
                }
            }
            */
        });
        newContactMethodGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case R.id.emailNewRadio:
                        contactPreference = "email";
                        break;
                    case R.id.smsNewRadio:
                        contactPreference = "sms";
                        break;
                }
                if(selectedContact){
                    Toast.makeText(settingsPage.this, "Please reselect your contact", Toast.LENGTH_SHORT).show();
                }
            }
        });
        saveNewContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedContact){
                    if(contactPreference.equals("sms")){
                        currentUser.setContactDetails(phone,settingsPage.this);
                    }
                    else{
                        currentUser.setContactDetails(email,settingsPage.this);
                    }
                    currentUser.setContactMethod(contactPreference, settingsPage.this);
                    editpasswordButton.setVisibility(View.VISIBLE);
                    editContactButton.setVisibility(View.VISIBLE);
                    selectNewContactButton.setVisibility(View.GONE);
                    newContactMethodGroup.setVisibility(View.GONE);
                    saveNewContactButton.setVisibility(View.GONE);
                    Toast.makeText(settingsPage.this, "Contact Updated" + contactDetails, Toast.LENGTH_SHORT).show();
                    selectNewContactButton.setText("New Contact");
                    selectNewContactButton.setBackgroundResource(R.color.redred);
                }
                else{
                    Toast.makeText(settingsPage.this, "Make sure a contact has been selected", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        currentItem= backgroundSpinner.getSelectedItem().toString();
        if(currentItem.equals("White")) {
            settingsTable.setBackgroundResource(R.color.white);
            currentUser.setColor("white", settingsPage.this); }
        else if(currentItem.equals("Blue")) {
            settingsTable.setBackgroundResource(R.color.blue);
            currentUser.setColor("blue", settingsPage.this); }
        else if(currentItem.equals("Yellow")) {
            settingsTable.setBackgroundResource(R.color.yellow);
            currentUser.setColor("yellow", settingsPage.this); }
        else if(currentItem.equals("Green")) {
            settingsTable.setBackgroundResource(R.color.green);
            currentUser.setColor("green", settingsPage.this); }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) { }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(settingsPage.this, homePage.class);
        i.putExtra("background", currentUser.getColor());
        i.putExtra("username", username);
        startActivity(i);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RESULT_PICK_CONTACT:
                    contactPicked(data);
                    break;
            }
        } else {
            Toast.makeText(this, "Failed To pick contact", Toast.LENGTH_SHORT).show();
        }
    }
    private void contactPicked(Intent data) {
        Cursor cursor = null;
        try {
            String phoneNo = null;
            Uri uri = data.getData();
            cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

            phoneNo = cursor.getString(phoneIndex);
            phone = phoneNo;
            /**
            if(contactPreference.equals("sms")){
                int phoneIndex = cursor.getColumnIndex (ContactsContract.CommonDataKinds.Phone.NUMBER);
                phone = cursor.getString (phoneIndex);
            }
            else{
                int emailIndex = cursor.getColumnIndex (ContactsContract.CommonDataKinds.Email.ADDRESS);
                email = cursor.getString (emailIndex);
            }*/
            //selectedContact = true;
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed To pick contact", Toast.LENGTH_SHORT).show();
        }
    }

}