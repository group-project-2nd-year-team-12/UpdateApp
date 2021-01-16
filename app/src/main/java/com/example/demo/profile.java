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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import es.dmoral.toasty.Toasty;

public class profile extends AppCompatActivity  {

//    SharedPreferences sp=getApplicationContext().getSharedPreferences("details", Context.MODE_PRIVATE);
//   String getemail=sp.getString("details","No email");

//TextView textname,textemail,textinstitute,texttelephone;
//Button btnEdit;
//String email;
    private   String url1="http://10.0.2.2/Android/files/profile.php";
    ListView lv;

    private static String email[];

    private static String first[];
    private static String last[];
    private  static String institute[];
   // private static String telephone[];
    private static String image[];
    String email_user;
  //  ArrayList<String>holder=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

//        textname=findViewById(R.id.name);
//        textemail=findViewById(R.id.email);
//        textinstitute=findViewById(R.id.address);
//        texttelephone=findViewById(R.id.telephone);


    ///set the toolbar in previous icon
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setTitle("Bodima.lk");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences sp=getApplicationContext().getSharedPreferences("details", Context.MODE_PRIVATE);
      email_user=sp.getString("details","No email");
        lv=(ListView) findViewById(R.id.lv);

        fetch_data_into_array(lv);
    }

   public void fetch_data_into_array(View view){
       String qur="?username="+email_user;

        class dbManager extends AsyncTask<String,Void, String>
        {
            protected void onPostExecute(String data){
                try {
                    JSONArray jsonArray=new JSONArray(data);
                    JSONObject jsonObject=null;

                    email=new String[jsonArray.length()];
                    first=new String[jsonArray.length()];
                    last=new String[jsonArray.length()];
                    institute=new String[jsonArray.length()];
                  //  telephone=new String[jsonArray.length()];
                    image=new String[jsonArray.length()];

                    for (int i=0;i<=jsonArray.length();i++){
                        jsonObject=jsonArray.getJSONObject(i);
                        email[i]=jsonObject.getString("email");
                        first[i]=jsonObject.getString("first_name");
                        last[i]=jsonObject.getString("last_name");
                        institute[i]=jsonObject.getString("institute");
                        //telephone[i]=jsonObject.getString("telephone");
                        image[i]="http://10.0.2.2/Android/files/" + jsonObject.getString("profileimage");

                    }
                    profile.myadapter adptr = new profile.myadapter(getApplicationContext(),email, first, last,institute,image);
                    lv.setAdapter(adptr);
                } catch (JSONException ex) {
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            protected String doInBackground(String... strings) {
                try {
                    URL url=new URL(strings[0]);
                    HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

                    StringBuffer data=new StringBuffer();
                    String line;

                    while ((line=bufferedReader.readLine())!=null){
                        data.append(line+"\n");
                    }
                    bufferedReader.close();
                    return  data.toString();


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        dbManager obj=new dbManager();
        obj.execute(url1,qur);

   }

  /*  private void fetchdata() {
       // String qry="?username="+email;
       class dbManager extends AsyncTask<String,Void,String>{

           protected void onPostExecute(String data){


           }
           @Override
           protected String doInBackground(String... strings) {
               try {
                   URL url=new URL(strings[0]);
                   HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                   BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
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
    }
*/
    class myadapter extends ArrayAdapter<String>{
        Context context;
      String email[];
        String first[];
        String last[];
        String institute[];
        String telephone[];
        String image[];

        public myadapter(@NonNull Context context,String email[], String first[],String last[],String institute[],String image[]) {

            super(context,R.layout.single_row_profile,R.id.email,email);
            this.email=email;
            this.first=first;
            this.last=last;
            this.institute=institute;
         //   this.telephone=telephone;
            this.image=image;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            LayoutInflater inflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=inflater.inflate(R.layout.single_row_profile,parent,false);

            //ImageView img=row.findViewById(R.id.img1);
            ImageView img=row.findViewById(R.id.imageid);
            TextView txtname=row.findViewById(R.id.name);
            TextView txtemail=row.findViewById(R.id.email);
            TextView txtins=row.findViewById(R.id.address);
        //    TextView txttel=row.findViewById(R.id.telephone);

            String url=image[position];
            txtname.setText(first[position]+" "+last[position]);
            txtins.setText(institute[position]);
           // txttel.setText(telephone[position]);
            txtemail.setText(email[position]);





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
                        Bitmap resized = Bitmap.createScaledBitmap(myBitmap, 100, 100, true);
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
            ImageLoadTask obj=new ImageLoadTask(url,img);
            obj.execute();

            return row;
        }

    }


    ///set the menu item in toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.example_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }











    ///click the menu icon the result what to do

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.item1:
                Intent intent1=new Intent(this,HomeBO.class);
                startActivity(intent1);
                Toasty.info(this,"Home page is again",Toast.LENGTH_LONG).show();
                //Toast.makeText(this,"Home page is selected",Toast.LENGTH_LONG).show();
                //return true;
                break;

            case R.id.item2:
                SharedPreferences sharedPreferences=getSharedPreferences("details",MODE_PRIVATE);
                if (sharedPreferences.contains("username")){
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.remove("username");
                    Intent intent=new Intent(this,MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(this,"Logout succesfully",Toast.LENGTH_LONG).show();
                  //  Toasty.success(this,"Succesfully Logout",Toast.LENGTH_LONG).show();
                }


                //return true;
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
