//package com.example.demo;
//
//
//import android.app.AlertDialog;
//        import android.app.ProgressDialog;
//        import android.content.Context;
//        import android.content.Intent;
//        import android.icu.util.Output;
//        import android.os.AsyncTask;
//import android.view.View;
//
//import java.io.BufferedReader;
//        import java.io.BufferedWriter;
//        import java.io.IOException;
//        import java.io.InputStream;
//        import java.io.InputStreamReader;
//        import java.io.OutputStream;
//        import java.io.OutputStreamWriter;
//        import java.net.HttpURLConnection;
//        import java.net.MalformedURLException;
//        import java.net.URL;
//        import java.net.URLEncoder;
//
//        import javax.net.ssl.HttpsURLConnection;
//
//
//public class BackgroundEditB extends AsyncTask<String,Void,String> {
//
//    Context context;
//    BackgroundEditB(Context ctx){
//        context=ctx;
//    }
//
//    //ProgressDialog pdLoading ;
//    AlertDialog alertDialog;
//
//    @Override
//    protected String doInBackground(String... voids) {
//
//        String type=voids[0];
//        String login_url="http://10.0.2.2/Android/files/updataB.php";
//        if (type.equals("updataB")){
//            try {
//                String email=voids[1];
//                String first_name=voids[2];
////                String 	institute=voids[3];
////                String telephone=voids[4];
//                URL url=new URL(login_url);
//                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
//                httpURLConnection.setRequestMethod("POST");
//                httpURLConnection.setDoOutput(true);
//                httpURLConnection.setDoInput(true);
//                OutputStream outputStream=httpURLConnection.getOutputStream();
//                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
//                String post_data= URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
//                        URLEncoder.encode("first_name","UTF-8")+"="+URLEncoder.encode(first_name,"UTF-8");  //+"&"+
////                        URLEncoder.encode("institute","UTF-8")+"="+URLEncoder.encode(institute,"UTF-8")+"&"+
////                        URLEncoder.encode("telephone","UTF-8")+"="+URLEncoder.encode(telephone,"UTF-8");
//
//                bufferedWriter.write(post_data);
//                bufferedWriter.flush();
//                bufferedWriter.close();
//                outputStream.close();
//
//                InputStream inputStream=httpURLConnection.getInputStream();
//                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
//                String result="";
//                String line="";
//                while ((line=bufferedReader.readLine())!=null){
//                    result+=line;
//
//                }
//                bufferedReader.close();
//                inputStream.close();
//                httpURLConnection.disconnect();
//                return result;
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//        return null;
//    }
//
//    @Override
//    protected void onPreExecute() {
//        alertDialog=new AlertDialog.Builder(context).create();
//        //    pdLoading= (ProgressDialog) new ProgressDialog.Builder(context).create();
//        alertDialog.setTitle("Login Status");
//    }
//
//    @Override
//    protected void onPostExecute(String result) {
//
////        alertDialog.setMessage(result);
////        alertDialog.show();
//        // pdLoading.dismiss();
////        if (result.equals("LOGIN SUCCESS")){
////            Intent i = new Intent(context,Second.class);
////            context.startActivity(i);
////        }else{
////            alertDialog.setMessage(result);
////            alertDialog.show();
////        }
//
////        if(result.equalsIgnoreCase("true"))
////        {
////
//////            Intent intent = new Intent(this,Second.class);
//////            startActivity(intent);
//////            MainActivity.this.finish();
////
////        }
//    }
//
//    @Override
//    protected void onProgressUpdate(Void... values) {
//        super.onProgressUpdate(values);
//    }
//}
//


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


public class BackgroundEditB extends AsyncTask<String,Void,String> {

    Context context;
    BackgroundEditB(Context ctx){
        context=ctx;
    }
    //ProgressDialog pdLoading ;
    AlertDialog alertDialog;

    @Override
    protected String doInBackground(String... voids) {

        String type=voids[0];
        String update_url="http://10.0.2.2/Android/files/updateB.php";
        if (type.equals("updataB")){
            try {
                String email=voids[1];
                String first_name=voids[2];
                String last_name=voids[3];
                String institute=voids[4];
                String telephone=voids[5];
                URL url=new URL(update_url);
                HttpURLConnection httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data= URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("first_name","UTF-8")+"="+URLEncoder.encode(first_name,"UTF-8")+"&"+
                        URLEncoder.encode("last_name","UTF-8")+"="+URLEncoder.encode(last_name,"UTF-8")+"&"+
                        URLEncoder.encode("institute","UTF-8")+"="+URLEncoder.encode(institute,"UTF-8")+"&"+
                        URLEncoder.encode("telephone","UTF-8")+"="+URLEncoder.encode(telephone,"UTF-8");

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

    @Override
    protected void onPreExecute() {
        alertDialog=new AlertDialog.Builder(context).create();
        //    pdLoading= (ProgressDialog) new ProgressDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {

//        alertDialog.setMessage(result);
//        alertDialog.show();
        // pdLoading.dismiss();
//        if (result.equals("LOGIN SUCCESS")){
//            Intent i = new Intent(context,Second.class);
//            context.startActivity(i);
//        }else{
//            alertDialog.setMessage(result);
//            alertDialog.show();
//        }

//        if(result.equalsIgnoreCase("true"))
//        {
//
////            Intent intent = new Intent(this,Second.class);
////            startActivity(intent);
////            MainActivity.this.finish();
//
//        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
