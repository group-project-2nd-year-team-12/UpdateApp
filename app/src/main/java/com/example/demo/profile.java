package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.zip.Inflater;

import es.dmoral.toasty.Toasty;

public class profile extends AppCompatActivity  {

TextView textname,textemail,textinstitute,texttelephone;
Button btnEdit;
String email;
    private static final String url1="http://10.0.2.2/Android/files/profile.php";
    ArrayList<String>holder=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        textname=findViewById(R.id.name);
        textemail=findViewById(R.id.email);
        textinstitute=findViewById(R.id.address);
        texttelephone=findViewById(R.id.telephone);
    ///set the toolbar in previous icon
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("Bodima.lk");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences sp=getApplicationContext().getSharedPreferences("details", Context.MODE_PRIVATE);
        email=sp.getString("details","No email");
        fetchdata();
    }

    private void fetchdata() {
        String qry="?username="+email;
       class dbManager extends AsyncTask<String,Void,String>{

           protected void onPostExecute(String data){


           }
           @Override
           protected String doInBackground(String... strings) {
               try {
                   URL url=new URL(strings[0]);
                   HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                   BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                   StringBuffer data=new StringBuffer();
                   String line;
                   while ((line=bufferedReader.readLine())!=null){
                       data.append(line+"\n");
                   }
                   bufferedReader.close();
                   return data.toString();

               } catch (MalformedURLException e) {
                   e.printStackTrace();
               } catch (IOException e) {
                   e.printStackTrace();
               }

               return null;
           }
       }
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
