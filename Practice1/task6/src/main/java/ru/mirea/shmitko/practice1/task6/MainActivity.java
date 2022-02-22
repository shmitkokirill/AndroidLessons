package ru.mirea.shmitko.practice1.task6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView myTextView = (TextView) findViewById(R.id.textView);
        myTextView.setText("New text...");

        Button myBtn = findViewById(R.id.button);
        myBtn.setText("MireaButton");

        CheckBox myChkBox = findViewById(R.id.chkBox);
        myChkBox.setChecked(true);

    }
}