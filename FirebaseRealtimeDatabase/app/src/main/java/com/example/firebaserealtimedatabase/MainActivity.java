package com.example.firebaserealtimedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
public  static final String TAG_Firebase="FirebaseRealTimeDB";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference users  =database.getReference("users");

        DatabaseReference user1 = users.child("1");

        Map<String,String> user1Map=new HashMap<>();
        user1Map.put("name","A");
        user1Map.put("age","20");
        user1Map.put("address","colombo");
        user1.setValue(user1Map);
        DatabaseReference user2 = users.child("2");

        Map<String,String> user2Map=new HashMap<>();
        user2Map.put("name","B");
        user2Map.put("age","20");
        user2Map.put("address","kandy");

user2.setValue(user2Map);


//        View btn = findViewById(R.id.button);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                reference.child(reference.push().getKey()).child("ABC");
//            }
//        });


    }


}