package com.example.shared_preference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
private SharedPreferences sharedPreferences;
public static final String LOCAL_DATA="my_db";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      View view  =findViewById(R.id.button);

        TextView tv=findViewById(R.id.editTextTextPersonName);
sharedPreferences=getSharedPreferences(LOCAL_DATA, Context.MODE_PRIVATE);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
SharedPreferences.Editor edit =sharedPreferences.edit();
edit.putString("name",tv.getText().toString());
edit.apply();

                Map<String,?> all=sharedPreferences.getAll();
                Log.i("Shared", "onClick: Achintha");
            }
        });    }
}