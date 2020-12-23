package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
public class MyAdsBOwner extends AppCompatActivity {
//    SharedPreferences sharedPreferences=getSharedPreferences("details",MODE_PRIVATE);
//    String email=sharedPreferences.getString("username","no name");

   String email;
    private static final String url1="http://10.0.2.2/Android/index.php";
    ListView lv;
    ArrayList<String>holder=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ads_b_owner);
        SharedPreferences sp=getApplicationContext().getSharedPreferences("details", Context.MODE_PRIVATE);
         email=sp.getString("username","No name");


        fetchdata();
    }
    public void fetchdata()
    {
        String qur="?username="+email;
        class dbManager extends AsyncTask<String,Void,String>{
            protected  void onPostExecute(String data){
                lv=(ListView)findViewById(R.id.lv);
                try {
                    JSONArray ja=new JSONArray(data);
                    JSONObject jo=null;
                    holder.clear();
                    for (int i=0;i<ja.length();i++){
                        jo=ja.getJSONObject(i);
                        String email=jo.getString("email");
                        holder.add(email);
                    }

                    ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,holder);
                    lv.setAdapter(arrayAdapter);

                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(String... strings) {
                try {
                    URL url=new URL(strings[0]);
                    HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    StringBuffer data=new StringBuffer();
                    String line;
                    while ((line=bufferedReader.readLine())!=null){
                        data.append(line+"\n");
                    }
                    bufferedReader.close();

                    return data.toString();



                }catch (Exception ex){
                    return ex.getMessage();
                }
            }
        }
        dbManager obj=new dbManager();
        obj.execute(url1+qur);
    }
}

/*public class MyAdsBOwner extends AppCompatActivity {
    private static final String url1="http://10.0.2.2/Android/index.php";
    ListView lv;
   ArrayList<String>holder=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ads_b_owner);
        fetchdata();
    }
    public void fetchdata()
    {
        class dbManager extends AsyncTask<String,Void,String>{
            protected  void onPostExecute(String data){
               lv=(ListView)findViewById(R.id.lv);
                try {
                    JSONArray ja=new JSONArray(data);
                    JSONObject jo=null;
                    holder.clear();
                    for (int i=0;i<ja.length();i++){
                        jo=ja.getJSONObject(i);
                        String email=jo.getString("email");
                        holder.add(email);
                    }

                    ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,holder);
                    lv.setAdapter(arrayAdapter);

                }catch (Exception ex){
                    Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(String... strings) {
                try {
                    URL url=new URL(strings[0]);
                    HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    StringBuffer data=new StringBuffer();
                    String line;
                    while ((line=bufferedReader.readLine())!=null){
                        data.append(line+"\n");
                    }
                    bufferedReader.close();

                    return data.toString();



                }catch (Exception ex){
                    return ex.getMessage();
                }
            }
        }
        dbManager obj=new dbManager();
        obj.execute(url1);
    }
}
*/
