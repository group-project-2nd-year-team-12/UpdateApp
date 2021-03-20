package com.example.demo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class InsideFoodPost extends AppCompatActivity {

    private static final String apiurl="http://10.0.2.2/Android/files/getBoarderDet.php";
    String F_post_id,product_name,price,title,emailShared;
    private static String first_name[];
    private static String last_name[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_food_post);

        Intent intent=getIntent();

         F_post_id=intent.getStringExtra("F_post_id");
         product_name=intent.getStringExtra("product_name");
         price=intent.getStringExtra("price");
         title=intent.getStringExtra("title");


        TextView txtF_post_id=findViewById(R.id.F_post_id);
        txtF_post_id.setText(product_name);

        SharedPreferences sharedPreferences=getSharedPreferences("details",MODE_PRIVATE);
        emailShared=sharedPreferences.getString("username","No name");





       // /set image
//        String image=intent.getStringExtra("image");
//        ImageView imageView=findViewById(R.id.image);
//        Glide.with(this).load(image).into(imageView);

        fetch_data_into_array();

    }

    private void fetch_data_into_array() {

        String qur="?email="+emailShared;
        class dbManager extends AsyncTask<String,Void,String> {

            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONArray ja=new JSONArray(s);
                    JSONObject jo=null;

                    first_name=new String[ja.length()];

                    last_name=new String[ja.length()];

                    for (int i=0;i<ja.length();i++){
                        jo=ja.getJSONObject(i);
                        first_name[i]=jo.getString("first_name");

                        last_name[i]=jo.getString("last_name");
                    }





                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            protected String doInBackground(String... strings)
            {
                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuffer data = new StringBuffer();
                    String line;

                    while ((line = br.readLine()) != null) {
                        data.append(line + "\n");
                    }
                    br.close();

                    return data.toString();

                } catch (Exception ex) {
                    return ex.getMessage();
                }

            }
        }
        dbManager obj=new dbManager();
        obj.execute(apiurl+qur);
    }

}