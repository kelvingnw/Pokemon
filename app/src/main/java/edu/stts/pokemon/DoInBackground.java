package edu.stts.pokemon;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class DoInBackground extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;
    String userlogin="";
    DoInBackground (Context ctx){
        context = ctx;
    }
    @Override
    protected String doInBackground(String... voids) {
        String type= voids[0];
        String login_url="https://zerorhytm.000webhostapp.com/login.php";
        String register_url="https://zerorhytm.000webhostapp.com/register.php";
        String insertSkill_url="https://zerorhytm.000webhostapp.com/insertSkill.php";
        if(type.equals("login")){
            try{
                String user=voids[1];
                String pass=voids[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream= httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("user","UTF-8")+"="+URLEncoder.encode(user,"UTF-8")+"&"+
                                    URLEncoder.encode("pass","UTF-8")+"="+URLEncoder.encode(pass,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream= httpURLConnection.getInputStream();
                BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line;
                while((line=bufferedReader.readLine())!=null){
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e){
                e.printStackTrace();
            } catch(ProtocolException e){
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("register")){
            try{
                String user=voids[1];
                String pass=voids[2];
                String confirm=voids[3];
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream= httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("user","UTF-8")+"="+URLEncoder.encode(user,"UTF-8")+"&"+
                        URLEncoder.encode("pass","UTF-8")+"="+URLEncoder.encode(pass,"UTF-8")+"&"+
                        URLEncoder.encode("confirm","UTF-8")+"="+URLEncoder.encode(confirm,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream= httpURLConnection.getInputStream();
                BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line;
                while((line=bufferedReader.readLine())!=null){
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("insertskill")){
            try{
                String skillname=voids[1];
                String desk=voids[2];
                String element=voids[3];
                String pp=voids[4];
                String multiplier=voids[5];
                URL url = new URL(insertSkill_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream= httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("skillname","UTF-8")+"="+URLEncoder.encode(skillname,"UTF-8")+"&"+
                        URLEncoder.encode("desk","UTF-8")+"="+URLEncoder.encode(desk,"UTF-8")+"&"+
                        URLEncoder.encode("element","UTF-8")+"="+URLEncoder.encode(element,"UTF-8")+"&"+
                        URLEncoder.encode("pp","UTF-8")+"="+URLEncoder.encode(pp,"UTF-8")+"&"+
                        URLEncoder.encode("multiplier","UTF-8")+"="+URLEncoder.encode(multiplier,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream= httpURLConnection.getInputStream();
                BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line;
                while((line=bufferedReader.readLine())!=null){
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e){
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
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        if(result!=null && result.equalsIgnoreCase("login admin"))
        {
            alertDialog.show();
            Intent intent = new Intent(context, Home.class);
            context.startActivity(intent);

        }
        else if(result!=null && result.equalsIgnoreCase("login success!!!"))
        {
            alertDialog.show();
            
            Intent intent = new Intent(context, Home.class);
            context.startActivity(intent);

        }
        else if(result!=null && result.equalsIgnoreCase("Insert Success"))
        {
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        }
        else if(result!=null && result.equalsIgnoreCase("Insert SkillAct Success"))
        {
            alertDialog.show();
            //Intent intent = new Intent(context, SkillAct.class);
            //context.startActivity(intent);

        }
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
