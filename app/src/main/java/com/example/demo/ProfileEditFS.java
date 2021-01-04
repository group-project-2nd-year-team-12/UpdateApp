package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;

public class ProfileEditFS extends AppCompatActivity {
    EditText EditFirst,EditLast,EditAddress,EditNic;
    String emailShared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit_f_s);

        EditFirst=findViewById(R.id.first_name);
        EditLast=findViewById(R.id.last_name);
        EditAddress=findViewById(R.id.address);
        EditNic=findViewById(R.id.nic);

        Intent intent=getIntent();
        EditFirst.setText(intent.getStringExtra("intentFirst"));
        EditLast.setText(intent.getStringExtra("intentLast"));
        EditAddress.setText(intent.getStringExtra("intentAddress"));
        EditNic.setText(intent.getStringExtra("intentNic"));
        emailShared=intent.getStringExtra("intentEmail");

    }

    public void onUpdateFS(View view) throws ExecutionException, InterruptedException {

        String first_name=EditFirst.getText().toString();
        String last_name=EditLast.getText().toString();
        String address=EditAddress.getText().toString();
        String nic=EditNic.getText().toString();


        String type="updateProfileFS";

        BackgroundEditFS backgroundEditFS=new BackgroundEditFS(this);
        String result=backgroundEditFS.execute(type,emailShared,first_name,last_name,address,nic).get();

        if (result.equals("Successfully")){
            Intent intent=new Intent(getApplicationContext(),ProfilePageFS.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(),"Successfully Updated",Toast.LENGTH_LONG).show();
        }


    }
}