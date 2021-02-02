package com.example.demo;

import android.content.Context;
import android.content.Intent;
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

public class NewOrders extends AppCompatActivity {


    String  emailShared;
    private static final String apiurl="http://10.0.2.2/Android/files/selectSFoodOrder.php";
    ListView lv;

    ListView lv1;

 //   private static String emailBoarder[];

    private static String order_id[];
    private static String expireTime[];
    private static String first_name[];
    private  static  String address[];
    private static String phone[];
    private static String total[];
    private static String method[];
    private static String 	item_name[];
    private static String 	quantity[];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_orders);

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
      //  lv1=findViewById(R.id.listitems);

        fetch_data_into_array(lv);

    }

    public void fetch_data_into_array(View view)
    {
      //  String qur="?username="+emailShared;

        class  dbManager extends AsyncTask<String,Void,String>
        {
            protected void onPostExecute(String data)
            {
                try {
                    JSONArray ja = new JSONArray(data);
                    JSONObject jo = null;



                    order_id= new String[ja.length()];

                    expireTime= new String[ja.length()];
                    first_name= new String[ja.length()];
                    address= new String[ja.length()];
                    phone = new String[ja.length()];
                    total = new String[ja.length()];
                    method = new String[ja.length()];

                    item_name = new String[ja.length()];
                    quantity = new String[ja.length()];




                    for (int i = 0; i < ja.length(); i++) {
                        jo = ja.getJSONObject(i);
                        order_id[i]=jo.getString("order_id");

                        expireTime[i]=jo.getString("expireTime");
                        first_name[i] = jo.getString("first_name");
                        address[i] = jo.getString("address");;

                        phone[i]=jo.getString("phone");
                        total[i]=jo.getString("total");
                        method[i]=jo.getString("method");
                        quantity[i]=jo.getString("quantity");
                        item_name[i]=jo.getString("item_name");



                    }

                    myadapter adptr = new myadapter(getApplicationContext(),order_id,expireTime, first_name, address,phone,total,method,item_name,quantity);
                  //  myadapter1 adptr1 = new myadapter1(getApplicationContext(),expireTime, first_name, address,phone,total,method);
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
        obj.execute(apiurl);

    }



    class myadapter extends ArrayAdapter<String>
    {
        Context context;

        String order_id[];
       String expireTime[];
       String first_name[];
       String address[];
       String phone[];
       String total[];
       String method[];
       String item_name[];
       String quantity[];

        myadapter(Context c, String order_id[],String expireTime[], String first_name[], String address[], String phone[],String total[],String method[],String item_name[],String quantity[])
        {
            super(c,R.layout.card_short_term_order,R.id.phone,phone);
            context=c;
            this.order_id=order_id;
            this.expireTime=expireTime;
            this.first_name=first_name;
            this.address=address;
            this.phone=phone;
            this.total=total;
            this.method=method;
            this.item_name=item_name;
            this.quantity=quantity;




        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            LayoutInflater inflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=inflater.inflate(R.layout.card_short_term_order,parent,false);

            //ImageView img=row.findViewById(R.id.img1);


            TextView txtorder=row.findViewById(R.id.ishan);

            TextView txtexpireTime=row.findViewById(R.id.expireTime);
            TextView txtfirst_name=row.findViewById(R.id.first_name);
            TextView txtfaddress=row.findViewById(R.id.address);
            TextView txtphone=row.findViewById(R.id.phone);
            TextView txtftotal=row.findViewById(R.id.total);
            TextView txtmethod=row.findViewById(R.id.method);

            TextView txtitem_name=row.findViewById(R.id.n1);
            TextView txtquantity=row.findViewById(R.id.q1);

            txtitem_name.setText(item_name[position]+" "+quantity[position]);
            txtexpireTime.setText(expireTime[position]);


            txtorder.setText(order_id[position]);
            txtexpireTime.setText(expireTime[position]);
            txtfirst_name.setText(first_name[position]);
            txtfaddress.setText(address[position]);
            txtphone.setText(phone[position]);
            txtftotal.setText(total[position]);
            txtmethod.setText(method[position]);











            return row;
        }
    }


//    class myadapter1 extends ArrayAdapter<String>
//    {
//        Context context;
//
//        String expireTime[];
//        String first_name[];
//        String address[];
//        String phone[];
//        String total[];
//        String method[];
//
//        myadapter1(Context c,String expireTime[], String first_name[], String address[], String phone[],String total[],String method[])
//        {
//            super(c,R.layout.card_short_term_order,R.id.phone,phone);
//            context=c;
//            this.expireTime=expireTime;
//            this.first_name=first_name;
//            this.address=address;
//            this.phone=phone;
//            this.total=total;
//            this.method=method;
//
//
//
//
//        }
//        @NonNull
//        @Override
//        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
//        {
//            LayoutInflater inflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View row=inflater.inflate(R.layout.card_short_term_order,parent,false);
//
//            //ImageView img=row.findViewById(R.id.img1);
//
//
//
//            TextView txtexpireTime=row.findViewById(R.id.expireTime);
//            TextView txtfirst_name=row.findViewById(R.id.first_name);
//            TextView txtfaddress=row.findViewById(R.id.address);
//            TextView txtphone=row.findViewById(R.id.phone);
//            TextView txtftotal=row.findViewById(R.id.total);
//            TextView txtmethod=row.findViewById(R.id.method);
//
//
//            txtexpireTime.setText(expireTime[position]);
//
//
//            txtexpireTime.setText(expireTime[position]);
//            txtfirst_name.setText(expireTime[position]);
//            txtfaddress.setText(expireTime[position]);
//            txtphone.setText(expireTime[position]);
//            txtftotal.setText(expireTime[position]);
//            txtmethod.setText(expireTime[position]);
//
//
//
//
//
//
//
//
//
//
//
//            return row;
//        }
//    }
//










}