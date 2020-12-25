package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyPostsF extends AppCompatActivity {

    String urladdress="http://10.0.2.2/Android/files/post.php";
    String[] name;
    String[] email;
    String[] imagePath;
    ListView listView;
    BufferedInputStream is;
    String line=null;
    String result=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_posts_f);

        listView=findViewById(R.id.lview);
        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        collectData();
CustomListView customListView=new CustomListView(this,name,email,imagePath);
listView.setAdapter(customListView);
    }
    private void collectData(){
        ///connection
        try{
            URL url=new URL(urladdress);
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            is=new BufferedInputStream(con.getInputStream());

        }catch(Exception ex){
            ex.printStackTrace();
        }
        //content
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            StringBuilder sb=new StringBuilder();
            while ((line=br.readLine())!=null){
                sb.append(line+"\n");
            }
            is.close();
            result=sb.toString();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        ///JSON
        try {
            JSONArray ja=new JSONArray(result);
            JSONObject jo=null;
            name=new String[ja.length()];
            email=new String[ja.length()];
            imagePath=new String[ja.length()];

            for(int i=0;i<=ja.length();i++){
                jo=ja.getJSONObject(i);
                name[i]=jo.getString("lane");
                email[i]=jo.getString("city");
                imagePath[i]=jo.getString("image");

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}