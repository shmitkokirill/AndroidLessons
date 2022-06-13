package ru.mirea.shmitko.mireaproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
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
import ru.mirea.shmitko.mireaproject.databinding.FragmentSettingsBinding;
import ru.mirea.shmitko.mireaproject.ui.calculator.CalculatorFragment;
import ru.mirea.shmitko.mireaproject.ui.dataRoom.DataRoom;
import ru.mirea.shmitko.mireaproject.ui.map.MapsFragment;
import ru.mirea.shmitko.mireaproject.ui.player.MusicPlayer;
import ru.mirea.shmitko.mireaproject.ui.player.MyPlayerService;
import ru.mirea.shmitko.mireaproject.ui.sensors.SensorsFragment;

public class MainActivity extends AppCompatActivity
        implements SensorEventListener,
        GoogleMap.OnMapClickListener {
    private SensorManager sensorManager;
    public static SharedPreferences preferences;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        preferences = getPreferences(MODE_PRIVATE);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_browser,
                R.id.nav_calc,
                R.id.nav_player,
                R.id.nav_sensors,
                R.id.nav_settings,
                R.id.nav_map,
                R.id.nav_dataRoom
        )
                .setOpenableLayout(drawer)
                .build();
        NavController navController =
                Navigation.findNavController(
                        this,
                        R.id.nav_host_fragment_content_main
                );
        NavigationUI.setupWithNavController(navigationView, navController);
    }
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);

        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY),
                SensorManager.SENSOR_DELAY_UI);

        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT),
                SensorManager.SENSOR_DELAY_UI);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (findViewById(R.id.txtViewS1) != null) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                float azimuth = event.values[0];
            ((TextView) findViewById(R.id.txtViewS1))
                    .setText("X-coords: " + azimuth);
            }
            if (event.sensor.getType() == Sensor.TYPE_GRAVITY) {
                float gravity = event.values[0];
            ((TextView) findViewById(R.id.txtViewS2))
                    .setText("  Gravity: " + gravity);
            }
            if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
                float light = event.values[0];
            ((TextView) findViewById(R.id.txtViewS3))
                    .setText("  Light: " + light);
            }
        }
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

    public void on_btnSubmitClick_dataRoomFragment(View v) {
        Fragment hostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_content_main);
        Fragment fragment =
                hostFragment.getChildFragmentManager().getFragments().get(0);

        EditText toDo = (EditText) findViewById(R.id.txtEditToDo);
        EditText whenDo = (EditText) findViewById(R.id.txtEditWhenDo);
        String toDoTxt = toDo.getText().toString();
        String whenDoTxt = whenDo.getText().toString();

        if (fragment != null && fragment.isVisible()) {
            if (fragment instanceof DataRoom) {
                ((DataRoom) fragment).on_btnSubmitClick(v, toDoTxt, whenDoTxt);
            }
        }
    }

    public void on_btnRemoveClick_dataRoomFragment(View v) {
        Fragment hostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_content_main);
        Fragment fragment =
                hostFragment.getChildFragmentManager().getFragments().get(0);

        if (fragment != null && fragment.isVisible()) {
            if (fragment instanceof DataRoom) {
                ((DataRoom) fragment).on_btnRemoveClick(v);
            }
        }
    }

    public void on_btnMakePhotoClick_SensorsFragment(View v) {
        Fragment hostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_content_main);
        Fragment fragment =
                hostFragment.getChildFragmentManager().getFragments().get(0);

        if (fragment != null && fragment.isVisible()) {
            if (fragment instanceof SensorsFragment) {
                ((SensorsFragment) fragment).on_btnMakePhotoClick(v);
            }
        }
    }

    public void on_btnShowPhotoClick_SensorsFragment(View v) {
        Fragment hostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_content_main);
        Fragment fragment =
                hostFragment.getChildFragmentManager().getFragments().get(0);

        if (fragment != null && fragment.isVisible()) {
            if (fragment instanceof SensorsFragment) {
                ((SensorsFragment) fragment).on_btnShowPhotoClick(v);
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

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                latLng).zoom(12).build();
        Fragment hostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_content_main);
        MapsFragment fragment =
                (MapsFragment) hostFragment.getChildFragmentManager().getFragments().get(0);

        GoogleMap mMap = fragment.getMap();
        mMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
        mMap.addMarker(new MarkerOptions().title("Что за место?")
                .snippet("Новое место").position(latLng));
    }
}