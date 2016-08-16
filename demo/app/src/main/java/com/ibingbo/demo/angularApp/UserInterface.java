package com.ibingbo.demo.angularApp;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.ibingbo.service.web.UserService;

import org.json.JSONArray;

/**
 * Created by zhangbingbing on 16/8/13.
 */
public class UserInterface {

    private Context context;
    private WebView webView;
    private UserService userService;

    public UserInterface(Context context, WebView webView){
        this.context=context;
        this.webView=webView;
        this.userService=new UserService(this.context);
    }

    @JavascriptInterface
    public JSONArray list(){
        final JSONArray users=this.userService.getUserList();
        return users;
    }
}
