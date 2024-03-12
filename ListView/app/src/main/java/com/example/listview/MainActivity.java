package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
public static final String Tag="MyView";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] data= {

                "kamal","Ashan","Anas","Hamdhan","Arkam","Hassan","Zakeeb"
        };

        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,data);

       ListView listView  =findViewById(R.id.list_view);
       listView.setAdapter(adapter);
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Log.i(Tag,"Clicked"+i+"id : "+l);
           }
       });


    }
}