package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;

public class ProfileEditBO extends AppCompatActivity {

    EditText EditFirst,EditLast,Editnic,Editaddress;
    String intentEmail;
    Button Edit;
    TextView txtfirst,txtlast,txtnic,txtaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit_b_o);

        EditFirst=findViewById(R.id.first_name);
        EditLast=findViewById(R.id.last_name);
        Editnic=findViewById(R.id.nic);
        Editaddress=findViewById(R.id.address);

        txtfirst=findViewById(R.id.email1);
        txtlast=findViewById(R.id.name1);
        txtnic=findViewById(R.id.institute1);
        txtaddress=findViewById(R.id.telephone1);

        Intent intent=getIntent();
        intentEmail=intent.getStringExtra("intentEmail");
        EditFirst.setText(intent.getStringExtra("intentfirst_name"));
        EditLast.setText(intent.getStringExtra("intentlast_name"));
        Editnic.setText(intent.getStringExtra("intentnic"));
        Editaddress.setText(intent.getStringExtra("intent_address"));

        Edit=findViewById(R.id.update);








    }

    public void onUpdateBO(View view) throws ExecutionException, InterruptedException {
        String first_name=EditFirst.getText().toString();
        String last_name=EditLast.getText().toString();
        String nic=Editnic.getText().toString();
        String address=Editaddress.getText().toString();

        txtfirst.setText(first_name);
        txtlast.setText(last_name);
        txtnic.setText(nic);
        txtaddress.setText(address);
        //updateProfileBO.php
        String type="updateProfileBO";

        BackgroundEditBO backgroundEditBO=new BackgroundEditBO(this);
        String result=backgroundEditBO.execute(type,intentEmail,first_name,last_name,nic,address).get();

        if (result.equals("Successfully")){
            Intent intent=new Intent(getApplicationContext(),ProfilePageBO.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(),"Successfully Updated",Toast.LENGTH_LONG).show();
        }


    }
}