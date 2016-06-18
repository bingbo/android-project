package com.example.zhangbingbing.myapplication;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhangbingbing on 16/6/18.
 */
public class WebAppInterface {
    Context mContext;

    /** Instantiate the interface and set the context */
    WebAppInterface(Context context){
        mContext = context;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast){
        Toast.makeText(mContext,toast,Toast.LENGTH_SHORT).show();
    }

    /**
     * get some data to display for page
     */
    @JavascriptInterface
    public String getMessage(){

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name","bill");
            jsonObject.put("age",30);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();

    }
}
