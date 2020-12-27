package com.example.demo;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;

        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.net.Uri;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Adapter;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import org.json.JSONArray;
        import org.json.JSONObject;

        import java.io.BufferedReader;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.util.ArrayList;

public class ProfilePageB extends AppCompatActivity {

    private static final String apiurl="http://10.0.2.2/Android/files/profile.php";
    ListView lv;


    private static String email[];
    private static String first_name[];
    private static String last_name[];
    private static String institute[];
    private static String telephone[];
    private static String profile[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page_b);

        ///set the toolbar
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lv=(ListView)findViewById(R.id.lv);

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
                Intent intent=new Intent(this,HomeBO.class);
                startActivity(intent);
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

        class  dbManager extends AsyncTask<String,Void,String>
        {
            protected void onPostExecute(String data)
            {
                try {
                    JSONArray ja = new JSONArray(data);
                    JSONObject jo = null;

                    email= new String[ja.length()];
                    first_name= new String[ja.length()];
                    last_name= new String[ja.length()];
                    institute = new String[ja.length()];
                    telephone = new String[ja.length()];
                    profile = new String[ja.length()];

                    for (int i = 0; i < ja.length(); i++) {
                        jo = ja.getJSONObject(i);
                        email[i] = jo.getString("email");
                        first_name[i] = jo.getString("first_name");
                        last_name[i] = jo.getString("last_name");;
                        institute[i] = jo.getString("institute");
                        telephone[i] = jo.getString("telephone");
                        profile[i] ="http://10.0.2.2/Android/files/" + jo.getString("image");;
                    }

                    myadapter adptr = new myadapter(getApplicationContext(),email,first_name, last_name, institute,telephone,profile);
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
        String email[];
        String first_name[];
        String last_name[];
        String institute[];
        String telephone[];
        String profile[];

        myadapter(Context c,String email[],String first_name[], String last_name[], String institute[],String telephone[],String profile[])
        {
            super(c,R.layout.row_profile,R.id.email,email);
            context=c;
            this.email=email;
            this.first_name=first_name;
            this.last_name=last_name;
            this.institute=institute;
            this.telephone=telephone;
            this.profile=profile;



        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            LayoutInflater inflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=inflater.inflate(R.layout.row_profile,parent,false);

            //ImageView img=row.findViewById(R.id.img1);


            TextView txtemail=row.findViewById(R.id.email);
            TextView txtname=row.findViewById(R.id.name);

            TextView txtinstitute=row.findViewById(R.id.institute);
            TextView txttel=row.findViewById(R.id.telephone);
            ImageView imgprofile=row.findViewById(R.id.profile);

            TextView txttitlt=row.findViewById(R.id.title);

            String url=profile[position];
            txtname.setText(first_name[position]+" "+last_name[position]);
            txttitlt.setText(first_name[position]+" "+last_name[position]);
            txtemail.setText(email[position]);
            txtinstitute.setText(institute[position]);
            txttel.setText(telephone[position]);





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
                        Bitmap resized = Bitmap.createScaledBitmap(myBitmap, 200, 200, true);
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
            ImageLoadTask obj=new ImageLoadTask(url,imgprofile);
            obj.execute();

            return row;
        }
    }








}