package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import es.dmoral.toasty.Toasty;

public class HomeFSupplier extends AppCompatActivity implements View.OnClickListener {

    CardView c1,c2,c3,c4,c5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_f_supplier);
        c1=findViewById(R.id.c1);
        c2=findViewById(R.id.c2);
        c3=findViewById(R.id.c3);
        c4=findViewById(R.id.c4);
        c5=findViewById(R.id.c5);

        ///set the toolbar previous icon
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    c1.setOnClickListener(this);
    c2.setOnClickListener(this);
    c3.setOnClickListener(this);
    c4.setOnClickListener(this);
    c5.setOnClickListener(this);


    }


    ////set the menu item
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.home_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    ////click menu item in menu


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.item1:
//                Intent intent=new Intent(this,HomeBOwner.class);
//                startActivity(intent);
//                Toasty.info(this,"Home page is again", Toast.LENGTH_LONG).show();
                //Toast.makeText(this,"Direct Home Page again",Toast.LENGTH_LONG).show();
                break;
            case R.id.item2:
                Intent intent1=new Intent(this,MainActivity.class);
                startActivity(intent1);
                Toasty.success(this,"Succesfully Logout", Toast.LENGTH_LONG).show();
                // Toast.makeText(this,"Successfully Logout",Toast.LENGTH_LONG).show();

                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        Intent i;
        switch (v.getId()){
            case R.id.c1:
                i=new Intent(getApplicationContext(),ForgetPassword.class);
                startActivity(i);
                break;
            case R.id.c4:
                i=new Intent(getApplicationContext(),MyPostsFSupplier.class);
                startActivity(i);
                break;
            case R.id.c5:
                i=new Intent(getApplicationContext(),ProfilePageFS.class);
                startActivity(i);
                break;

        }

    }
}