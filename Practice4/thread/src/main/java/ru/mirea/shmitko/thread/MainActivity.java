package ru.mirea.shmitko.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtViewInfo = findViewById(R.id.txtViewInfo);
        Thread mainThread = Thread.currentThread();
        txtViewInfo.setText("Текущий поток: " + mainThread.getName());
        mainThread.setName("MireaThread");
        txtViewInfo.append("\nНовое имя потока: " + mainThread.getName());
    }

    public void on_btnClicked(View view) {
        new Thread(new Runnable() {
            public void run() {
                int numberThread = counter++;
                Log.i(
                    "ThreadProject",
                    "Thread started №" + numberThread
                );
                long endTime = System.currentTimeMillis() + 20 * 1000;
                while (System.currentTimeMillis() < endTime) {
                    synchronized (this) {
                        try {
                            wait(endTime - System.currentTimeMillis());
                        } catch (Exception e) {
                        }
                    }
                }
                Log.i(
                    "ThreadProject",
                    "Thread completed №" + numberThread
                );
            }
        }).start();
    }
}