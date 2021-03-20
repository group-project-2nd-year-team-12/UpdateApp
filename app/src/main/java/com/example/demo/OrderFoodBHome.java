package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

public class OrderFoodBHome extends AppCompatActivity {
 CardView c1,c2,c3;
    TextView txtF_post_id;

    String F_post_id,title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food_b_home);



        Intent intent=getIntent();


         F_post_id=intent.getStringExtra("F_post_id");
         title=intent.getStringExtra("title");
         txtF_post_id=findViewById(R.id.F_post_id);
     //    txtF_post_id.setText(F_post_id);
        txtF_post_id.setText(title);

        //set the toolbar
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        c1=findViewById(R.id.c1);
        c2=findViewById(R.id.c2);
        c3=findViewById(R.id.c3);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"You selected breakfast food", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(OrderFoodBHome.this,BreakfastFood.class);
                intent.putExtra("F_post_id",F_post_id);
                intent.putExtra("title",title);
                startActivity(intent);
               // startActivity(new Intent(OrderFoodBHome.this,BreakfastFood.class));
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"You selected Lunch food", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(OrderFoodBHome.this,LunchFood.class);
                intent.putExtra("F_post_id",F_post_id);
                intent.putExtra("title",title);
                startActivity(intent);

            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"You selected dinner food", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(OrderFoodBHome.this,DinnerFood.class);
                intent.putExtra("F_post_id",F_post_id);
                intent.putExtra("title",title);
                startActivity(intent);
            }
        });




    }
}