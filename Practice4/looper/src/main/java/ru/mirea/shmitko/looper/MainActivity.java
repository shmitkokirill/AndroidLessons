package ru.mirea.shmitko.looper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
//    MyLooper myLooper;
    MySecondLooper myLooper;
    public static boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        myLooper = new MyLooper();
        myLooper = new MySecondLooper();
        myLooper.start();

        Runnable run = new Runnable() {
            public void run() {
                while (flag) {
                    try {
                        Message msg = new Message();
                        Bundle bundle = new Bundle();
                        bundle.putString("KEY", "21 год, студент");
                        msg.setData(bundle);
                        if (myLooper != null) {
                            myLooper.handler.sendMessage(msg);
                        }
                        TimeUnit.SECONDS.sleep(21);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread t = new Thread(run);
        t.start();

    }

    public void on_btnFirstClicked(View view) {
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("KEY", "mirea");
        msg.setData(bundle);
        if (myLooper != null) {
            myLooper.handler.sendMessage(msg);
        }
    }

    public void on_btnSecondClicked(View view) {
        flag = (flag) ? false : true;
    }

}