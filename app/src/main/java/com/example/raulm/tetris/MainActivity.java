package com.example.raulm.tetris;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }

    private void llancarJoc(){
        Intent i = new Intent(this, Joc.class);
        startActivity(i);
    }

    private void llancarJocMultijugador(){
        Intent i = new Intent(this, JocMultijugador.class);
        startActivity(i);
    }
}
