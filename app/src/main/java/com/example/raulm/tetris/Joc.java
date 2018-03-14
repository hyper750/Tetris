package com.example.raulm.tetris;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

/**
 * Created by RaulM on 11/01/2018.
 */

public class Joc extends Activity{

    private VistaJoc vistaJoc;

    @Override
    protected void onCreate(Bundle save){
        super.onCreate(save);
        setContentView(R.layout.layoutjoc);

        vistaJoc = (VistaJoc) findViewById(R.id.VistaJoc);
    }

    @Override
    protected void onPause(){
        super.onPause();
        vistaJoc.getFil().pausar();
    }

    @Override
    protected void onResume(){
        super.onResume();
        vistaJoc.getFil().reanudar();
        //Per si pitj home i torn entrar estarà a sa mateixa activitat però sa musica aturada, te que seguir
        startService(new Intent(this, MusicaFondu.class));
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean activarMusica = pref.getBoolean("musicaActivada", true);
        if(activarMusica) {
            //Iniciar musica de fondu, principi comença des de 0
            startService(new Intent(this, MusicaFondu.class));
        }
        else{
            stopService(new Intent(this, MusicaFondu.class));
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        vistaJoc.getFil().aturar();
    }
}
