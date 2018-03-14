package com.example.raulm.tetris;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by RaulM on 08/03/2018.
 */

public class MusicaFondu extends Service {
    private MediaPlayer reproductor;

    @Override
    public void onCreate(){
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int idArranc){
        int id = intent.getExtras().getInt("idMusica", R.raw.tetrisoriginal);
        reproductor = MediaPlayer.create(this, id);
        reproductor.setLooping(true);
        reproductor.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        reproductor.stop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
