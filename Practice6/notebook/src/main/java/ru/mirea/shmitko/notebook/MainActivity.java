package ru.mirea.shmitko.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPref;
    private EditText txtEditFileName;
    private EditText txtEditSomeText;
    private static final String KEY_TXT = "some_key_for_text";
    private static final String KEY_FILE = "some_key_for_file";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = getPreferences(MODE_PRIVATE);
        txtEditSomeText = findViewById(R.id.txtEditSomeText);
        txtEditFileName = findViewById(R.id.txtEditFileName);

        String txtFromFile = sharedPref.getString(KEY_TXT, "isEmpty");
        String fileName = sharedPref.getString(KEY_FILE, "isEmpty");
        if (txtFromFile != "isEmpty")
            txtEditSomeText.setText(txtFromFile);
        if (fileName != "isEmpty")
            txtEditFileName.setText(fileName);
    }

    public void on_btnSaveClick(View view) {
        FileOutputStream outputStream;
        String fileName = txtEditFileName.getText().toString();
        String text = txtEditSomeText.getText().toString();
        try {
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(text.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        SharedPreferences.Editor editor = sharedPref.edit();
        // Сохранение значения по ключу SAVED_TEXT
        editor.putString(KEY_TXT, text);
        editor.putString(KEY_FILE, fileName);
        editor.apply();
        Toast.makeText(this, "Text saved", Toast.LENGTH_SHORT).show();
    }
}