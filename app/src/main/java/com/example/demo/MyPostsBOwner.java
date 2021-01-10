//package com.example.demo;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Adapter;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//
//public class MyPostsBOwner extends AppCompatActivity {
//
//
//    String  emailShared;
//    private static final String apiurl="http://10.0.2.2/Android/files/MyPostsBO.php";
//    ListView lv;
//
//
//    private static String category[];
//    private static String girlsBoys[];
//    private static String person_count[];
//    private static String image[];
//    private static  String description[];
//    private static String review[];
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_my_posts_b_owner);
//
//        SharedPreferences sp=getApplicationContext().getSharedPreferences("details", Context.MODE_PRIVATE);
//        emailShared=sp.getString("username","No name");
//
//        ///set the toolbar
//        Toolbar toolbar=findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        lv=findViewById(R.id.listposts);
//
//        fetch_data_into_array(lv);
//
//
//    }
//    ///create menu item in toolbar
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater=getMenuInflater();
//        inflater.inflate(R.menu.example_menu,menu);
//        return true;
//    }
//    ////click menu item in menu
//
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId())
//        {
//            case R.id.item1:
//                Intent intent=new Intent(this,HomeBO.class);
//                startActivity(intent);
//                //      Toasty.info(this,"Home page is again",Toast.LENGTH_LONG).show();
//                // Toast.makeText(this,"Home page is selected",Toast.LENGTH_LONG).show();
//                break;
//
//            case R.id.item2:
//                SharedPreferences sharedPreferences=getSharedPreferences("details",MODE_PRIVATE);
//                if (sharedPreferences.contains("username")){
//                    SharedPreferences.Editor editor=sharedPreferences.edit();
//                    editor.remove("username");
//                    Intent intent1=new Intent(this,MainActivity.class);
//                    startActivity(intent1);
//                    Toast.makeText(this,"Succesfully Logout",Toast.LENGTH_LONG).show();
//                }
//
//                //   Toasty.success(this,"Logout succesfully",Toast.LENGTH_LONG).show();
//                //
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//
//
//
//
//    public void fetch_data_into_array(View view)
//    {
//        String qur="?username="+emailShared;
//
//        class  dbManager extends AsyncTask<String,Void,String>
//        {
//            protected void onPostExecute(String data)
//            {
//                try {
//                    JSONArray ja = new JSONArray(data);
//                    JSONObject jo = null;
//                    category=new String[ja.length()];
//                    girlsBoys=new String[ja.length()];
//                    person_count=new String[ja.length()];
//                    image=new String[ja.length()];
//                    description=new String[ja.length()];
//                    review=new String[ja.length()];
//
//
//
//                    for (int i = 0; i < ja.length(); i++) {
//                        jo = ja.getJSONObject(i);
//
//
//                        category[i]=jo.getString("category");
//                        girlsBoys[i]=jo.getString("girlsBoys");
//                        person_count[i]=jo.getString("person_count");
//                        image[i]="http://10.0.2.2/Android/files/"+jo.getString("image");
//                        description[i]=jo.getString("description");
//                        review[i]=jo.getString("review");
//
//
//
//                    }
//
//                    MyPostsBOwner.myadapter adptr = new myadapter(getApplicationContext(),category,girlsBoys,person_count,image);
//                    lv.setAdapter(adptr);
//
//                } catch (Exception ex) {
//                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            protected String doInBackground(String... strings)
//            {
//                try {
//                    URL url = new URL(strings[0]);
//                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//
//                    StringBuffer data = new StringBuffer();
//                    String line;
//
//                    while ((line = br.readLine()) != null) {
//                        data.append(line + "\n");
//                    }
//                    br.close();
//
//                    return data.toString();
//
//                } catch (Exception ex) {
//                    return ex.getMessage();
//                }
//
//            }
//
//        }
//        dbManager obj=new dbManager();
//        obj.execute(apiurl+qur);
//
//    }
//
//    class myadapter extends ArrayAdapter<String>
//    {
//        Context context;
//        String category[];
//        String girlsBoys[];
//        String person_count[];
//        String image[];
//        String description[];
//        String review[];
//
//        myadapter(Context c,String category[], String girlsBoys[], String person_count[], String image[])
//        {
//            super(c,R.layout.single_post_bo,R.id.pcount,person_count);
//            context=c;
//            this.category=category;
//            this.girlsBoys=girlsBoys;
//            this.person_count=person_count;
//            this.image=image;
//            this.review=review;
//            this.description=description;
//
//
//
//        }
//        @NonNull
//        @Override
//        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
//        {
//            LayoutInflater inflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            View row=inflater.inflate(R.layout.single_post_bo,parent,false);
//
//            TextView txtcategory=row.findViewById(R.id.categery);
//            TextView txtgirlsBoys=row.findViewById(R.id.girlsBoys);
//            TextView txtperson_count=row.findViewById(R.id.pcount);
//            TextView txtreview=row.findViewById(R.id.postreview);
//            TextView txtdescription=row.findViewById(R.id.description);
//            ImageView imgpost=row.findViewById(R.id.imgpostbo);
//
//
//            String url=image[position];
//            txtcategory.setText(category[position]);
//            txtgirlsBoys.setText(girlsBoys[position]);
//            txtperson_count.setText(person_count[position]);
//            txtreview.setText(review[position]);
//            txtdescription.setText(description[position]);
//
//
//
//
//
//            class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {
//                private String url;
//                private ImageView imageView;
//
//                public ImageLoadTask(String url, ImageView imageView) {
//                    this.url = url;
//                    this.imageView = imageView;
//                }
//
//                @Override
//                protected Bitmap doInBackground(Void... params) {
//                    try {
//                        URL connection = new URL(url);
//                        InputStream input = connection.openStream();
//                        Bitmap myBitmap = BitmapFactory.decodeStream(input);
//                        Bitmap resized = Bitmap.createScaledBitmap(myBitmap, 200, 200, true);
//                        return resized;
//                    } catch (Exception e) {
//                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
//                    }
//                    return null;
//                }
//                @Override
//                protected void onPostExecute(Bitmap result) {
//                    super.onPostExecute(result);
//                    imageView.setImageBitmap(result);
//                }
//            }
//             ImageLoadTask obj=new ImageLoadTask(url,imgpost);
//              obj.execute();
//
//            return row;
//        }
//    }
//
//
//
//
//
//
//
//
//}
//








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

public class MyPostsBOwner extends AppCompatActivity {


    String  emailShared;
    private static final String apiurl="http://10.0.2.2/Android/files/MyPostsBO.php";
    ListView lv;


    private static String description[];
    private static String review[];
   private static String category[];
    private static String girlsBoys[];
    private static String person_count[];
    private static String image[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_posts_b_owner);

        SharedPreferences sp=getApplicationContext().getSharedPreferences("details", Context.MODE_PRIVATE);
        emailShared=sp.getString("username","No name");

        ///set the toolbar
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lv=(ListView)findViewById(R.id.listposts);

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
//                Intent intent=new Intent(this,HomeBO.class);
//                startActivity(intent);
                //      Toasty.info(this,"Home page is again",Toast.LENGTH_LONG).show();
                // Toast.makeText(this,"Home page is selected",Toast.LENGTH_LONG).show();
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
        String qur="?username="+emailShared;

        class  dbManager extends AsyncTask<String,Void,String>
        {
            protected void onPostExecute(String data)
            {
                try {
                    JSONArray ja = new JSONArray(data);
                    JSONObject jo=null ;

                    description= new String[ja.length()];
                    review= new String[ja.length()];
                    category= new String[ja.length()];
                    girlsBoys = new String[ja.length()];
                    person_count = new String[ja.length()];
                    image = new String[ja.length()];

                    for (int i = 0; i < ja.length(); i++) {
                        jo = ja.getJSONObject(i);
                        description[i] = jo.getString("description");
                        review[i] = jo.getString("review");
                        category[i] = jo.getString("category");;
                        girlsBoys[i] = jo.getString("girlsBoys");
                        person_count[i] = jo.getString("person_count");
                        image[i] ="http://10.0.2.2/Android/files/" + jo.getString("image");;
                    }

                    myadapter adptr = new myadapter(getApplicationContext(),description,review,category,girlsBoys,person_count,image);
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
        String description[];
        String review[];
        String category[];
        String girlsBoys[];
        String person_count[];
        String image[];

        myadapter(Context c,String description[],String review[],String category[],String girlsBoys[],String person_count[],String image[])
        {
            super(c,R.layout.single_post_bo,R.id.description,description);
            context=c;
            this.description=description;
            this.review=review;
           this.category=category;
            this.girlsBoys=girlsBoys;
            this.person_count=person_count;
            this.image=image;



        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            LayoutInflater inflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=inflater.inflate(R.layout.single_post_bo,parent,false);

            //ImageView img=row.findViewById(R.id.img1);


            TextView txtdescription=row.findViewById(R.id.description);
            TextView txtreview=row.findViewById(R.id.postreview);

           TextView txtcategory=row.findViewById(R.id.category);
            TextView txtgirlsBoys=row.findViewById(R.id.girlsBoys);
            ImageView imgimage=row.findViewById(R.id.imgpostbo);

           TextView txtperson_count=row.findViewById(R.id.pcount);

            String url=image[position];
//            txtname.setText(first_name[position]+" "+last_name[position]);
//            txttitlt.setText(first_name[position]+" "+last_name[position]);
//            txtemail.setText(email[position]);
//            txtinstitute.setText(institute[position]);
            txtdescription.setText(description[position]);
            txtreview.setText(review[position]);
            txtcategory.setText(category[position]);
            txtgirlsBoys.setText(girlsBoys[position]);
            txtperson_count.setText(person_count[position]);





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
                        Bitmap resized = Bitmap.createScaledBitmap(myBitmap, 400, 300, true);
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