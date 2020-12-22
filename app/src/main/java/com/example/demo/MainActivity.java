package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
EditText usernameEdit,passwordEdit;
TextView TextForget,TextRegister ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEdit=(EditText) findViewById(R.id.username);
        passwordEdit=(EditText) findViewById(R.id.password);
        TextForget=findViewById(R.id.forgetp);
        TextRegister=findViewById(R.id.register);

    }
    public void OnLogin(View view) throws ExecutionException, InterruptedException {
        String username=usernameEdit.getText().toString();
        String password=passwordEdit.getText().toString();
       // tt =findViewById(R.id.textView);
        String type="login";

        Background background=new Background(this);
       String  result = background.execute(type, username, password).get();
                if (result.equals("LOGIN SUCCESS")){
                    Intent i = new Intent(this,Second.class);
                    this.startActivity(i);
                  // tt.setText(result);
                }else{
                    AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                    alertDialog.setMessage(result);
                    alertDialog.show();
                }


    }

    public void Onforget(View view) {
        Intent intent=new Intent(this,ForgetPassword.class);
        startActivity(intent);
    }

    public void Onregister(View view) {
        Intent intent=new Intent(this,Register.class);
        startActivity(intent);
    }
}


