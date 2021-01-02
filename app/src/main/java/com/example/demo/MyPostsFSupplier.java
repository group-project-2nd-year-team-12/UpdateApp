package com.example.demo;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyPostsFSupplier extends AppCompatActivity {


    String  emailShared;
    private static final String apiurl="http://10.0.2.2/Android/files/postfs.php";
    ListView lv;


    private static String title[];
    private static String address[];
    private static String rating[];
    private static String lifespan[];
    private static String post_amount[];
    private static String image[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_posts_f_supplier);

        SharedPreferences sp=getApplicationContext().getSharedPreferences("details", Context.MODE_PRIVATE);
        emailShared=sp.getString("username","No name");

        ///set the toolbar
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lv=(ListView)findViewById(R.id.listpostsfs);

        fetch_data_into_array(lv);


    }
    ///create menu item in toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.example_menu,menu);
        return true;
    }


    ////click menu item in menu


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.item1:
                Intent intent=new Intent(this,HomeFSupplier.class);
                startActivity(intent);
                  //    Toasty.info(this,"Home page is again",Toast.LENGTH_LONG).show();
                Toast.makeText(this,"Home page is selected",Toast.LENGTH_LONG).show();
                break;

            case R.id.item2:
                SharedPreferences sharedPreferences=getSharedPreferences("details",MODE_PRIVATE);
                if (sharedPreferences.contains("username")){
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.remove("username");
                    Intent intent1=new Intent(this,MainActivity.class);
                    startActivity(intent1);
                    Toast.makeText(this,"Succesfully Logout",Toast.LENGTH_LONG).show();
                }

                //   Toasty.success(this,"Logout succesfully",Toast.LENGTH_LONG).show();
                //
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    public void fetch_data_into_array(View view)
    {
        String qur="?email="+emailShared;

        class  dbManager extends AsyncTask<String,Void,String>
        {
            protected void onPostExecute(String data)
            {
                try {
                    JSONArray ja = new JSONArray(data);
                    JSONObject jo=null ;

                    title= new String[ja.length()];
                    address= new String[ja.length()];
                    rating= new String[ja.length()];
                    lifespan = new String[ja.length()];
                    post_amount = new String[ja.length()];
                    image = new String[ja.length()];

                    for (int i = 0; i < ja.length(); i++) {
                        jo = ja.getJSONObject(i);
                        title[i] = jo.getString("ad_title");
                        address[i] = jo.getString("address");
                        rating[i] = jo.getString("rating");;
                        lifespan[i] = jo.getString("lifespan");
                        post_amount[i] = jo.getString("post_amount");
                        image[i] ="http://10.0.2.2/Android/files/" + jo.getString("image");;
                    }

                    myadapter adptr = new myadapter(getApplicationContext(),title,address,rating,lifespan,post_amount,image);
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

        String title[];
        String address[];
        String rating[];
        String lifespan[];
        String post_amount[];
        String image[];

        myadapter(Context c,String title[],String address[],String rating[],String lifespan[],String post_amount[],String image[])
        {
            super(c,R.layout.single_post_fs,R.id.title,title);
            context=c;
            this.title=title;
            this.address=address;
            this.rating=rating;
            this.lifespan=lifespan;
            this.post_amount=post_amount;
            this.image=image;



        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            LayoutInflater inflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=inflater.inflate(R.layout.single_post_fs,parent,false);

            //ImageView img=row.findViewById(R.id.img1);


            TextView txttitle=row.findViewById(R.id.title);
            TextView txtaddress=row.findViewById(R.id.address);

            TextView txtrating=row.findViewById(R.id.rating);
            TextView txtlifespan=row.findViewById(R.id.lifespan);
            TextView txtpost_amount=row.findViewById(R.id.pamount);
            ImageView imgimage=row.findViewById(R.id.imgpostfs);



            String url=image[position];
//            txtname.setText(first_name[position]+" "+last_name[position]);
//            txttitlt.setText(first_name[position]+" "+last_name[position]);
//            txtemail.setText(email[position]);
//            txtinstitute.setText(institute[position]);
            txttitle.setText(title[position]);
            txtaddress.setText(address[position]);
            txtrating.setText(rating[position]);
            txtlifespan.setText(lifespan[position]);
            txtpost_amount.setText(post_amount[position]);





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
                        Bitmap resized = Bitmap.createScaledBitmap(myBitmap, 300, 300, true);
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
            ImageLoadTask obj=new ImageLoadTask(url,imgimage);
            obj.execute();

            return row;
        }
    }








}