package com.example.raulm.tetris;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by RaulM on 11/01/2018.
 */

public class Joc extends Activity implements ModificarPuntuacio{

    private VistaJoc vistaJoc;
    private TextAmbFont puntuacio;

    @Override
    protected void onCreate(Bundle save){
        super.onCreate(save);
        setContentView(R.layout.layoutjoc);

        puntuacio = (TextAmbFont)findViewById(R.id.puntuacio);
        vistaJoc = (VistaJoc) findViewById(R.id.VistaJoc);
        vistaJoc.setParePuntuacio(this);
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
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        vistaJoc.getFil().aturar();
    }

    @Override
    public void setPuntuacio(int puntuacio){
        this.puntuacio.setText(getResources().getString(R.string.puntuacio) + " " + puntuacio);
    }

    /*@Override
    public void onSaveInstanceState(Bundle save){
        super.onSaveInstanceState(save);
        save.putSerializable("joc", vistaJoc.getTetris());
    }

    @Override
    public void onRestoreInstanceState(Bundle load){
        super.onRestoreInstanceState(load);
        TetrisObject t = (TetrisObject) load.getSerializable("joc");
        vistaJoc.setTetris(t);
    }*/
}
