package com.example.demo;

import android.content.Context;
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

public class NewRequestBO extends AppCompatActivity {

    private static final String apiurl="http://10.0.2.2/Android/files/newRequests.php";


    private String request_id[];
    private String student_name[];
    private String request_data[];
    private String message[];
    private String boarding_city[];
    private String image[];

    private String student_email[];


    String Request_id;


     String emailShared;
    ListView lv;
    Button btnAccept;
    TextView txtishan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_request_b_o);

        SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("details", Context.MODE_PRIVATE);
        emailShared=sharedPreferences.getString("username","no value");

        ///set the toolbar
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        lv=(ListView) findViewById(R.id.listnewrequest);
        btnAccept=findViewById(R.id.accept);
        txtishan=findViewById(R.id.ishan);



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
                    student_email=new String[jsonArray.length()];
                    request_data=new String[jsonArray.length()];
                    message=new String[jsonArray.length()];
                    boarding_city=new String[jsonArray.length()];
                    image=new String[jsonArray.length()];

                    student_name=new String[jsonArray.length()];






                    for (int i=0;i<jsonArray.length();i++){

                        jsonObject=jsonArray.getJSONObject(i);

                        request_id[i]=jsonObject.getString("request_id");
                        student_email[i]=jsonObject.getString("student_email");
                        request_data[i]=jsonObject.getString("date");
                        message[i]=jsonObject.getString("message");
                        boarding_city[i]=jsonObject.getString("city");
                        image[i]=jsonObject.getString("image");
                        student_name[i]=jsonObject.getString("first_name");
                        image[i] ="http://10.0.2.2/Android/files/" + jsonObject.getString("image");;
                    }
                   MyAdapter myAdapter=new MyAdapter(getApplicationContext(),request_id,student_name,request_data,message,boarding_city,image,student_name);
                    lv.setAdapter(myAdapter);

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

        dbManager obj=new dbManager();
        obj.execute(apiurl+qur);


    }





        //AcceptReqBO.php



    class MyAdapter extends ArrayAdapter<String>{
        Context context;
        String request_id[];
        String student_email[];
        String request_data[];
        String message[];
        String boarding_city[];
        String image[];
        String student_name[];

        public MyAdapter(Context context, String request_id[],String student_email[],String request_data[],String message[],String boarding_city[],String image[],String student_name[]) {
            super(context, R.layout.list_new_request_bo,R.id.request_id,request_id);

            this.context=context;
            this.request_id=request_id;
            this.student_email=student_email;
            this.request_data=request_data;
            this.message=message;
            this.boarding_city=boarding_city;
            this.image=image;
            this.student_name=student_name;

        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {


            LayoutInflater inflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=inflater.inflate(R.layout.list_new_request_bo,parent,false);


            TextView txtrequest_id=row.findViewById(R.id.request_id);
            TextView txtstudent_email=row.findViewById(R.id.student_email);
            TextView txtrequest_data=row.findViewById(R.id.request_data);
            TextView txtmessage=row.findViewById(R.id.message);
            TextView txtboarding_city=row.findViewById(R.id.city);
         //   Button btnAccept=row.findViewById(R.id.accept);

            btnAccept=row.findViewById(R.id.accept);

            ImageView imgpost=row.findViewById(R.id.requestimg);
            TextView txtstudent_name=row.findViewById(R.id.student_name);

              txtishan=findViewById(R.id.ishan);


            String url=image[position];
            txtrequest_id.setText("Request id:"+request_id[position]);
            txtstudent_email.setText(student_email[position]);
            txtrequest_data.setText(request_data[position]);
            txtmessage.setText(message[position]);
            txtboarding_city.setText(boarding_city[position]);
            txtstudent_name.setText(student_name[position]);

            Request_id=request_id[position];


             btnAccept.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      txtishan.setText(Request_id);
                      String type="AcceptReqBO";

//                      BackgroundNewReqBO backgroundNewReqBO=new BackgroundNewReqBO(this);
//                  backgroundNewReqBO.execute(type,Request_id);
//                      try {
//                          String result=backgroundNewReqBO.execute(type,Request_id).get();
//                          if (result.equals("Successfully")){
//                             // Intent intent=new Intent(getApplicationContext(),ProfilePageBO.class);
//                             // startActivity(intent);
//                              Toast.makeText(getApplicationContext(),"Successfully Updated",Toast.LENGTH_LONG).show();
//                          }
//                      } catch (ExecutionException e) {
//
//                          e.printStackTrace();
//                      } catch (InterruptedException e) {
//                          e.printStackTrace();
//                      }

                  }
              });












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
            ImageLoadTask obj=new ImageLoadTask(url,imgpost);
            obj.execute();

            return row;


        }




    }


}