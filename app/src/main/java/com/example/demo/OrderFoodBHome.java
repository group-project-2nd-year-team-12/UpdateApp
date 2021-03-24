package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;

public class OrderFoodBHome extends AppCompatActivity {
 CardView c1,c2,c3;
    TextView txtF_post_id;

    String F_post_id,title;

    SliderLayout sliderLayout;
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



        sliderLayout = findViewById(R.id.imageSlider);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL); //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(1); //set scroll delay in seconds :

        setSliderViews();
    }
    private void setSliderViews() {

        for (int i = 0; i <= 4; i++) {

            DefaultSliderView sliderView = new DefaultSliderView(this);

            switch (i) {
                case 0:
                    sliderView.setImageUrl("https://a.fsdn.com/con/app/proj/evolution-x/screenshots/2s2SvQb_d.jpg/max/max/1");
                    break;
                case 1:
                    sliderView.setImageUrl("https://xtremedroid.com/wp-content/uploads/2019/11/2.png");
                    break;
                case 2:
                    sliderView.setImageUrl("https://i.redd.it/bg62h711mej21.png");
                    break;

                case 3:
                    sliderView.setImageUrl("https://xtremedroid.com/wp-content/uploads/2019/11/1-473x1024.png");
                    break;
                case 4:
                    sliderView.setImageUrl("https://i.redd.it/2w5pnx6f1op41.jpg");
                    break;
            }

            sliderView.setImageScaleType(
                    ImageView.ScaleType.CENTER_CROP);
            //at last add this view in your layout :
            sliderLayout.addSliderView(sliderView);
        }
    }
}