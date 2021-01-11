package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;

public class CancelReqBO extends AppCompatActivity {

    String request_id,type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_req_b_o);

        Intent intent=getIntent();
        request_id=intent.getStringExtra("request_id");
        type=intent.getStringExtra("type");

        BackgroundNewReqBO backgroundNewReqBO=new BackgroundNewReqBO(CancelReqBO.this);
        try {
            String result=backgroundNewReqBO.execute(type,request_id).get();

            if (result.equals("Successfully")){
                Intent intent1=new Intent(CancelReqBO.this,NewRequestBO.class);
                Toast.makeText(getApplicationContext(),"Request is cancelled", Toast.LENGTH_LONG).show();
                startActivity(intent1);

            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Toast.makeText(getApplicationContext(),request_id,Toast.LENGTH_LONG).show();

    }
}