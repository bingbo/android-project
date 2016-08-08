package com.ibingbo.component.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.util.Log;
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
        if(isMobileConnected(context) || isMobileConnected(context) || isWifiConnected(context)){
            Toast.makeText(context,"network is connected... net type is " + getNetWorkType(context),Toast.LENGTH_LONG);
            Log.i(TAG,"network is ok ,type is "+getNetWorkType(context));
        }else {
            Toast.makeText(context,"network is disconnected...",Toast.LENGTH_LONG);
            Log.i(TAG,"network is not aviable");
        }

    }

    /**
     * 网络是否连接
     * @param context
     * @return
     */
    private boolean isNetWorkConnected(Context context){
        if(context !=null){
            ConnectivityManager manager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=manager.getActiveNetworkInfo();
            if(networkInfo!=null){
                return networkInfo.isAvailable();
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     * 判断wifi是否可用
     * @param context
     * @return
     */
    private boolean isWifiConnected(Context context){
        if(context!=null){
            ConnectivityManager manager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifiInfo=manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if(wifiInfo!=null){
                return wifiInfo.isAvailable();
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     * 判断mobile网络是否可用
     */
    private boolean isMobileConnected(Context context){
        if(context!=null){
            ConnectivityManager manager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mobileInfo=manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if(mobileInfo!=null){
                return mobileInfo.isAvailable();
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     * 获取网络类型名
     * @param context
     * @return
     */
    private String getNetWorkType(Context context){
        if(context!=null){
            ConnectivityManager manager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=manager.getActiveNetworkInfo();
            if(networkInfo!=null && networkInfo.isAvailable()){
                return networkInfo.getTypeName();
            }else {
                return null;
            }
        }else {
            return null;
        }
    }

}
