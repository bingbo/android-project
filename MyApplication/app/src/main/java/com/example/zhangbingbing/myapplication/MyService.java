package com.example.zhangbingbing.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {

    private boolean running = false;
    private String data = "this is a test";
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        data = intent.getStringExtra("data");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        running = false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        running = true;
        new Thread(){
            @Override
            public void run() {
                super.run();
                while (running){
                    System.out.println("the data is "+data);
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
