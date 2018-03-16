package com.example.raulm.tetris;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    private HomeListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Botons
        Button player1 = (Button)findViewById(R.id.player1);
        player1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llancarJoc();
            }
        });

        Button player2 = (Button)findViewById(R.id.player2Temps);
        player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llancarJocMultijugador();
            }
        });

        Button player2Destruccio = (Button)findViewById(R.id.player2Destruccio);
        player2Destruccio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llancarJocMultijugadorDestruccio();
            }
        });

        Button opcions = (Button)findViewById(R.id.opcions);
        opcions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llancarOpcions();
            }
        });

        Button llistar = (Button)findViewById(R.id.llistarPuntuacions);
        llistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ActivitatPuntuacions.class));
            }
        });

        //Aturar sa musica quan pitji es boto home
        listener = new HomeListener(this);
        listener.setOnHomePressedListener(new HomeListener.onHomeListener() {
            @Override
            public void onHomePress() {
                SeleccioMusica.getInstance(MainActivity.this).aturar();
            }
        });

    }

    private void llancarJoc(){
        Intent i = new Intent(this, Joc.class);
        startActivity(i);
    }

    private void llancarJocMultijugador(){
        Intent i = new Intent(this, JocMultijugador.class);
        startActivity(i);
        //Toast.makeText(this, "Mode temps incomplert", Toast.LENGTH_SHORT).show();
    }

    private void llancarJocMultijugadorDestruccio(){
        Intent i = new Intent(this, JocMultijugadorDestruccio.class);
        startActivity(i);
        //Toast.makeText(this, "Mode destrucció incomplert", Toast.LENGTH_SHORT).show();
    }

    private void llancarOpcions(){
        Intent i = new Intent(this, Preferencies.class);
        startActivity(i);
    }

    @Override
    protected void onResume(){
        super.onResume();
        SeleccioMusica.getInstance(this).iniciarMusica(SeleccioMusica.getInstance(this).musicaActivable());
        listener.iniciar();
    }

    //Quan es tanqui s'aplicacio aturi sa musica
    @Override
    protected void onDestroy(){
        super.onDestroy();
        //Atur es servei rellenant temps musica
        SeleccioMusica.getInstance(this).aturar();
        //No aturar es listener a onStop, perque si no estaria a s'activitat de joc i quan minimitzes no s'aturaria sa musica
        listener.aturar();
    }

}
