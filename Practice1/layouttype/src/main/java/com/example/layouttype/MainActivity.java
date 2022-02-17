package com.example.layouttype;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task4_2);

        ImageView contactImage = findViewById(R.id.imageView);
        contactImage.setImageResource(R.drawable.contact);

        ImageView dialImage = findViewById(R.id.dialView);
        dialImage.setImageResource(R.drawable.dial);
    }
}