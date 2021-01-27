package com.example.demo;


import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderFood extends AppCompatActivity {

    private static final String PRODUCT_URL="http://10.0.2.2/Android/files/food.php";

    RecyclerView recyclerView;
   // ProductAdapter adapter;
    FoodNameAdapter adapter;

    String first_name;
   // List<Product> productList;
    List<FoodName> foodNameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_food);

        ///set toolbar previous icon
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       // productList=new ArrayList<>();
        foodNameList=new ArrayList<>();

        recyclerView=findViewById(R.id.recyclerfood);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        loadProducts();









    }

    private void loadProducts() {
//        StringRequest stringRequest=new StringRequest(Request.Method.GET, PRODUCT_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        try {
//                            JSONArray food=new JSONArray(response);
//                            for (int i=0;i<food.length();i++)
//                            {
//                                JSONObject jsonObject=food.getJSONObject(i);
//                                String title=jsonObject.getString("ad_title");
//                                String  description=jsonObject.getString("description");
//                                String address=jsonObject.getString("address");
//                                String rating=jsonObject.getString("rating");
//                                String image="http://10.0.2.2/Android/files/"+jsonObject.getString("image");
//
//                                FoodName foodName=new FoodName(title,description,address,rating,image);
//
//                                foodNameList.add(foodName);
//
//                            }
//
//                            adapter=new FoodNameAdapter(OrderFood.this,foodNameList);
//                            recyclerView.setAdapter(adapter);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
        StringRequest stringRequest=new StringRequest(Request.Method.GET, PRODUCT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray food=new JSONArray(response);

                            for (int i=0;i<food.length();i++){

                                JSONObject jsonObject=food.getJSONObject(i);
                                String title=jsonObject.getString("ad_title");
                                String  description=jsonObject.getString("description");
                                String address=jsonObject.getString("address");
                                String rating=jsonObject.getString("rating");
                                String image="http://10.0.2.2/Android/files/"+jsonObject.getString("image");

                                FoodName foodName=new FoodName(title,description,address,rating,image);
                                foodNameList.add(foodName);
                            }
                            adapter=new FoodNameAdapter(OrderFood.this,foodNameList);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(OrderFood.this,"One item selected",Toast.LENGTH_LONG).show();
                                }
                            });


//                            adapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
//                                @Override
//                                public void onItemClick(int position) {
//                                    //productList.get(position)
//                                    Toast.makeText(OrderFood.this,"One item selected",Toast.LENGTH_LONG).show();
//                                }
//
//
//                                @Override
//                                public void onDelete(int position) {
//
//                                    String type="AcceptReqBO";
//
//                                    // Intent intent=new Intent(getApplicationContext(),HomeBOwner.class);
//                                    //   startActivity(intent);
//
//
//                                    Toast.makeText(OrderFood.this,"up  "+position,Toast.LENGTH_LONG).show();
//                                }
//                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(OrderFood.this,error.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

      Volley.newRequestQueue(this).add(stringRequest);
    }
}