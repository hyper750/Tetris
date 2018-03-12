package com.example.raulm.tetris;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

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

        Button player2 = (Button)findViewById(R.id.player2);
        player2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llancarJocMultijugador();
            }
        });

        //Aturar sa musica quan pitji es boto home
        listener = new HomeListener(this);
        listener.setOnHomePressedListener(new HomeListener.onHomeListener() {
            @Override
            public void onHomePress() {
                stopService(new Intent(MainActivity.this, MusicaFondu.class));
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
    }

    @Override
    protected void onResume(){
        super.onResume();
        //Musica fondu
        startService(new Intent(this, MusicaFondu.class));
        listener.iniciar();
    }

    //Quan es tanqui s'aplicacio aturi sa musica
    @Override
    protected void onDestroy(){
        super.onDestroy();
        //No aturar es listener a onStop, perque si no estaria a s'activitat de joc i quan minimitzes no s'aturaria sa musica
        listener.aturar();
        stopService(new Intent(this, MusicaFondu.class));
    }
}
