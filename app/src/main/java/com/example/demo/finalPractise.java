package com.example.demo;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.net.Uri;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.view.LayoutInflater;
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

public class finalPractise extends AppCompatActivity {

    private static final String apiurl="http://10.0.2.2/Android/files/post.php";
    ListView lv;


    private static String lane[];
    private static String categery[];
    private static String girlsBoys[];
    private static String city[];
    private static String image[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_practise);


        lv=(ListView)findViewById(R.id.lv);

        fetch_data_into_array(lv);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = lv.getItemAtPosition(position).toString();

                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
        });
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

                    categery= new String[ja.length()];
                    girlsBoys= new String[ja.length()];
                    lane= new String[ja.length()];
                    city = new String[ja.length()];
                    image = new String[ja.length()];

                    for (int i = 0; i < ja.length(); i++) {
                        jo = ja.getJSONObject(i);
                        categery[i] = jo.getString("category");
                        girlsBoys[i] = jo.getString("girlsBoys");
                        lane[i] = jo.getString("lane");;
                        city[i] = jo.getString("city");
                        image[i] ="http://10.0.2.2/Android/files/" + jo.getString("image");;
                    }

                    myadapter adptr = new myadapter(getApplicationContext(),categery,girlsBoys, lane, city,image);
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
        String cat[];
        String gb[];
        String ttl[];
        String dsc[];
        String rimg[];

        myadapter(Context c,String cat[],String gb[], String ttl[], String dsc[],String rimg[])
        {
            super(c,R.layout.row_profile,R.id.name,ttl);
            context=c;
            this.cat=cat;
            this.gb=gb;
            this.rimg=rimg;
            this.ttl=ttl;
            this.dsc=dsc;

        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
        {
            LayoutInflater inflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=inflater.inflate(R.layout.row_profile,parent,false);

            //ImageView img=row.findViewById(R.id.img1);
            ImageView img=row.findViewById(R.id.profile);
            TextView tv3=row.findViewById(R.id.address);
            TextView tv4=row.findViewById(R.id.telephone);
            TextView tv1=row.findViewById(R.id.name);
            TextView tv2=row.findViewById(R.id.email);

            String url=rimg[position];
            tv1.setText(ttl[position]);
            tv2.setText(dsc[position]);
            tv3.setText(cat[position]);
            tv4.setText(gb[position]);





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
            ImageLoadTask obj=new ImageLoadTask(url,img);
            obj.execute();

            return row;
        }
    }
}