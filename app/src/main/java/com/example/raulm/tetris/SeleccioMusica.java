package com.example.raulm.tetris;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

/**
 * Created by RaulM on 14/03/2018.
 */

public class SeleccioMusica {
    private Activity activity;
    private int id = -1;

    private static SeleccioMusica _musica;

    private SeleccioMusica(Activity activity){
        this.activity = activity;
    }

    public void iniciarMusica(boolean comprovacio){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(activity);
        int id = Integer.parseInt(pref.getString("tipusMusica", "0"));
        if(comprovacio) {
            if(this.id != id){
                aturar();
                iniciarMusica(id);
            }
        }
        else {
            aturar();
        }
    }

    public boolean musicaActivable(){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(activity);
        return pref.getBoolean("musicaActivada", true);
    }

    public void iniciarMusica(int id){
        String[] musica = activity.getResources().getStringArray(R.array.tipusMusica);
        int[] idMusica = new int[musica.length];
        for (int x = 0; x < musica.length; x++) {
            idMusica[x] = Integer.parseInt(musica[x]);
        }

        if (id >= 0 && id < idMusica.length) {
            this.id = id;
            Intent i = new Intent(activity, MusicaFondu.class);

            if (idMusica[1] == id) {
                i.putExtra("idMusica", R.raw.tetrisremix);
            } else {
                i.putExtra("idMusica", R.raw.tetrisoriginal);
            }

            activity.startService(i);
        }
    }

    public void aturar(){
        this.id = -1;
        activity.stopService(new Intent(activity, MusicaFondu.class));
    }

    public static SeleccioMusica getInstance(Activity activity){
        if(_musica == null){
            _musica = new SeleccioMusica(activity);
        }

        return _musica;
    }
}
