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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NewOrders extends AppCompatActivity {


    String  emailShared;
    private static final String apiurl="http://10.0.2.2/Android/files/selectSFoodOrder.php";
    ListView lv;
     String Oid;

     CardView c1,c2,c3;




    private static String order_id[];
    private static String expireTime[];
    private static String shedule[];
    private static String order_type[];
    private static String first_name[];
    private  static  String address[];
    private  static String restaurant[];
    private static String phone[];
    private static String total[];
    private static String method[];




    Button btnAccept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_orders);

        c1=findViewById(R.id.c1);
        c2=findViewById(R.id.c2);
        c3=findViewById(R.id.c3);



        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c1.setVisibility(View.GONE);
                c2.setVisibility(View.INVISIBLE);
                c3.setVisibility(View.INVISIBLE);
                startActivity(new Intent(getApplicationContext(),BreakfastOrder.class));
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c2.setVisibility(View.GONE);
                c1.setVisibility(View.INVISIBLE);
                c3.setVisibility(View.INVISIBLE);
                startActivity(new Intent(getApplicationContext(),LunchOrder.class));
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c3.setVisibility(View.GONE);
                c2.setVisibility(View.INVISIBLE);
                c1.setVisibility(View.INVISIBLE);
                startActivity(new Intent(getApplicationContext(),DinnerOrder.class));
            }
        });

        //set the shared preferences
        SharedPreferences sp=getApplicationContext().getSharedPreferences("details", Context.MODE_PRIVATE);
        emailShared=sp.getString("username","No name");

        //set toolbar
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        //Perform ItemSelectedListener

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.dashbaord:
                        startActivity(new Intent(getApplicationContext(),LongTermOrder.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:

                        return true;
                }
                return false;
            }
        });





       // selectSFoodOrder.php
        lv=(ListView)findViewById(R.id.listfood);




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

                    expireTime= new String[ja.length()];
                    shedule=new String[ja.length()];
                    first_name= new String[ja.length()];
                    address= new String[ja.length()];
                    restaurant=new String[ja.length()];
                    phone = new String[ja.length()];
                    total = new String[ja.length()];
                    method = new String[ja.length()];
                    order_type=new String[ja.length()];






                    for (int i = 0; i < ja.length(); i++) {
                        jo = ja.getJSONObject(i);
                        order_id[i]=jo.getString("order_id");

                        expireTime[i]=jo.getString("time");
                        shedule[i]=jo.getString("shedule");
                        first_name[i] = jo.getString("first_name");
                        address[i] = jo.getString("address");
                        restaurant[i]=jo.getString("restaurant");
                        phone[i]=jo.getString("phone");
                        total[i]=jo.getString("total");
                        method[i]=jo.getString("method");
                        order_type[i]=jo.getString("order_type");




                    }

                    myadapter adptr = new myadapter(getApplicationContext(),order_id,expireTime,shedule, first_name, address,restaurant,phone,total,method,order_type);

                    lv.setAdapter(adptr);

                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), "None", Toast.LENGTH_LONG).show();
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
       String expireTime[];
       String shedule[];
       String first_name[];
       String address[];
       String restaurant[];
       String phone[];
       String total[];
       String method[];
       String order_type[];


        myadapter(Context c, String order_id[],String expireTime[],String shedule[],String first_name[], String address[],String restaurant[], String phone[],String total[],String method[],String order_type[])
        {
            super(c,R.layout.card_short_term_all,R.id.order_id,order_id);
            context=c;
            this.order_id=order_id;
            this.expireTime=expireTime;
            this.shedule=shedule;
            this.first_name=first_name;
            this.address=address;
            this.restaurant=restaurant;
            this.phone=phone;
            this.total=total;
            this.method=method;
            this.order_type=order_type;





        }
        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            LayoutInflater inflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=inflater.inflate(R.layout.card_short_term_all,parent,false);





            TextView txtexpireTime=row.findViewById(R.id.expireTime);
            TextView txtshedule=row.findViewById(R.id.shedule);
            TextView txtfirst_name=row.findViewById(R.id.first_name);
            TextView txtfaddress=row.findViewById(R.id.address);
            TextView txtrestaurant=row.findViewById(R.id.restaurant);
            TextView txtphone=row.findViewById(R.id.phone);
            TextView txtftotal=row.findViewById(R.id.total);
            TextView txtmethod=row.findViewById(R.id.method);
            TextView txtorderId=row.findViewById(R.id.order_id);
            TextView txtorder_type=row.findViewById(R.id.order_type);
             btnAccept=row.findViewById(R.id.viewbtn);




            txtorderId.setText(order_id[position]);
            txtexpireTime.setText(expireTime[position]);



            txtexpireTime.setText(expireTime[position]);
            txtshedule.setText(shedule[position]);
            txtfirst_name.setText(first_name[position]);
            txtfaddress.setText(address[position]);
            txtrestaurant.setText(restaurant[position]);
            txtphone.setText(phone[position]);
            txtftotal.setText(total[position]);
            txtmethod.setText(method[position]);
            txtorder_type.setText(order_type[position]);
            Oid=order_id[position];

//            textView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    Intent intent=new Intent(NewOrders.this,viewOrdersShort.class);
//                    intent.putExtra("order_id",Oid);
//                    startActivity(intent);
//
//                }
//            });
            btnAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(NewOrders.this,viewOrdersShort.class);
                    intent.putExtra("order_id",order_id[position]);
                    intent.putExtra("method",method[position]);
                    Toast.makeText(getApplicationContext(),emailShared, Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
            });










            return row;
        }
    }












}