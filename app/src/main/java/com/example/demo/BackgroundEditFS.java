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

public class BackgroundEditFS extends AsyncTask<String,Void,String> {


    Context context;
    BackgroundEditFS(Context ctx){context=ctx;}
    AlertDialog alertDialog;


    @Override
    protected String doInBackground(String... strings) {

        String type=strings[0];
        String update_url="http://10.0.2.2/Android/files/updateProfileFS.php";
        if (type.equals("updateProfileFS")){


            try {

                String email=strings[1];
                String first_name=strings[2];
                String last_name=strings[3];
                String address=strings[4];
                String nic=strings[5];
                URL url=new URL(update_url);

                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("first_name","UTF-8")+"="+URLEncoder.encode(first_name,"UTF-8")+"&"+
                        URLEncoder.encode("last_name","UTF-8")+"="+URLEncoder.encode(last_name,"UTF-8")+"&"+
                        URLEncoder.encode("address","UTF-8")+"="+URLEncoder.encode(address,"UTF-8")+"&"+
                        URLEncoder.encode("nic","UTF-8")+"="+URLEncoder.encode(nic,"UTF-8");

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

