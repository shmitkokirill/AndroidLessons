package ru.mirea.shmitko.intentfilter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnBrowserClicked(View view) {
        Uri address = Uri.parse("https://www.mirea.ru/");
        Intent openLinkIntent = new Intent(Intent.ACTION_VIEW, address);
        if (openLinkIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(openLinkIntent);
        } else {
            Log.d("Intent", "Не получается обработать намерение!");
        }
    }

    public void onBtnTextReaderClicked(View view) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_SUBJECT, "MIREA");
        share.putExtra(Intent.EXTRA_TEXT, "Шмитько Кирилл Андреевич");
        startActivity(Intent.createChooser(share, "Мои ФИО"));
    }

}