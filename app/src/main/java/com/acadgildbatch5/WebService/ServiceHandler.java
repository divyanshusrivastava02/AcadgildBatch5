package com.acadgildbatch5.WebService;

import android.util.Log;

import org.apache.http.NameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Divyanshu on 15-01-2017.
 */

public class ServiceHandler {

//    static  String response = null;
//    public final  static int GET =1;
//    public final static int POST = 2;


    public String makeServiceCall(String url){
        String response = null;
        try{
            URL url1 = new URL(url);
            HttpURLConnection connection = (HttpURLConnection)url1.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);

            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK){
                return null;
            }

            Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
            StringBuilder sb = new StringBuilder();
            for(int i;(i =in.read())>0;)
                sb.append((char)i);
            response = sb.toString();
//            System.out.println("RESPONSE  :::::"+response);
//            Log.i("My VALue",response.toString());
        }catch (Exception e){
            e.printStackTrace();
            Log.e("My VALue crashed",e.getMessage());
        }

        return response;
    }



}
