package ru.mirea.shmitko.mireaproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import ru.mirea.shmitko.mireaproject.databinding.ActivityMainBinding;
import ru.mirea.shmitko.mireaproject.ui.calculator.CalculatorFragment;
import ru.mirea.shmitko.mireaproject.ui.player.MusicPlayer;
import ru.mirea.shmitko.mireaproject.ui.player.MyPlayerService;

public class MainActivity extends AppCompatActivity {


    public static SharedPreferences preferences;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getPreferences(MODE_PRIVATE);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(
                        view,
                        "Replace with your own action",
                        Snackbar.LENGTH_LONG
                )
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_browser, R.id.nav_calc, R.id.nav_player)
                .setOpenableLayout(drawer)
                .build();
        NavController navController =
                Navigation.findNavController(
                        this,
                        R.id.nav_host_fragment_content_main
                );
        NavigationUI.setupActionBarWithNavController(
                this,
                navController,
                mAppBarConfiguration
        );
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController =
                Navigation.findNavController(
                        this,
                        R.id.nav_host_fragment_content_main
                );
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void on_btnNumberClick_calcFragment(View v) {
        Fragment hostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_content_main);
        Fragment fragment =
                hostFragment.getChildFragmentManager().getFragments().get(0);

        if (fragment != null && fragment.isVisible()) {
            if (fragment instanceof CalculatorFragment) {
                ((CalculatorFragment) fragment).on_btnNumberClick(v);
            }
        }
    }

    public void on_btnOperationClick_calcFragment(View v) {
        Fragment hostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_content_main);
        Fragment fragment =
                hostFragment.getChildFragmentManager().getFragments().get(0);

        if (fragment != null && fragment.isVisible()) {
            if (fragment instanceof CalculatorFragment) {
                ((CalculatorFragment) fragment).on_btnOperationClick(v);
            }
        }
    }
    public void on_btnPlayClick_musicFrag(View v) {
        startService(
            new Intent(MainActivity.this, MyPlayerService.class));
    }
    public void on_btnStopPlayClick_musicFrag(View v) {
        stopService(
                new Intent(
                        MainActivity.this,
                        MyPlayerService.class
                )
        );
    }
}