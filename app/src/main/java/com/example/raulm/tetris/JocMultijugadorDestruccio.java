package com.example.raulm.tetris;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by RaulM on 14/03/2018.
 */

public class JocMultijugadorDestruccio extends Joc {
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.layoutjoc);
        //Rebent es pesos de s'altre que juga
    }
}
