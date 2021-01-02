package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutionException;

public class ProfileEditB extends AppCompatActivity {
    TextView txtemail,txtName,txtInstitute,txtTel;
    EditText Editemail,EditName,EditInstitute,EditTel;
    Button btnupdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit_b);
        Editemail=findViewById(R.id.email);
        EditName=findViewById(R.id.name);
        EditInstitute=findViewById(R.id.institute);
        EditTel=findViewById(R.id.telephone);

        Intent intent=getIntent();
        Editemail.setText(intent.getStringExtra("intentEmail"));
        String f=intent.getStringExtra("intentFirst");
       // String l=intent.getStringExtra("intentLast");
        EditName.setText(f);
        EditInstitute.setText(intent.getStringExtra("intentIns"));
        EditTel.setText(intent.getStringExtra("intentTel"));


        btnupdate=findViewById(R.id.update);
        txtemail=findViewById(R.id.email1);
        txtName=findViewById(R.id.name1);
        txtInstitute=findViewById(R.id.institute1);
        txtTel=findViewById(R.id.telephone1);



//
    /*    btnupdate.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             final String email = Editemail.getText().toString();
                                             final String name = EditName.getText().toString();
                                             final String institute = EditInstitute.getText().toString();
                                             final String telephone = EditTel.getText().toString();

                                             String type = "updataB";
                                             BackgroundEditB background = new BackgroundEditB(this);
                                             try {
                                                 String result= background.execute(type, email, name, institute, telephone).get();

                                                 if (result.equals("Update Successfully")){

                                                     Toast.makeText(getApplicationContext(),"Successfully",Toast.LENGTH_LONG).show();
                                                  }
                                             } catch (ExecutionException e) {
                                                 e.printStackTrace();
                                             } catch (InterruptedException e) {
                                                 e.printStackTrace();
                                             }
//
//              //  String qur="?email="+email+"&name="+name+"&institute="+institute+"&telephone="+telephone;;
//
//
//
//
//
//
////                txtemail.setText(email);
////                txtName.setText(name);
////                txtInstitute.setText(institute);
////                txtTel.setText(telephone);
          }
       });*/

    }

   /* public void onUpdate(View view) throws ExecutionException, InterruptedException{
         String email=Editemail.getText().toString();
                 String name=EditName.getText().toString();
                 String institute=EditInstitute.getText().toString();
                // String telephone=EditTel.getText().toString();

                String type="updataB";

                //BackgroundEditB backgroundEditB= new BackgroundEditB((View.OnClickListener) this);
        BackgroundEditB backgroundEditB=new BackgroundEditB(this);

      //  backgroundEditB.execute(type, email, name,institute,telephone);

        String  result = backgroundEditB.execute(type, email, name,institute).get();

        if (result.equals("Update Successfully")){

            Toast.makeText(getApplicationContext(),"Successfully",Toast.LENGTH_LONG).show();
        }


    }


*/


    public void onUpdate(View view) throws ExecutionException, InterruptedException {


        String email=Editemail.getText().toString();
        String name=EditName.getText().toString();
        String institute=EditInstitute.getText().toString();
        String telephone=EditTel.getText().toString();
//
//        txtemail.setText(email);
//                txtName.setText(name);
//                txtInstitute.setText(institute);
//                txtTel.setText(telephone);
               // txtTel.setText(telephone);


    //    String type="updataB";


//        BackgroundEditB background=new BackgroundEditB(this);
//        String  result = background.execute(type, email, name,institute,telephone).get();
//        if (result.equals("Successfully")){
//
//
//            Toast.makeText(getApplicationContext(),"Successfully",Toast.LENGTH_LONG).show();
//
//        }

    }








}