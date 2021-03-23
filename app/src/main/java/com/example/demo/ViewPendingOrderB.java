package com.example.demo;

        import android.content.Context;
        import android.content.Intent;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.view.LayoutInflater;
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

        import org.json.JSONArray;
        import org.json.JSONObject;

        import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;

public class ViewPendingOrderB extends AppCompatActivity {
    private static final String apiurl="http://10.0.2.2/Android/files/viewSOitems.php";
    ListView lv;
    String  emailShared;

    private static String item_name[];
    private static String quantity[];
    String order_id,method;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pending_order_b);
        TextView textView=findViewById(R.id.ishan);
        Intent intent=getIntent();
        order_id=intent.getStringExtra("order_id");
        method=intent.getStringExtra("method");
//        textView.setText(intent.getStringExtra("order_id"));
        //   textView.setText(order_id);
        //viewSOitems.php


        //set the toolbar
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lv=findViewById(R.id.list_food_items);

        fetch_data_into_array(lv);
    }


    public void fetch_data_into_array(View view)
    {
        String qur="?order_id="+order_id;


        class  dbManager extends AsyncTask<String,Void,String>
        {
            protected void onPostExecute(String data)
            {
                try {
                    JSONArray ja = new JSONArray(data);
                    JSONObject jo = null;


                    item_name=new String[ja.length()];
                    quantity=new String[ja.length()];

                    for (int i = 0; i < ja.length(); i++) {
                        jo = ja.getJSONObject(i);

                        item_name[i]=jo.getString("item_name");
                        quantity[i]=jo.getString("quantity");

                    }


                    myadapter adptr = new myadapter(getApplicationContext(),item_name, quantity);
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

        String item_name[];
        String quantity[];

        myadapter(Context c,String item_name[], String quantity[])
        {
            super(c,R.layout.card_pending_order_boarder,R.id.item,item_name);
            context=c;
            this.item_name=item_name;
            this.quantity=quantity;

        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            LayoutInflater inflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=inflater.inflate(R.layout.card_pending_order_boarder,parent,false);

            TextView txtitem=row.findViewById(R.id.item);
            TextView txtquantity=row.findViewById(R.id.quantity);


            txtitem.setText(item_name[position]);
            txtquantity.setText(quantity[position]);


            return row;
        }
    }


}