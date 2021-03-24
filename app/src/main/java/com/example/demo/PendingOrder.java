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
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AlertDialog;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;

        import org.json.JSONArray;
        import org.json.JSONObject;

        import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;

public class PendingOrder extends AppCompatActivity {


    String  emailShared;
    private static final String apiurl="http://10.0.2.2/Android/files/selectPendingOB.php";
    ListView lv;
    String Oid;






    private static String order_id[];
    private static String time[];
    private static String shedule[];


    private  static  String address[];
    private  static String restaurant[];

    private static String total[];
    private static String method[];




    TextView btnAccept;
    Button btnConfirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_order);





        //set the shared preferences
        SharedPreferences sp=getApplicationContext().getSharedPreferences("details", Context.MODE_PRIVATE);
        emailShared=sp.getString("username","No name");

        //set toolbar
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);








        lv=(ListView)findViewById(R.id.listfoodpending);




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

                    time= new String[ja.length()];
                    shedule=new String[ja.length()];

                    address= new String[ja.length()];
                    restaurant=new String[ja.length()];

                    total = new String[ja.length()];
                    method = new String[ja.length()];
//                    order_type=new String[ja.length()];






                    for (int i = 0; i < ja.length(); i++) {
                        jo = ja.getJSONObject(i);
                        order_id[i]=jo.getString("order_id");

                        time[i]=jo.getString("time");
                        shedule[i]=jo.getString("shedule");

                        address[i] = jo.getString("address");
                        restaurant[i]=jo.getString("restaurant");

                        total[i]=jo.getString("total");
                        method[i]=jo.getString("method");
//                        order_type[i]=jo.getString("order_type");




                    }

                    myadapter adptr = new myadapter(getApplicationContext(),order_id,time,shedule, address,restaurant,total,method);

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
        String time[];
        String shedule[];

        String address[];
        String restaurant[];

        String total[];
        String method[];
//        String order_type[];


        myadapter(Context c, String order_id[],String time[],String shedule[], String address[],String restaurant[],String total[],String method[])
        {
            super(c,R.layout.card_pending_food_boarder,R.id.order_id,order_id);
            context=c;
            this.order_id=order_id;
            this.time=time;
            this.shedule=shedule;

            this.address=address;
            this.restaurant=restaurant;

            this.total=total;
            this.method=method;






        }
        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            LayoutInflater inflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=inflater.inflate(R.layout.card_pending_food_boarder,parent,false);





            TextView txtexpireTime=row.findViewById(R.id.expireTime);
            TextView txtshedule=row.findViewById(R.id.shedule);

            TextView txtfaddress=row.findViewById(R.id.address);
            TextView txtrestaurant=row.findViewById(R.id.restaurant);

            TextView txtftotal=row.findViewById(R.id.total);
            TextView txtmethod=row.findViewById(R.id.method);
            TextView txtorderId=row.findViewById(R.id.order_id);
            //TextView txtorder_type=row.findViewById(R.id.order_type);
            btnAccept=row.findViewById(R.id.viewbtn);
            btnConfirm=row.findViewById(R.id.confirm);




            txtorderId.setText(order_id[position]);
            txtexpireTime.setText(time[position]);



            //txtexpireTime.setText(expireTime[position]);
            txtshedule.setText(shedule[position]);

            txtfaddress.setText(address[position]);
            txtrestaurant.setText(restaurant[position]);

            txtftotal.setText(total[position]);
            txtmethod.setText(method[position]);
            //   txtorder_type.setText(order_type[position]);
            Oid=order_id[position];

            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder alert=new AlertDialog.Builder(PendingOrder.this);
                    alert.setMessage("Do you want to cancel this order?");
                    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent=new Intent(PendingOrder.this,CancelPendingOB.class);
                            intent.putExtra("order_id",order_id[position]);
                            startActivity(intent);
                        }
                    }).setNegativeButton("Cancel",null).setCancelable(false);

                    AlertDialog alertDialog=alert.create();
                    alertDialog.show();



                }
            });
            btnAccept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(PendingOrder.this,ViewPendingOrderB.class);
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