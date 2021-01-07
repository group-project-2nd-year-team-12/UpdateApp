package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;

public class Register extends AppCompatActivity {

    TextView txttype,txtid;
    String type;
    String req;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txttype=findViewById(R.id.txt1);
        txtid=findViewById(R.id.txt2);

        Intent intent=getIntent();
        txttype.setText(intent.getStringExtra("type"));
        txtid.setText(intent.getStringExtra("Request_id"));

        type=intent.getStringExtra("type");
        req=intent.getStringExtra("Request_id");

                              BackgroundNewReqBO backgroundNewReqBO=new BackgroundNewReqBO(Register.this);

                      try {
                          String result=backgroundNewReqBO.execute(type,req).get();
                          if (result.equals("Successfully")){
                               Intent intent1=new Intent(Register.this,NewRequestBO.class);
                               startActivity(intent1);
                             // Toast.makeText(getApplicationContext(),"Successfully Updated",Toast.LENGTH_LONG).show();
                          }
                      } catch (ExecutionException e) {

                          e.printStackTrace();
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }

    }
}