package com.example.demo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class InsideFoodPost extends AppCompatActivity {

    private static final String apiurl="http://10.0.2.2/Android/files/getBoarderDet.php";
    String F_post_id,product_name,price,title,emailShared,first,last,address,phone;
    private static String first_name[];
    private static String last_name[];

    EditText editaddress,editphone;
    Button order;
    ListView lv;
    TextView txtproduct_name,txtprice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_food_post);

        //set the toolbar
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Intent intent=getIntent();

         F_post_id=intent.getStringExtra("F_post_id");
         product_name=intent.getStringExtra("product_name");
         price=intent.getStringExtra("price");
         title=intent.getStringExtra("title");


//        TextView txtF_post_id=findViewById(R.id.F_post_id);
//        txtF_post_id.setText(product_name);

        txtproduct_name=findViewById(R.id.product_name);
        txtprice=findViewById(R.id.price);
        txtproduct_name.setText(product_name);
        txtprice.setText(price);

        SharedPreferences sharedPreferences=getSharedPreferences("details",MODE_PRIVATE);
        emailShared=sharedPreferences.getString("username","No name");


        editaddress=findViewById(R.id.editAddress);
        editphone=findViewById(R.id.editPhone);
        order=findViewById(R.id.btnOrder);



//        order.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               if ((TextUtils.isEmpty(editaddress.getText())) & (TextUtils.isEmpty(editphone.getText())) ){
//                   editaddress.setError("Enter Value");
//                   editphone.setError("Enter number");
////                   editaddress.requestFocus();
////                   editphone.requestFocus();
//
//
//               }else{
//                   address=editaddress.getText().toString();
//                   phone=editphone.getText().toString();
//                   Toast.makeText(getApplicationContext(),address+" "+phone,Toast.LENGTH_LONG).show();
//               }
//            }
//        });



       // /set image
//        String image=intent.getStringExtra("image");
//        ImageView imageView=findViewById(R.id.image);
//        Glide.with(this).load(image).into(imageView);
        lv=findViewById(R.id.listboarderd);

        fetch_data_into_array(lv);

    }

    private void fetch_data_into_array(View view) {

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
                    myadapter adptr = new myadapter(getApplicationContext(), first_name, last_name);
                    lv.setAdapter(adptr);





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

    public void onOrder(View view) {
        if ((TextUtils.isEmpty(editaddress.getText())) & (TextUtils.isEmpty(editphone.getText())) ){
            editaddress.setError("Enter Value");
            editphone.setError("Enter number");
//                   editaddress.requestFocus();
//                   editphone.requestFocus();


        }else{
            address=editaddress.getText().toString();
            phone=editphone.getText().toString();
            String type="InsertFoodB";
            String term="shortTerm";
            String order_type="breakfast";
            String shedule="now";
            String method="cash";


            BackgroundFoodReqB b=new BackgroundFoodReqB(this);
            b.execute(type,emailShared,address,first,last,term,order_type,shedule,title,F_post_id,price,phone,method,product_name);
            Toast.makeText(getApplicationContext(),address+" "+phone,Toast.LENGTH_LONG).show();
        }
    }


    class myadapter extends ArrayAdapter<String>
    {
        Context context;


        String first_name[];
        String last_name[];

        myadapter(Context c,String first_name[], String last_name[])
        {
            super(c,R.layout.card_details_boarder,R.id.first_name,first_name);
            context=c;

            this.first_name=first_name;
            this.last_name=last_name;





        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            LayoutInflater inflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=inflater.inflate(R.layout.card_details_boarder,parent,false);




            TextView txtfirst_name=row.findViewById(R.id.first_name);

            txtfirst_name.setText(first_name[position]+" "+last_name[position]);

            first=first_name[position];
            last=last_name[position];







            return row;
        }
    }

}