package com.example.layout_flatter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LayoutInflater inflate=LayoutInflater.from(getApplicationContext());
        ViewGroup button=(ViewGroup) inflate.inflate(R.layout.custom_button_layout,null);

      TextView tv =button.findViewById(R.id.textView);
        tv.setText("My button");
ViewGroup frame=findViewById(R.id.frame);

frame.addView(button);

button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
     View customtoast =inflate.inflate(R.layout.custom_toast, null);
     TextView msg = (TextView) customtoast.findViewById(R.id.textView2);
        msg.setText("Hello This is custom toast");

        Toast t=new Toast(MainActivity.this);
t.setView(customtoast);
t.setDuration(t.LENGTH_LONG);
t.setGravity(Gravity.TOP,0,200);
t.show();
        //Toast toast= Toast.makeText(MainActivity.this,"Hello toast",Toast.LENGTH_SHORT);
    //toast.show();
    }
});
    }
}