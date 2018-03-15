package com.example.raulm.tetris;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaulM on 15/03/2018.
 */

public class PuntuacionsSingleton {
    private List<Puntuacio> arrayPuntuacions;
    private AdaptadorPuntuacions adaptador;
    private static PuntuacionsSingleton instancia;

    private PuntuacionsSingleton(Context context){
        arrayPuntuacions = Puntuacio.exemple();
        adaptador = new AdaptadorPuntuacions(context, arrayPuntuacions);
    }

    public static PuntuacionsSingleton getInstance(Context context){
        if(instancia == null){
            instancia = new PuntuacionsSingleton(context);
        }
        return instancia;
    }

    public AdaptadorPuntuacions getAdaptador(){
        return adaptador;
    }
}
