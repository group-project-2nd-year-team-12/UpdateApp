package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
EditText usernameEdit,passwordEdit;
TextView TextForget,TextRegister ;
    Spinner myspinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEdit=(EditText) findViewById(R.id.username);
        passwordEdit=(EditText) findViewById(R.id.password);
        TextForget=findViewById(R.id.forgetp);
        TextRegister=findViewById(R.id.register);

        myspinner=findViewById(R.id.level);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.level,R.layout.support_simple_spinner_dropdown_item);
        myspinner.setAdapter(adapter);


    }
    public void OnLogin(View view) throws ExecutionException, InterruptedException {
        String username=usernameEdit.getText().toString();
        String password=passwordEdit.getText().toString();

        String level=myspinner.getSelectedItem().toString();
       // tt =findViewById(R.id.textView);
        String type="login";


        Background background=new Background(this);
       String  result = background.execute(type, username, password,level).get();
                if (result.equals("LOGIN SUCCESS")){

                    SharedPreferences sharedPreferences=getSharedPreferences("details",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("username",username);
                    editor.commit();

                    SharedPreferences sharedPreferences1=getSharedPreferences("details",MODE_PRIVATE);
                    String email=sharedPreferences.getString("username","No name");
                    if (level.equals("boarder")){
                        Intent i = new Intent(this,HomeBO.class);
                        this.startActivity(i);
                        Toast.makeText(this,"your username "+email,Toast.LENGTH_LONG).show();
                    }else  if (level.equals("boardings_owner")){
                        Intent i = new Intent(this,HomeBOwner.class);
                        this.startActivity(i);
                        Toast.makeText(this,"your username "+email,Toast.LENGTH_LONG).show();
                    }
                    else  if (level.equals("food_supplier")){
                        Intent i = new Intent(this,ForgetPassword.class);
                        this.startActivity(i);
                        Toast.makeText(this,"your username "+email,Toast.LENGTH_LONG).show();
                    }
                  // tt.setText(result);
                }else{
                    AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                    alertDialog.setMessage(result);
                    alertDialog.show();
                }


    }

    public void Onforget(View view) {
        Intent intent=new Intent(this,my_post_BO.class);
        startActivity(intent);
    }

    public void Onregister(View view) {
        Intent intent=new Intent(this,finalPractise.class);
        startActivity(intent);
    }
}


