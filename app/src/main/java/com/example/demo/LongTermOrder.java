package com.example.demo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LongTermOrder extends AppCompatActivity {

    private static final String apiurl = "http://10.0.2.2/Android/files/selectLongFood.php";
    String emailShared;
    ListView lv;

    private static String order_id[];

    private static String first_name[];
    private static String address[];
    private static String phone[];
    private static String total[];
    private static String method[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_term_order);

        //set sharedpreferences
        SharedPreferences sp = getApplicationContext().getSharedPreferences("details", Context.MODE_PRIVATE);
        emailShared = sp.getString("username", "No name");


        //set toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.dashbaord);

        //Perform ItemSelectedListener

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.dashbaord:

                        return true;
                    case R.id.home:

                        startActivity(new Intent(getApplicationContext(), NewOrders.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });


        lv = findViewById(R.id.listlongfood);


        //selectLongFood.php

        fetch_data_into_array(lv);


    }

    public void fetch_data_into_array(View view)
    {
        String qur="?username="+emailShared;

        class  dbManager extends AsyncTask<String,Void,String>
        {
            protected void onPostExecute(String data)
            {
                try {
                    JSONArray ja = new JSONArray(data);
                    JSONObject jo = null;



                    order_id= new String[ja.length()];
                    first_name= new String[ja.length()];
                    address= new String[ja.length()];
                    phone = new String[ja.length()];
                    total = new String[ja.length()];
                    method = new String[ja.length()];






                    for (int i = 0; i < ja.length(); i++) {
                        jo = ja.getJSONObject(i);
                        order_id[i]=jo.getString("order_id");

                        first_name[i] = jo.getString("first_name");
                        address[i] = jo.getString("address");;
                        phone[i]=jo.getString("phone");
                        total[i]=jo.getString("total");
                        method[i]=jo.getString("method");




                    }

                    myadapter adptr = new myadapter(getApplicationContext(),order_id, first_name, address,phone,total,method);

                    lv.setAdapter(adptr);

                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
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



    class myadapter extends ArrayAdapter<String>
    {
        Context context;

        String order_id[];

        String first_name[];
        String address[];
        String phone[];
        String total[];
        String method[];


        myadapter(Context c, String order_id[],String first_name[], String address[], String phone[],String total[],String method[])
        {
            super(c,R.layout.card_long_term_food,R.id.order_id,order_id);
            context=c;
            this.order_id=order_id;

            this.first_name=first_name;
            this.address=address;
            this.phone=phone;
            this.total=total;
            this.method=method;





        }
        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            LayoutInflater inflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=inflater.inflate(R.layout.card_long_term_food,parent,false);





            TextView txtfirst_name=row.findViewById(R.id.first_name);
            TextView txtfaddress=row.findViewById(R.id.address);
            TextView txtphone=row.findViewById(R.id.phone);
            TextView txtftotal=row.findViewById(R.id.total);
            TextView txtmethod=row.findViewById(R.id.method);
            TextView txtorderId=row.findViewById(R.id.order_id);





            txtorderId.setText(order_id[position]);

            txtfirst_name.setText(first_name[position]);
            txtfaddress.setText(address[position]);
            txtphone.setText(phone[position]);
            txtftotal.setText(total[position]);
            txtmethod.setText(method[position]);



            return row;
        }
    }
}