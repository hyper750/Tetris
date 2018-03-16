package com.example.raulm.tetris;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

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
        LinearLayoutManager layout = new LinearLayoutManager(this);
        //Girar ses dades, perque firebase m'he dona ses dades només de forme ascendent
        layout.setReverseLayout(true);
        layout.setStackFromEnd(true);
        recyclerView.setLayoutManager(layout);
        recyclerView.setAdapter(adaptador);
    }
}
