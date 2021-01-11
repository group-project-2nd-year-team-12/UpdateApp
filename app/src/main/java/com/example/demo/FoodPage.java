package com.example.demo;


        import android.content.DialogInterface;
        import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
        import androidx.appcompat.app.AlertDialog;
        import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

      //  import es.dmoral.toasty.Toasty;

public class FoodPage extends AppCompatActivity {
    CardView orderfood,Acceptorder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_page);
        orderfood=findViewById(R.id.c1);
        Acceptorder=findViewById(R.id.c2);

        orderfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),OrderFood.class);
                startActivity(intent);
              //  Toasty.info(getApplicationContext(),"Welcome to Order food Page", Toast.LENGTH_LONG).show();
            }
        });

        Acceptorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AcceptedOrder.class);
                startActivity(intent);
               // Toasty.info(getApplicationContext(),"Welcome to Order food Page",Toast.LENGTH_LONG).show();
            }
        });



        ///set toolbar in previous icon
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    ////set item menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.example_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    ////click menu item in menu


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.item1:
                Intent intent=new Intent(this,HomeBO.class);
                startActivity(intent);
              //  Toasty.info(this,"Home page is again",Toast.LENGTH_LONG).show();
                //Toast.makeText(this,"Home page is again",Toast.LENGTH_LONG).show();
                break;

            case R.id.item2:


                AlertDialog.Builder alert=new AlertDialog.Builder(FoodPage.this);
                alert.setMessage("Are you want to logout?").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sharedPreferences=getSharedPreferences("details",MODE_PRIVATE);
                        if (sharedPreferences.contains("username")){
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.remove("username");
                            // editor.commit();
                            Intent intent1=new Intent(FoodPage.this,MainActivity.class);
                            startActivity(intent1);

                            Toast.makeText(FoodPage.this,"Logout Successfully",Toast.LENGTH_LONG).show();
                        }
                    }
                }).setNegativeButton("Cancel",null).setCancelable(false);

                AlertDialog alertDialog=alert.create();
                alertDialog.show();

              break;


                //Toasty.success(this,"Logout succesfully",Toast.LENGTH_LONG).show();
                //Toast.makeText(this,"Logout succesfully",Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
