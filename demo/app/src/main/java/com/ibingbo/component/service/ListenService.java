package com.ibingbo.component.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.BatteryManager;
import android.os.IBinder;
import android.util.Log;

import com.ibingbo.component.receiver.BatteryChangedReceiver;
import com.ibingbo.component.receiver.NetworkStateReceiver;

public class ListenService extends Service {
    private NetworkStateReceiver networkStateReceiver =new NetworkStateReceiver();
    private BatteryChangedReceiver batteryChangedReceiver=new BatteryChangedReceiver();
    private static final String TAG="DEMO_LISTEN_SERVICE";
    public ListenService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /***********动态注册监听广播,静态注册在manifest配置文件里***************/
        //监听网络变化
        IntentFilter filter=new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        this.registerReceiver(networkStateReceiver,filter);

        //监听电量变化
        IntentFilter filter1=new IntentFilter();
        filter1.addAction(BatteryManager.ACTION_CHARGING);
        this.registerReceiver(batteryChangedReceiver,filter1);
        /***********注册监听广播***************/
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"listen service is running...");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(networkStateReceiver);
        this.unregisterReceiver(batteryChangedReceiver);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
