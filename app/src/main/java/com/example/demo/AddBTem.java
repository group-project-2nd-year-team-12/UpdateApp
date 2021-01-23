package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;

import es.dmoral.toasty.Toasty;

public class AddBTem extends AppCompatActivity {

TextView t1;
    String request_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_b_tem);
t1=findViewById(R.id.t2);
        Intent intent=getIntent();

//        request_id=intent.getStringExtra("request_id");
//
        t1.setText(intent.getStringExtra("request_id"));
        String type="UpdateAddasB";
       request_id=intent.getStringExtra("request_id");
        BackgroundAddBByBO backgroundAddBByBO=new BackgroundAddBByBO(this);
        try {
            String result=backgroundAddBByBO.execute(type,request_id).get();
            if (result.equals("Successfully")){
                Intent intent1=new Intent(AddBTem.this,AddasBoarderNew.class);
                startActivity(intent1);
                Toasty.success(getApplicationContext(),"Boarder added Success", Toast.LENGTH_LONG).show();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}