package ru.mirea.shmitko.practice1.task7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find view-elements
        Button okBtn = (Button) findViewById(R.id.btnOk);
        Button canBtn = (Button) findViewById(R.id.btnCancel);
        TextView txtView = (TextView) findViewById(R.id.textView);

        View.OnClickListener btnOkClicked = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtView.setText("OK button pressed");
            }
        };
        okBtn.setOnClickListener(btnOkClicked);

        View.OnClickListener btnCanClicked = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtView.setText("Cancel button pressed");
            }
        };
        canBtn.setOnClickListener(btnCanClicked);

    }
}