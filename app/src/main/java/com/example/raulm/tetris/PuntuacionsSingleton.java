package com.example.raulm.tetris;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaulM on 15/03/2018.
 */

public class PuntuacionsSingleton {
    private AdaptadorPuntuacions adaptador;
    private static PuntuacionsSingleton instancia;
    private DatabaseReference puntsReference;

    private final static String _PUNTS_NODE = "puntuacions";

    private PuntuacionsSingleton(Context context){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
        puntsReference = database.getReference().child(_PUNTS_NODE);
        adaptador = new AdaptadorPuntuacions(context, puntsReference.orderByChild("punts"));
    }

    public DatabaseReference getPuntsReference(){
        return puntsReference;
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
