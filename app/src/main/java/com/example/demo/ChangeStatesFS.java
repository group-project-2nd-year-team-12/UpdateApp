package com.example.demo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.concurrent.ExecutionException;

public class ChangeStatesFS extends AppCompatActivity {

    String emailShare;
    RadioGroup radioGroup;
    RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_states_f_s);

        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("details", Context.MODE_PRIVATE);
        emailShare=sharedPreferences.getString("username","no name");

        //set toolbar
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       radioGroup=findViewById(R.id.radiogroup);




    }

    public void checkButton(View view) throws ExecutionException, InterruptedException {
        int radioId=radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(radioId);


        String type="UpdateAvailable";
        BackgroundSetStates backgroundSetStates=new BackgroundSetStates(this);
        String result=backgroundSetStates.execute(type,emailShare).get();
        if (result.equals("Successfully")){
            Toast.makeText(ChangeStatesFS.this,"You are now available state",Toast.LENGTH_LONG).show();
        }






//        AlertDialog.Builder alert=new AlertDialog.Builder(ChangeStatesFS.this);
//        alert.setMessage("Are you in the available time?").
//                setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(ChangeStatesFS.this,"sethhhhhhhh",Toast.LENGTH_LONG).show();
//                    }
//                }).setNegativeButton("Cancel",null).setCancelable(false);
//        AlertDialog alertDialog=alert.create();
//        alertDialog.show();





    }

    public void OnUnavailable(View view) {
        int radioId=radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(radioId);
        Toast.makeText(ChangeStatesFS.this,"Un avskkc"+emailShare,Toast.LENGTH_LONG).show();

        //UpdateUnAvailable

//        AlertDialog.Builder alert=new AlertDialog.Builder(ChangeStatesFS.this);
//        alert.setMessage("Are you in the Unavailable time?").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(ChangeStatesFS.this,"Un avskkc",Toast.LENGTH_LONG).show();
//            }
//        }).setNegativeButton("Cancel",null).setCancelable(false);
//        AlertDialog alertDialog=alert.create();
//        alertDialog.show();


    }
}