package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DinnerFood extends AppCompatActivity {

    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinner_food);

        t=findViewById(R.id.ishan);
        Intent intent=getIntent();
        t.setText(intent.getStringExtra("F_post_id"));
    }
}