package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LunchFood extends AppCompatActivity {

    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch_food);
        Intent intent=getIntent();

        t=findViewById(R.id.ishan);
        t.setText(intent.getStringExtra("F_post_id"));
    }
}