package com.ibingbo.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zhangbingbing on 16/7/30.
 */
public class Http {

    private static StringBuilder sb;
    private static HttpURLConnection conn;

    public static String get(String url){

        sb=new StringBuilder();
        try {
            URL uri = new URL(url);
            conn = (HttpURLConnection) uri.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String msg = "";
            while ((msg = br.readLine()) != null) {
                sb.append(msg);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(conn != null){
                conn.disconnect();
            }
        }
        return sb.toString();
    }

    public static String post(String url,String params){
        sb=new StringBuilder();
        try {
            URL uri = new URL(url);
            conn = (HttpURLConnection) uri.openConnection();
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setConnectTimeout(10000);
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setUseCaches(false);
            conn.connect();
            OutputStream out=conn.getOutputStream();
            out.write(params.getBytes());
            out.flush();
            out.close();

            int responseCode = conn.getResponseCode();

            if(200 == responseCode){
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String msg = "";
                while ((msg = br.readLine()) != null) {
                    sb.append(msg);
                }
            }



        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(conn != null){
                conn.disconnect();
            }
        }
        return sb.toString();
    }
}
