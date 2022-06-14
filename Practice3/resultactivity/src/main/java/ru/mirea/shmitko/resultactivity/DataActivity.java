package ru.mirea.shmitko.resultactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DataActivity extends AppCompatActivity {
    private EditText fieldEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        fieldEditText = (EditText) findViewById(R.id.txtEdit);
    }

    public void sendBtnClicked(View view) {
        Intent intent = new Intent();
        intent.putExtra(Constants.EXTRA_MESS_NAME, fieldEditText.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}