package com.ibingbo.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
            //conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            conn.setDoInput(true);
            //conn.setDoOutput(true);



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
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(params.getBytes().length));
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();
            conn.getOutputStream().write(params.getBytes("UTF-8"));//将参数写入输出流
            conn.getOutputStream().flush();
            conn.getOutputStream().close();


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
}
