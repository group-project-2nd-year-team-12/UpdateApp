package com.example.demo;

        import android.app.AlertDialog;
        import android.content.Context;
        import android.os.AsyncTask;

        import java.io.BufferedReader;
        import java.io.BufferedWriter;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.io.OutputStream;
        import java.io.OutputStreamWriter;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.net.URLEncoder;

public class BackgroundFoodReqB extends AsyncTask<String,Void,String> {


    Context context;
    BackgroundFoodReqB(Context ctx){context=ctx;}
    AlertDialog alertDialog;


    @Override
    protected String doInBackground(String... strings) {

        String type=strings[0];
        String insert_url="http://10.0.2.2/Android/files/InsertFoodB.php";
        if (type.equals("InsertFoodB")){


            try {

                String email=strings[1];
                String address=strings[2];
                String first_name=strings[3];
                String last_name=strings[4];
                String term=strings[5];
                String order_type=strings[6];
                String shedule=strings[7];
                String title=strings[8];
                String F_post_id=strings[9];
                String price=strings[10];
                String phone=strings[11];
                String method=strings[12];
                String product_name=strings[13];


                URL url=new URL(insert_url);

                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("address","UTF-8")+"="+URLEncoder.encode(address,"UTF-8")+"&"+
                        URLEncoder.encode("first_name","UTF-8")+"="+URLEncoder.encode(first_name,"UTF-8")+"&"+
                        URLEncoder.encode("last_name","UTF-8")+"="+URLEncoder.encode(last_name,"UTF-8")+"&"+
                        URLEncoder.encode("term","UTF-8")+"="+URLEncoder.encode(term,"UTF-8")+"&"+
                        URLEncoder.encode("order_type","UTF-8")+"="+URLEncoder.encode(order_type,"UTF-8")+"&"+
                        URLEncoder.encode("shedule","UTF-8")+"="+URLEncoder.encode(shedule,"UTF-8")+"&"+
                        URLEncoder.encode("title","UTF-8")+"="+URLEncoder.encode(title,"UTF-8")+"&"+
                        URLEncoder.encode("F_post_id","UTF-8")+"="+URLEncoder.encode(F_post_id,"UTF-8")+"&"+
                        URLEncoder.encode("price","UTF-8")+"="+URLEncoder.encode(price,"UTF-8")+"&"+
                        URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8")+"&"+
                        URLEncoder.encode("method","UTF-8")+"="+URLEncoder.encode(method,"UTF-8")+"&"+

                        URLEncoder.encode("product_name","UTF-8")+"="+URLEncoder.encode(product_name,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return result;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
