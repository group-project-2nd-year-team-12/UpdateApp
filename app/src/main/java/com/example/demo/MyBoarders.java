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

public class MyBoarders extends AppCompatActivity {


    String  emailShared;
    private static final String apiurl="http://10.0.2.2/Android/files/myboarders.php";
    ListView lv;

    private static String emailBoarder[];

    private static String first_name[];
    private static String last_name[];
    private  static  String institute[];
    private static String gender[];
    private static String telephone[];
    private static String city[];
    private static String profile[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_boarders);

        SharedPreferences sp=getApplicationContext().getSharedPreferences("details", Context.MODE_PRIVATE);
        emailShared=sp.getString("username","No name");

        ///set the toolbar
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lv=(ListView)findViewById(R.id.listviewboarder);

        fetch_data_into_array(lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),MyBordersInsideData.class);
                intent.putExtra("email",emailBoarder[position]);
                intent.putExtra("first_name",first_name[position]);
                intent.putExtra("last_name",last_name[position]);
                intent.putExtra("institute",institute[position]);
                intent.putExtra("gender",gender[position]);
                intent.putExtra("telephone",telephone[position]);

                startActivity(intent);

            }
        });


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
                Intent intent=new Intent(this,HomeBOwner.class);
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
        String qur="?username="+emailShared;

        class  dbManager extends AsyncTask<String,Void,String>
        {
            protected void onPostExecute(String data)
            {
                try {
                    JSONArray ja = new JSONArray(data);
                    JSONObject jo = null;


                    emailBoarder= new String[ja.length()];
                    first_name= new String[ja.length()];
                    last_name= new String[ja.length()];
                    profile = new String[ja.length()];
                    institute = new String[ja.length()];
                    gender = new String[ja.length()];
                    telephone = new String[ja.length()];
                    city = new String[ja.length()];



                    for (int i = 0; i < ja.length(); i++) {
                        jo = ja.getJSONObject(i);

                        emailBoarder[i]=jo.getString("email");
                        first_name[i] = jo.getString("first_name");
                        last_name[i] = jo.getString("last_name");;
                        profile[i] ="http://10.0.2.2/Android/files/" + jo.getString("image");;
                        institute[i]=jo.getString("institute");
                        gender[i]=jo.getString("gender");
                        telephone[i]=jo.getString("telephone");
                        city[i] = jo.getString("city");


                    }

                    myadapter adptr = new myadapter(getApplicationContext(),emailBoarder, first_name, last_name,profile,institute,gender,telephone,city);
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

        String emailboarder[];
        String first_name[];
        String last_name[];
        String city[];
        String institute[];
        String gender[];
        String telephone[];

        String profile[];

        myadapter(Context c,String emailboarder[], String first_name[], String last_name[], String profile[],String institute[],String gender[],String telephone[], String city[])
        {
            super(c,R.layout.single_boarder_list,R.id.txtcity,city);
            context=c;
            this.emailboarder=emailboarder;
            this.first_name=first_name;
            this.last_name=last_name;
            this.profile=profile;
            this.institute=institute;
            this.gender=gender;
            this.telephone=telephone;
            this.city=city;




        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            LayoutInflater inflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=inflater.inflate(R.layout.single_boarder_list,parent,false);

            //ImageView img=row.findViewById(R.id.img1);



            TextView txtname=row.findViewById(R.id.txtname);
            TextView txtcity=row.findViewById(R.id.txtcity);
            ImageView imgprofile=row.findViewById(R.id.imgboarder);



            String url=profile[position];
            txtname.setText(first_name[position]+" "+last_name[position]);

            txtcity.setText(city[position]+" boarder");





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
            ImageLoadTask obj=new ImageLoadTask(url,imgprofile);
            obj.execute();

            return row;
        }
    }








}