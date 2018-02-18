package com.example.raulm.tetris;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by RaulM on 20/01/2018.
 */

public class JocMultijugador extends Joc {
    @Override
    public void onCreate(Bundle saved){
        super.onCreate(saved);
        setContentView(R.layout.layoutjoc);
        //Layout amb dues pantalles, es teu tetris i es de s'enemic i vegent es pesos que envies
    }
}
