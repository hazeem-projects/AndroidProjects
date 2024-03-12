package com.jiat.framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ImageView> imageViews = new ArrayList<>();
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViews.add(findViewById(R.id.imageView1));
        imageViews.add(findViewById(R.id.imageView2));
        imageViews.add(findViewById(R.id.imageView3));

        for(ImageView view : imageViews){
            view.setVisibility(View.INVISIBLE);
        }


        Button button_next = findViewById(R.id.button_next);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if (index < imageViews.size() -1){
                        imageViews.get(++index).setVisibility(View.VISIBLE);
                    }
                    for (int i = 0; i < imageViews.size(); i++){
                        if (i != index){
                            imageViews.get(i).setVisibility(View.INVISIBLE);
                        }
                    }

            }
        });

        Button button_previous = findViewById(R.id.button_previous);
        button_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index > 0){
                    imageViews.get(--index).setVisibility(View.VISIBLE);
                }
                for (int i = 0; i < imageViews.size(); i++){
                    if (i != index){
                        imageViews.get(i).setVisibility(View.INVISIBLE);
                    }
                }

            }
        });
    }
}