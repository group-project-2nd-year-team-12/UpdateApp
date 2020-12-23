package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.zip.Inflater;

import es.dmoral.toasty.Toasty;

public class profile extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
///set the toolbar in previous icon
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("Bodima.lk");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    ///set the menu item in toolbar


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.example_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }


    ///click the menu icon the result what to do

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.item1:
                Intent intent1=new Intent(this,HomeBO.class);
                startActivity(intent1);
                Toasty.info(this,"Home page is again",Toast.LENGTH_LONG).show();
                //Toast.makeText(this,"Home page is selected",Toast.LENGTH_LONG).show();
                //return true;
                break;

            case R.id.item2:
                SharedPreferences sharedPreferences=getSharedPreferences("details",MODE_PRIVATE);
                if (sharedPreferences.contains("username")){
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.remove("username");
                    Intent intent=new Intent(this,MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(this,"Logout succesfully",Toast.LENGTH_LONG).show();
                  //  Toasty.success(this,"Succesfully Logout",Toast.LENGTH_LONG).show();
                }


                //return true;
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
