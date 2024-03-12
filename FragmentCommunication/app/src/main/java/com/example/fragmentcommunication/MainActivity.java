package com.example.fragmentcommunication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.SurfaceControl;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      View view = findViewById(R.id.button2);

      view.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
           FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        MyFragment1 myFragment1=new MyFragment1();
        MyFragment2 myFragment2=new MyFragment2();
fragmentTransaction.replace(R.id.container1,myFragment1,"t1");
              fragmentTransaction.replace(R.id.container2,myFragment2,"t2");
              fragmentTransaction.commit();

          }
      });

    }
}