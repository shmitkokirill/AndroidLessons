package ru.mirea.shmitko.looper;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class MySecondLooper extends Thread {
    Handler handler;
    @SuppressLint("HandlerLeak")
    @Override
    public void run() {
        Log.d("MySecondLooper", "run");
        Looper.prepare();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                Log.d(
                    "MySecondLooper",
                     msg.getData().getString("KEY")
                );
            }
        };
        Looper.loop();
    }
}