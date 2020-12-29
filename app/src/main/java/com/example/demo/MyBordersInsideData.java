package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MyBordersInsideData extends AppCompatActivity {

TextView textView;
    String emailBoarder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_borders_inside_data);
        textView=findViewById(R.id.email);
        Intent intent=getIntent();
       // emailBoarder=intent.getStringExtra("email");
        textView.setText(intent.getStringExtra("institute"));
       // txtname.setText(intent.getStringExtra("first_name"));




    }
}