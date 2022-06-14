package ru.mirea.shmitko.intentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class secondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView mainTxtView = (TextView) findViewById(R.id.txtView);

        String shareMsg = getIntent().getStringExtra(Constants.EXTRA_MESS_NAME);

        mainTxtView.setText(shareMsg);
    }
}