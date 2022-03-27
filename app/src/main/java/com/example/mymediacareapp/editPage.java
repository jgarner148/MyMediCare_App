package com.example.mymediacareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class editPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_page);
        Bundle extras = getIntent().getExtras();
        String type="";
        String old="";
        if(extras!=null){
            type=extras.getString("Type");
        }

        Button editButtonSave = (Button)findViewById(R.id.editButtonSave);
        editButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView editInput = (TextView) findViewById(R.id.editInput);
                String input = editInput.getText().toString();
                DataBaseHelper db = new DataBaseHelper(editPage.this);
                db.updateString(input,old ,type);
            }
        });
    }
}