package com.example.mymediacareapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class viewResult extends AppCompatActivity {
    //Declaring variables
    TableLayout currentTable;
    String background;
    String username;

    /**
     * This method is called when the activity is created
     * @param savedInstanceState the saved instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_result);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){ //Getting the background and username from the previous activity
            currentTable = (TableLayout)findViewById(R.id.viewResultTable);
            background = extras.getString("background");
            setBackground.table(background,currentTable);
            username = extras.getString("username");
        }

        //Getting the test information from the previous activity
        String result = extras.getString("userInput");
        String risk = extras.getString("riskLevel");
        String type = extras.getString("type");

        TextView resultText = (TextView) findViewById(R.id.resultText);
        resultText.setText(result);

        TextView riskText = (TextView) findViewById(R.id.riskText);
        riskText.setText(risk);

        if(risk.equals("High Risk")){ //Method for showing extra UI if the risk level is high
            Button contactButton = (Button) findViewById(R.id.contactButton);
            contactButton.setVisibility(View.VISIBLE);
            contactButton.setOnClickListener(new View.OnClickListener() {
                /**
                 * This method is called when the contact button is clicked
                 * @param view the view
                 */
                @SuppressLint("IntentReset")
                @Override
                public void onClick(View view) {
                    DataBaseHelper db = new DataBaseHelper(viewResult.this);
                    user currentUser = new user(db.getFromUsername(username));
                    String contactMethod = currentUser.getContactMethod();
                    String message="Hi, I recorded a high risk ";
                    if(type.equals("Temp")){
                        message = message + "temperature of " + result + " degrees in the MyMediCare app.";
                    }
                    else if(type.equals("Blood")){
                        message = message + "blood pressure of " + result + " in the MyMediCare app.";
                    }
                    else if(type.equals("Heart")){
                        message = message + "heart rate of " + result + " BPM in the MyMediCare app.";
                    }
                    if(contactMethod.equals("sms")){
                        //Method for sending SMS message
                        //Checks to see if permission is granted
                        if(ContextCompat.checkSelfPermission(viewResult.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
                            //Checks to see if permission denied
                            if (ActivityCompat.shouldShowRequestPermissionRationale(viewResult.this, Manifest.permission.SEND_SMS)){
                                Toast.makeText(viewResult.this, "Failed to send  SMS - Permission denied", Toast.LENGTH_SHORT).show(); }
                            else{
                                //Requesting permission
                                ActivityCompat.requestPermissions(viewResult.this, new String[]{Manifest.permission.SEND_SMS}, 0); }
                        }
                        try{
                            SmsManager smsManager = SmsManager.getDefault();
                            smsManager.sendTextMessage(currentUser.getContactDetails(), null, message, null, null);
                            Toast.makeText(viewResult.this, "Message sent as SMS", Toast.LENGTH_SHORT).show(); }
                        catch(java.lang.SecurityException ignored){ }
                    }
                    else{
                        //Method for sending email
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setData(Uri.parse("mailto:"));
                        emailIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{currentUser.getContactDetails()});
                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "MyMediCare high risk notification");
                        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
                        emailIntent.setType("message/rfc822");
                        startActivity(Intent.createChooser(emailIntent,"Choose email app"));
                    }
                }
            });
        }


        Button exit = (Button) findViewById(R.id.exitButton);
        exit.setOnClickListener(new View.OnClickListener() {
            /**
             * This method is called when the exit button is clicked
             * @param view the view
             */
            @Override
            public void onClick(View view) {
                Intent i = new Intent(viewResult.this, homePage.class);
                i.putExtra("background", background);
                i.putExtra("username",username);
                startActivity(i);
            }
        });
    }

    /**
     * This method is called when the back button is pressed
     */
    @Override
    public void onBackPressed() {
        Intent i = new Intent(viewResult.this, homePage.class);
        i.putExtra("background", background);
        i.putExtra("username", username);
        startActivity(i);
    }

}