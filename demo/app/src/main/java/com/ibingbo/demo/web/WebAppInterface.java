package com.ibingbo.demo.web;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.ibingbo.models.User;
import com.ibingbo.service.ContactService;
import com.ibingbo.service.web.UserService;

import org.json.JSONArray;

/**
 * Created by zhangbingbing on 16/8/4.
 */
public class WebAppInterface {
    private Context context;
    private WebView webView;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private ProgressDialog progressDialog;

    public WebAppInterface(Context context,WebView webView){
        this.context=context;
        this.webView=webView;
        this.builder=new AlertDialog.Builder(context);
        this.progressDialog=new ProgressDialog(context);
        this.progressDialog.setMessage("load....");

    }

    @JavascriptInterface
    public void showToast(String toast){
        Toast t= Toast.makeText(this.context,toast,Toast.LENGTH_LONG);
        t.setGravity(Gravity.CENTER,0,0);
        t.show();
        this.webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("javascript:haveSay()");
            }
        });
    }

    @JavascriptInterface
    public void login(String name,String password){
        UserService userService = new UserService(context);
        User user=new User(name,password,null);
        boolean result = userService.hasUser(user);
        if(result){
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl("file:///android_asset/www/index.html");
                }
            });

        }
    }

    @JavascriptInterface
    public void register(String name,String password,String email){
        UserService service=new UserService(context);
        User user=new User(name,password,email);
        boolean result = service.addUser(user);
        if(result){
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl("file:///android_asset/www/index.html");
                }
            });

        }
    }

    @JavascriptInterface
    public void loadData(){
        UserService service=new UserService(context);
        final JSONArray result= service.getUserList();
        this.webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("javascript:listCallback(" + result + ")");
            }
        });

    }

    @JavascriptInterface
    public void delete(final String id){
        this.builder.setMessage("are you sure to delete?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UserService service=new UserService(context);
                        boolean res = service.deleteUserById(id);
                        webView.post(new Runnable() {
                            @Override
                            public void run() {
                                webView.reload();
                            }
                        });
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        this.alertDialog=this.builder.create();
        this.alertDialog.show();

    }

    @JavascriptInterface
    public void loadContactsData(){
        ContactService service=new ContactService(context);
        final JSONArray result = service.getContactList();
        this.webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("javascript:listContactCallback(" + result + ")");
            }
        });
    }

    @JavascriptInterface
    public void showProgress(boolean show){
        if(show){
            this.progressDialog.show();
        }else {
            this.progressDialog.hide();
        }
    }
}
