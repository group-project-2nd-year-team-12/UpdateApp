package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
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

public class AddasBoarderBO extends AppCompatActivity {

    private static final String PRODUCT_URL="http://10.0.2.2/Android/files/sample.php";

    RecyclerView recyclerView;
    ProductAdapter adapter;

    String first_name;
    List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addas_boarder_b_o);

        productList=new ArrayList<>();

        recyclerView=findViewById(R.id.recyclerP);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));



       loadProducts();









    }

    private void loadProducts() {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, PRODUCT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray products=new JSONArray(response);

                            for (int i=0;i<products.length();i++){

                                JSONObject productsobject=products.getJSONObject(i);

                                String first_name=productsobject.getString("first_name");
                                String last_name=productsobject.getString("last_name");
                                String institute=productsobject.getString("institute");

                                String image="http://10.0.2.2/Android/files/"+productsobject.getString("image");

                                Product product=new Product(first_name,last_name,institute,image);
                                productList.add(product);
                            }
                            adapter=new ProductAdapter(AddasBoarderBO.this,productList);
                            recyclerView.setAdapter(adapter);

                            adapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(int position) {
                                    //productList.get(position)
                                    Toast.makeText(AddasBoarderBO.this,"One item selected",Toast.LENGTH_LONG).show();
                                }


                                @Override
                                public void onDelete(int position) {

                                   // String type="AcceptReqBO";
                                    Intent intent=new Intent(getApplicationContext(),HomeBOwner.class);
                                    startActivity(intent);


                                    Toast.makeText(AddasBoarderBO.this,"up  "+position,Toast.LENGTH_LONG).show();
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddasBoarderBO.this,error.getMessage(),Toast.LENGTH_LONG).show();

            }
        });

        Volley.newRequestQueue(this).add(stringRequest);
    }
}