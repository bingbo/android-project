package com.ibingbo.demo.web;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.net.http.SslError;
import android.os.BatteryManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.Window;
import android.webkit.ConsoleMessage;
import android.webkit.HttpAuthHandler;
import android.webkit.JsPromptResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.ibingbo.component.receiver.BootCompleteReceiver;
import com.ibingbo.component.receiver.NetworkStateReceiver;
import com.ibingbo.demo.R;
import com.ibingbo.models.User;
import com.ibingbo.service.web.UserService;

import org.json.JSONException;
import org.json.JSONObject;


public class IndexActivity extends AppCompatActivity {

    private WebView indexView;
    private UserService userService;
    private Activity activity;

    private final String TAG = "DEMO_INDEX_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //必须在setcontentView之间调用
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_index);

        /***********动态注册监听广播,静态注册在manifest配置文件里***************/
        //监听网络变化
        IntentFilter filter=new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(new NetworkStateReceiver(),filter);

        //监听电量变化
//        IntentFilter filter1=new IntentFilter();
//        filter1.addAction(BatteryManager.ACTION_CHARGING);
//        this.registerReceiver(new BootCompleteReceiver(),filter1);
        /***********注册监听广播***************/

        activity = this;

        userService = new UserService(this);
        indexView = (WebView) this.findViewById(R.id.indexView);
        indexView.setWebViewClient(new MyWebViewClient());
        indexView.setWebChromeClient(new MyChromeClient());
        indexView.addJavascriptInterface(new WebAppInterface(this, indexView), "Demo");
        indexView.requestFocus();


        WebSettings settings = indexView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);

        indexView.loadUrl("file:///android_asset/www/index.html");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop.....");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i(TAG,"on configuration changed...");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i(TAG,"on post resume.....");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"on destroy....");
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.i(TAG,"on post create....");
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        Log.i(TAG,"on content changed....");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,"on save instance state....");
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        Log.i(TAG,"on title changed....");
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        Log.i(TAG,"on menu opened....");
        return super.onMenuOpened(featureId, menu);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"on pause....");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"on resume....");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"on start....");
        //手动发送广播
        //这里开机启动广播开始推送消息
        Intent intent=new Intent(this, BootCompleteReceiver.class);
        intent.putExtra("msg","hello, receiver...");
        this.sendBroadcast(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"on restart....");
    }

    /**
     * 监听按键事件,比如回退键等
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && indexView.canGoBack()) {
            indexView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 这里用webviewclient控制页面跳转行为
     */
    private class MyWebViewClient extends WebViewClient {
        /**
         * 打开连接前的处理,可以重写跳转逻辑
         *
         * @param view
         * @param request
         * @return
         */
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            Uri uri = request.getUrl();
            view.loadUrl(request.getUrl().getEncodedPath());
            Log.i(TAG, request.getUrl().getPath());
            //return super.shouldOverrideUrlLoading(view, request);
            return true;
        }


        /**
         * 接收处理web资源加载错误问题
         *
         * @param view
         * @param request
         * @param error
         */
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            Toast.makeText(IndexActivity.this, "Oh no! " + error.getDescription(), Toast.LENGTH_SHORT).show();
        }

        /**
         * 处理https请求,webView默认是不处理https请求的，页面显示空白,为webView处理ssl证书设置
         *
         * @param view
         * @param handler
         * @param error
         */
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            //等待证书响应
            handler.proceed();
            //表示挂起连接，为默认方式
            //handler.cancel();
            //可做其他处理
            //handler.handleMessage(null);
        }


        /**
         * 载入页面开始的事件
         *
         * @param view
         * @param url
         * @param favicon
         */
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.i(TAG, "the page is start to load...");
        }

        /**
         * 载入页面完成的事件
         *
         * @param view
         * @param url
         */
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.i(TAG, "the page is end to load...");
        }

        /**
         * 接收到http请求的事件
         *
         * @param view
         * @param handler
         * @param host
         * @param realm
         */
        @Override
        public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
            super.onReceivedHttpAuthRequest(view, handler, host, realm);
            Log.i(TAG, "this is get the http request....");
        }
    }

    /**
     * 用这个webchromeclient来与页面进行数据交互,如提交数据,获取数据等
     */
    private class MyChromeClient extends WebChromeClient {
        private final String BRIDGE_KEY = "test";


        /**
         * 接收js里的prompt事件
         *
         * @param view
         * @param url
         * @param message
         * @param defaultValue
         * @param result
         * @return
         */
        @Override
        public boolean onJsPrompt(final WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            boolean res = true;
            if (message.equals(BRIDGE_KEY)) {
                try {
                    JSONObject data = new JSONObject(defaultValue);
                    String method = data.getString("method");
                    if (method.equals("login")) {
                        String name = data.getString("name");
                        String password = data.getString("password");
                        User user = new User(name, password, null);
                        res = userService.hasUser(user);
                        if (res) {
                            //result.confirm();
                            Toast.makeText(IndexActivity.this, "login success", Toast.LENGTH_SHORT).show();
                            view.post(new Runnable() {
                                @Override
                                public void run() {
                                    view.loadUrl("file:///android_asset/www/index.html");
                                }
                            });

                            return true;
                        } else {
                            Toast.makeText(IndexActivity.this, "name or password is wrong", Toast.LENGTH_LONG).show();
                            return false;
                        }


                    } else if (method.equals("register")) {
                        User user = new User(data.getString("name"), data.getString("password"), data.getString("email"));
                        res = userService.addUser(user);
                        if(res){
                            Toast.makeText(IndexActivity.this,"register success",Toast.LENGTH_SHORT).show();
                            view.post(new Runnable() {
                                @Override
                                public void run() {
                                    view.loadUrl("file:///android_asset/www/index.html");
                                }
                            });

                            return true;
                        }else {
                            Toast.makeText(IndexActivity.this,"failed,input again..",Toast.LENGTH_LONG).show();
                            return false;
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                res = false;
            }
            return res;
        }

        /**
         * 接收处理js里的console事件
         *
         * @param consoleMessage
         * @return
         */
        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            Log.d("IndexAcitivity", consoleMessage.message() + "--- from line " + consoleMessage.lineNumber() + " of sourceid " + consoleMessage.sourceId());
            return super.onConsoleMessage(consoleMessage);
        }

        /**
         * 在标题状态栏显示加载进度条
         *
         * @param view
         * @param newProgress
         */
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            IndexActivity.this.setProgress(newProgress * 100);
        }
    }
}
