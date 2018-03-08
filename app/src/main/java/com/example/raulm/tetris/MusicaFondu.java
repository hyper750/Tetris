package com.example.raulm.tetris;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
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
        reproductor = MediaPlayer.create(this, R.raw.tetrisoriginal);
        reproductor.setLooping(true);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int idArranc){
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
