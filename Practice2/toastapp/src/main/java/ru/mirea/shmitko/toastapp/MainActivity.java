package ru.mirea.shmitko.toastapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast toast = Toast.makeText(getApplicationContext(), "Здравствуй MIREA! ФИО", Toast.LENGTH_LONG);

        toast.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout toastContainer = (LinearLayout) toast.getView();
        ImageView ImageView = new ImageView(getApplicationContext());
        LinearLayout.LayoutParams linLayoutParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        ImageView.setLayoutParams(linLayoutParam);
        ImageView.setImageResource(R.drawable.cat);
        ImageView.setScaleType(android.widget.ImageView.ScaleType.CENTER_INSIDE);
        ImageView.setAdjustViewBounds(true);
        toastContainer.addView(ImageView, 0);
        toast.show();
    }
}