package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MyBordersInsideData extends AppCompatActivity {

TextView txtemail,txttel,txtgender,txtinstitute;
//Button previous;
    String emailBoarder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_borders_inside_data);





        ///set the toolbar
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtemail=findViewById(R.id.email);
        txttel=findViewById(R.id.telephone);
        txtgender=findViewById(R.id.gender);
        txtinstitute=findViewById(R.id.institute);

       // previous=findViewById(R.id.btn_previous);

        Intent intent=getIntent();

        txtinstitute.setText(intent.getStringExtra("institute"));
        txtemail.setText(intent.getStringExtra("email"));
        txttel.setText(intent.getStringExtra("telephone"));
        txtgender.setText(intent.getStringExtra("gender"));


//        previous.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1=new Intent(getApplicationContext(),MyBoarders.class);
//                startActivity(intent1);
//            }
//        });


    }
}