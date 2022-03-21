package ru.mirea.shmitko.intentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSendBtnClicked(View view) {
        long dateInMillis = System.currentTimeMillis();
        String format = "yyyy-MM-dd HH:mm:ss";
        final SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        String dateString = dateFormat.format(new Date(dateInMillis));


        Intent secActivity = new Intent(
                this,
                secondActivity.class
        );
        secActivity.putExtra(Constants.EXTRA_MESS_NAME, dateString);
        startActivity(secActivity);
    }
}