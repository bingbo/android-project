package com.ibingbo.component.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * 接收网络状态变化通知
 */
public class NetworkStateReceiver extends BroadcastReceiver {
    private static final String TAG = "DEMO_NetWorkReceiver";
    public NetworkStateReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(isNetworkAvailable(context)){
            Toast.makeText(context,"network is connected...",Toast.LENGTH_LONG);
        }else {
            Toast.makeText(context,"network is disconnected...",Toast.LENGTH_LONG);
        }

    }

    private boolean isNetworkAvailable(Context context){
        ConnectivityManager manager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network networks[] = manager.getAllNetworks();
        if(networks != null){
            for (int i=0;i<networks.length;i++){
                NetworkInfo info= manager.getNetworkInfo(networks[i]);
                if(info.getState() == NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
        }else {
            return false;
        }
        return false;
    }
}
