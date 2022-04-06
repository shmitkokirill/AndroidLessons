package ru.mirea.shmitko.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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
        long endTime = System.currentTimeMillis() + 20 * 1000;
        while (System.currentTimeMillis() < endTime) {
            synchronized (this) {
                try {
                    wait(endTime - System.currentTimeMillis());
                } catch (Exception e) {
                }
            }
        }
    }
}