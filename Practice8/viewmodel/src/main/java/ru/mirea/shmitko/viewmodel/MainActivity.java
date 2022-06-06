package ru.mirea.shmitko.viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=findViewById(R.id.progrBar);
        ProgressViewModel viewModel = new ProgressViewModel();
        viewModel.getProgressState().observe(
                this,
                new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if(aBoolean)
                        {
                            progressBar.setVisibility(View.VISIBLE);
                        }
                        else progressBar.setVisibility(View.INVISIBLE);
                    }
                });
        viewModel.showProgress();
    }
}
