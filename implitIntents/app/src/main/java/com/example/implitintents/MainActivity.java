package com.example.implitintents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button callbtn;
Button smsbtn;
Button cambtn;
Button strgebtn;

public  static int REQUEST_CALL=1;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

callbtn = findViewById(R.id.button);
smsbtn=findViewById(R.id.smsbtn);
cambtn=findViewById(R.id.button3);

callbtn.setOnClickListener(new View.OnClickListener(){

    @Override
    public void onClick(View view) {
        makeCall();
    }
});

cambtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent camIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
       //Intent vedioIntent=new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
       //startActivity(vedioIntent);
        startActivity(camIntent);
    }
});
strgebtn=findViewById(R.id.opnstroge);
strgebtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
Intent strogeIntent=new Intent(Intent.ACTION_OPEN_DOCUMENT);
strogeIntent.addCategory(Intent.CATEGORY_OPENABLE);
strogeIntent.setType("*/*");
startActivity(strogeIntent);
    }
});
smsbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent smssend=new Intent(Intent.ACTION_VIEW,Uri.fromParts("sms","0773342225",null));
        smssend.putExtra("sms","Hello there!");
        startActivity(smssend);
    }
});



        }
    private void makeCall(){

       // Intent call=new Intent(Intent.ACTION_CALL);
        //call.setData(Uri.parse("tel: 0773342225"));
Intent callin=new Intent(Intent.ACTION_VIEW,Uri.fromParts("tel",  "0773342225",null));
        if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
        }else{
startActivity(callin);


        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


    }
}