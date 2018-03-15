package com.example.raulm.tetris;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by RaulM on 15/03/2018.
 */

public class ActivitatPuntuacions extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AdaptadorPuntuacions adaptador;

    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activitat_puntuacions);
        adaptador = PuntuacionsSingleton.getInstance(this).getAdaptador();
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(adaptador);
    }
}
