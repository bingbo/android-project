package com.example.zhangbingbing.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String data = intent.getStringExtra("data");

        System.out.println("hello,recieve the broadcast,this data is : "+data);
        Log.i("broadcast",data);
    }
}
