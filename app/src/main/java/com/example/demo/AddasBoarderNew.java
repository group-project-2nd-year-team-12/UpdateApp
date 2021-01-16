package com.example.demo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AddasBoarderNew extends AppCompatActivity {

    private static final String apiurl="http://10.0.2.2/Android/files/AddasBSelect.php";

    ListView lv;
    String emailShared,Request_id;

    private String request_id[];
    private String first_name[];
    private String gender[];
    private String NIC[];
    private String telephone[];
    private String institute[];
   private String B_post_id[];
   private  String 	keymoneyAmount[];
   //private String payment_method[];

//   private String payment_date[];






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addas_boarder_new);

        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("details", Context.MODE_PRIVATE);
        emailShared=sharedPreferences.getString("username","no name");


        ///set the toolbar previous icon
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        lv=findViewById(R.id.listaddasb);

        fetch_array_data(lv);
    }

    private void fetch_array_data(View view) {

        String qur="?email="+emailShared;
        class dbManager extends AsyncTask<String,Void,String>{

            protected void onPostExecute(String data){


                try {
                    JSONArray jsonArray=new JSONArray(data);
                    JSONObject jsonObject=null;

                    request_id=new String[jsonArray.length()];
                    first_name=new String[jsonArray.length()];
                    gender=new String[jsonArray.length()];
                    NIC=new String[jsonArray.length()];
                    telephone=new String[jsonArray.length()];
                    institute=new String[jsonArray.length()];
                   B_post_id=new String[jsonArray.length()];
                    keymoneyAmount=new String[jsonArray.length()];


                    //payment_method=new String[jsonArray.length()];

                  //  payment_date=new String[jsonArray.length()];




                    for (int i=0;i<jsonArray.length();i++){
                        jsonObject=jsonArray.getJSONObject(i);


                        request_id[i]=jsonObject.getString("request_id");
                        first_name[i]=jsonObject.getString("first_name");
                        gender[i]=jsonObject.getString("gender");
                        NIC[i]=jsonObject.getString("NIC");
                        telephone[i]=jsonObject.getString("telephone");
                        institute[i]=jsonObject.getString("institute");
                        B_post_id[i]=jsonObject.getString("B_post_id");
                        keymoneyAmount[i]=jsonObject.getString("keymoneyAmount");

                     //   payment_method[i]=jsonObject.getString("payment_method");

                      //  payment_date[i]=jsonObject.getString("payment_date");



                    }


                    myAdapter adapter=new myAdapter(getApplicationContext(), request_id,first_name,gender,NIC,telephone,institute,B_post_id,keymoneyAmount);

                    lv.setAdapter(adapter);



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }



            @Override
            protected String doInBackground(String... strings) {

                try {
                    URL url=new URL(strings[0]);
                    HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream() ));

                    StringBuffer data=new StringBuffer();
                    String line;
                    while ((line=bufferedReader.readLine())!=null){
                        data.append(line+"\n");
                    }
                    bufferedReader.close();

                    return data.toString();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }

        dbManager db=new dbManager();
        db.execute(apiurl+qur);


    }

    class myAdapter extends ArrayAdapter<String>{

        Context context;
        String request_id[];
        String first_name[];
        String gender[];
        String NIC[];

        String telephone[];
        String institute[];
       String B_post_id[];
       String keymoneyAmount[];
   //    String payment_method[];

    //  String payment_date[];



        public myAdapter(Context context,String request_id[],String first_name[],String gender[],String NIC[],String telephone[],String institute[],String B_post_id[],String keymoneyAmount[]) {
            super(context, R.layout.card_add_as_boarder_list,R.id.request_id,request_id);
            this.context=context;
            this.request_id=request_id;
            this.first_name=first_name;
            this.gender=gender;
            this.NIC=NIC;
            this.telephone=telephone;
            this.institute=institute;
            this.B_post_id=B_post_id;
            this.keymoneyAmount=keymoneyAmount;
        //    this.payment_method=payment_method;

           // this.payment_date=payment_date;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater inflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=inflater.inflate( R.layout.card_add_as_boarder_list,parent,false);


            TextView txtrequest_id=row.findViewById(R.id.request_id);
            TextView txtfirst_name=row.findViewById(R.id.name);
            TextView txtgender=row.findViewById(R.id.gender);
            TextView txtNIC=row.findViewById(R.id.nic);
            TextView txttelephone=row.findViewById(R.id.telephone);
            TextView txtinstitute=row.findViewById(R.id.institute);
            TextView txtB_post_id=row.findViewById(R.id.post_id);
            TextView txtkeymoneyAmount=row.findViewById(R.id.keymoneyAmount);
            Button btnAdd=row.findViewById(R.id.addboarder);
          // TextView txtpayment_date=row.findViewById(R.id.payment_date);

            Request_id=txtrequest_id.getText().toString();
          // TextView txtpayment_method=row.findViewById(R.id.payment_method);

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder alert=new AlertDialog.Builder(AddasBoarderNew.this);
                    alert.setMessage("Are you sure, Add boarder?");
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(AddasBoarderNew.this,AddBTem.class);
                            intent.putExtra("request_id",Request_id);
                            startActivity(intent);
                        }
                    }).setNegativeButton("Cancel",null).setCancelable(false);

                    AlertDialog alertDialog=alert.create();
                    alertDialog.show();





                }
            });


            txtrequest_id.setText(request_id[position]);
            txtfirst_name.setText(first_name[position]);
            txtgender.setText(gender[position]);
            txtNIC.setText(NIC[position]);
            txttelephone.setText(telephone[position]);
            txtinstitute.setText(institute[position]);
             txtB_post_id.setText(B_post_id[position]);

             txtkeymoneyAmount.setText(keymoneyAmount[position]);
            //txtpayment_method.setText(payment_method[position]);
            // txtpayment_date.setText(payment_date[position]);

           return row;





        }

    }


}




