package com.example.demo;

        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AlertDialog;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.BufferedReader;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;

public class DinnerFood extends AppCompatActivity {
    private static final String apiurl="http://10.0.2.2/Android/files/selectFoodD.php";
    ListView lv;
    TextView txt;
    String F_post_id;
    String first_name,emailShared,title;

//selectFoodB.php

    String type="dinner";

    private static String product_name[];
    private static String image[];
    private static String price[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dinner_food);

        SharedPreferences sp=getApplicationContext().getSharedPreferences("details", Context.MODE_PRIVATE);
        emailShared=sp.getString("username","No name");

      //  txt=findViewById(R.id.ishan);
        Intent intent=getIntent();
        F_post_id=intent.getStringExtra("F_post_id");
        title=intent.getStringExtra("title");
        //txt.setText(title);
        //  txt.setText(intent.getStringExtra("F_post_id"));

        //set the toolbar
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lv=(ListView) findViewById(R.id.listfoodB);
        fetch_data_into_array(lv);

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"You can add that item",Toast.LENGTH_LONG).show();
//            }
//        });

    }

    private void fetch_data_into_array(View view) {

        String qur="?F_post_id="+F_post_id;
        class dbManager extends AsyncTask<String,Void,String> {

            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONArray ja=new JSONArray(s);
                    JSONObject jo=null;

                    product_name=new String[ja.length()];
                    image=new String[ja.length()];
                    price=new String[ja.length()];

                    for (int i=0;i<ja.length();i++){
                        jo=ja.getJSONObject(i);
                        product_name[i]=jo.getString("product_name");
                        image[i]="http://10.0.2.2/Android/files/" +jo.getString("image");
                        price[i]=jo.getString("price");
                    }
                    myAdapter adapter=new myAdapter(getApplicationContext(),product_name,image,price);
                    lv.setAdapter(adapter);




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


    class myAdapter extends ArrayAdapter<String> {
        Context context;
        String product_name[];
        String image[];
        String price[];

        public myAdapter(Context context,String product_name[], String image[],String price[]) {
            super(context, R.layout.card_food_items_in_order,R.id.product_name, product_name);

            this.product_name=product_name;
            this.image=image;
            this.price=price;
        }


        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            final LayoutInflater inflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=inflater.inflate(R.layout.card_food_items_in_order,parent,false);

            //ImageView img=row.findViewById(R.id.img1);



            TextView txtproduct_name=row.findViewById(R.id.product_name);
            TextView txtprice=row.findViewById(R.id.price);
            ImageView imgfood=row.findViewById(R.id.image);

            Button btn=row.findViewById(R.id.btnAdd);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {





                    AlertDialog.Builder alert=new AlertDialog.Builder(DinnerFood.this);
                    alert.setMessage("Are you want to order "+product_name[position]+"? ").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(),"You can add that item",Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(DinnerFood.this,InsideFoodPost.class);
                            intent.putExtra("F_post_id",F_post_id);
                            intent.putExtra("product_name",product_name[position]);
                            intent.putExtra("image",image[position]);
                            intent.putExtra("price",price[position]);
                            intent.putExtra("type",type);
                            intent.putExtra("title",title);

                            startActivity(intent);

                        }
                    }).setNegativeButton("Cancel",null).setCancelable(false);

                    AlertDialog alertDialog=alert.create();
                    alert.show();



                }
            });



            String url=image[position];
            txtproduct_name.setText(product_name[position]);

            txtprice.setText("Rs: "+price[position]);





            class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {
                private String url;
                private ImageView imageView;

                public ImageLoadTask(String url, ImageView imageView) {
                    this.url = url;
                    this.imageView = imageView;
                }

                @Override
                protected Bitmap doInBackground(Void... params) {
                    try {
                        URL connection = new URL(url);
                        InputStream input = connection.openStream();
                        Bitmap myBitmap = BitmapFactory.decodeStream(input);
                        Bitmap resized = Bitmap.createScaledBitmap(myBitmap, 1200, 800, true);
                        return resized;
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    }
                    return null;
                }
                @Override
                protected void onPostExecute(Bitmap result) {
                    super.onPostExecute(result);
                    imageView.setImageBitmap(result);
                }
            }
            ImageLoadTask obj=new ImageLoadTask(url,imgfood);
            obj.execute();

            return row;
        }

    }
}