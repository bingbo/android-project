package com.example.zhangbingbing.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

public class ShareActivity extends AppCompatActivity {

    private static final String TAG = "ShareActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        if(Intent.ACTION_SEND.equals(action) && type!=null){
            //发送分享文本
            if("text/plain".equals(type)){
                String shareText=intent.getStringExtra(Intent.EXTRA_TEXT);
                Log.i(TAG,"sharetext: "+shareText);
            //发送分享图片
            }else if(type.startsWith("image/")){
                Uri imageuri = intent.getParcelableExtra(Intent.EXTRA_STREAM);
                Log.i(TAG,imageuri.toString());
            }
        //发送分享多张图片
        }else if(Intent.ACTION_SEND_MULTIPLE.equals(action) && type!=null){
            if(type.startsWith("image/")){
                ArrayList<Uri> imageuris=intent.getParcelableArrayListExtra(Intent.EXTRA_STREAM);
                Log.i(TAG,imageuris.toString());
            }
        }else {

        }
    }
}
