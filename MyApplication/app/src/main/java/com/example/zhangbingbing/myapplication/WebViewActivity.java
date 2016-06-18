package com.example.zhangbingbing.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class WebViewActivity extends AppCompatActivity {
    // The URL scheme should be non-hierarchical (no trailing slashes)
    private static final String APP_SCHEME = "test-app:";

    private WebView webView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        WebView webView = (WebView) findViewById(R.id.webView);

        webView.addJavascriptInterface(new WebAppInterface(this),"Android");

        webView.setWebViewClient(new MyWebViewClient());

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);


        webView.loadUrl("file:///android_asset/www/webview.html");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){
            webView.goBack();
            return true;
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)

        return super.onKeyDown(keyCode, event);
    }

    private class MyWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(url.startsWith(APP_SCHEME)){
                try {
                    String urlData = URLDecoder.decode(url.substring(APP_SCHEME.length()), "UTF-8");
                    //自定义页面内容
                    //view.loadData(urlData+"<div style='color:red'>test aaa</div>","text/html","UTF-8");
                    //view.loadDataWithBaseURL("test-app:next_page.html",null,"text/html","UTF-8",null);
                    //加载一个已经存在的页面
                    view.loadUrl("file:///android_asset/www/next_page.html");
                    return true;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            Uri suri = Uri.parse(url);
            if(suri.getHost().equals("m.baidu.com")){
                // This is my web site, so do not override; let my WebView load the page
                //在当前webview中显示,不需要打开浏览器
                return false;
            }
            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
            //相当于打开浏览器进行浏览页面
            Intent intent = new Intent(Intent.ACTION_VIEW, suri);
            startActivity(intent);
            return true;
        }
    }
}
