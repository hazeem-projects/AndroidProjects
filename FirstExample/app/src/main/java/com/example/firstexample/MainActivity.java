package com.example.firstexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Btnclick (View view){

EditText fname =findViewById(R.id.editTextTextPersonName);
        EditText lname = findViewById(R.id.editTextTextPersonName2);
    EditText email=findViewById(R.id.editTextTextPersonName3);

   TextView tfn= findViewById(R.id.textView2);
   TextView tln=findViewById(R.id.textView3);
   TextView tem=findViewById(R.id.textView4);

   tfn.setText("First Name : "+tfn);
   tln.setText("Last Name : "+tln);
   tem.setText("Email : "+tem);

    }
}