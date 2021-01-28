package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InsideFoodPost extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_food_post);

        Intent intent=getIntent();

        String F_post_id=intent.getStringExtra("F_post_id");
        TextView txtF_post_id=findViewById(R.id.F_post_id);
        txtF_post_id.setText(F_post_id);

        ///set image
//        String image=intent.getStringExtra("image");
//        ImageView imageView=findViewById(R.id.image);
//        Glide.with(this).load(image).into(imageView);



    }
}