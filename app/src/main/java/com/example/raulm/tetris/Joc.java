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
        vistaJoc.setActivity(this);
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
        SeleccioMusica.getInstance(this).iniciarMusica(SeleccioMusica.getInstance(this).musicaActivable());
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        vistaJoc.getFil().aturar();
    }
}
