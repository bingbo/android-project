package com.ibingbo.component.receiver;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.ibingbo.component.service.MsgPushService;
import com.ibingbo.demo.MainActivity;

public class BootCompleteReceiver extends BroadcastReceiver {

    private static final String TAG = "DEMO_Receiver";
    public BootCompleteReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, MsgPushService.class);
        context.startService(service);

        NotificationCompat.Builder builder=new NotificationCompat.Builder(context);
        builder.setContentTitle("My Notifiaction")
                .setContentText("Hello,start to push message...")
        .setSmallIcon(android.support.v7.appcompat.R.drawable.notification_template_icon_bg);
        Intent intent1=new Intent(context, MainActivity.class);

        TaskStackBuilder stackBuilder=TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(intent1);
        PendingIntent pendingIntent=stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManager manager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1,builder.build());

        //Log.i(TAG,service.getStringExtra("msg"));
        Log.i(TAG,"boot complete. starting MsgPushService...");
    }
}
