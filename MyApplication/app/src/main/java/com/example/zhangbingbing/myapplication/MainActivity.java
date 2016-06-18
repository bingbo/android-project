package com.example.zhangbingbing.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getCanonicalName();

    private WebView mWebView;

    private ProgressBar mLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView) this.findViewById(R.id.wvPortal);
        mLoading = (ProgressBar) this.findViewById(R.id.pbLoading);

        mWebView.loadUrl("file:///android_asset/www/index.html");
        WebSettings mWebSettings = mWebView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new BridgeWCClient());

    }


    private class BridgeWCClient extends WebChromeClient {

        private final String BRIDGE_KEY = "test";

        @Override

        public boolean onJsPrompt(WebView view, String url, String title,

                                  String message, JsPromptResult result) {

            if (title.equals(BRIDGE_KEY)) {

                JSONObject commandJSON = null;

                try {

                    commandJSON = new JSONObject(message);

                    processCommand(commandJSON);

                } catch (JSONException ex) {

                    //Received an invalid json

                    Log.e(TAG, "Invalid JSON: " + ex.getMessage());

                    result.confirm();

                    return true;

                }

                result.confirm();

                return true;

            } else {

                return false;

            }

        }

        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            Log.d("MainAcitivity",consoleMessage.message()+"--- from line "+consoleMessage.lineNumber()+" of sourceid "+consoleMessage.sourceId());
            return super.onConsoleMessage(consoleMessage);
        }
    }

    private void processCommand(JSONObject commandJSON)

            throws JSONException {

        String command = commandJSON.getString("method");

        if ("login".equals(command)) {

            int state = commandJSON.getInt("state");
            final String name = commandJSON.getString("name");
            final String password = commandJSON.getString("password");
            Intent i = new Intent(MainActivity.this,BActivity.class);
            i.putExtra("name",name);
            i.putExtra("password",password);
            startActivity(i);

            /*
            if (state == 0) {

                MainActivity.this.runOnUiThread(new Runnable() {

                    @Override

                    public void run() {

                        mLoading.setVisibility(View.VISIBLE);

                    }

                });

            } else if (state == 1) {

                MainActivity.this.runOnUiThread(new Runnable() {

                    @Override

                    public void run() {

                        mLoading.setVisibility(View.GONE);
                        mWebView.loadUrl("javascript:Bridge.callBack({success:true, message:\"logged in\"})");


                    }

                });

            }
            */

        }

    }




}
