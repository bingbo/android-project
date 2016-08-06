package com.ibingbo.component.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.ibingbo.component.service.MsgPushService;

public class BootCompleteReceiver extends BroadcastReceiver {

    private static final String TAG = "DEMO_Receiver";
    public BootCompleteReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, MsgPushService.class);
        context.startService(service);
        //Log.i(TAG,service.getStringExtra("msg"));
        Log.i(TAG,"boot complete. starting MsgPushService...");
    }
}
