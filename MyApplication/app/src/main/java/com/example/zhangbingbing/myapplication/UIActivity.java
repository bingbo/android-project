package com.example.zhangbingbing.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class UIActivity extends AppCompatActivity {
    private WebView webView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);

        webView = (WebView) findViewById(R.id.ui_webview);
        webView.loadUrl("file:///android_asset/www/index.html");
    }
}
