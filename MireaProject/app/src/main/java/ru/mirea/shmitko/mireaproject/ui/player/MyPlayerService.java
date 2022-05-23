package ru.mirea.shmitko.mireaproject.ui.player;

import static ru.mirea.shmitko.mireaproject.MainActivity.preferences;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import ru.mirea.shmitko.mireaproject.R;

public class MyPlayerService extends Service {
    private MediaPlayer mediaPlayer;

    public MyPlayerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate(){
        mediaPlayer= MediaPlayer.create(this, R.raw.a_song);
        mediaPlayer.setLooping(true);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        mediaPlayer.start();
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        mediaPlayer.stop();
    }
}