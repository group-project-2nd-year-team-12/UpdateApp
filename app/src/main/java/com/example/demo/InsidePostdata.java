package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class InsidePostdata extends AppCompatActivity {
TextView lane,categery,girlsBoys,city;
ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_postdata);

        lane=findViewById(R.id.lane);
        categery=findViewById(R.id.categery);
        girlsBoys=findViewById(R.id.girlsBoys);
        city=findViewById(R.id.city);

      // image=findViewById(R.id.image);
        Intent intent=getIntent();
        lane.setText(intent.getStringExtra("lane"));
        categery.setText(intent.getStringExtra("categery"));
        city.setText(intent.getStringExtra("city"));
        girlsBoys.setText(intent.getStringExtra("girlsBoys"));

    //  image.setImageResource(intent.getIntExtra("image",0));
    }
}