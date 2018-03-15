package com.example.raulm.tetris;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by RaulM on 15/03/2018.
 */

public class ActivitatGuardar extends Activity {
    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.guardarpuntuacio);

        final EditText nom = (EditText)findViewById(R.id.nom);
        Intent i = getIntent();
        final int puntuacio = i.getExtras().getInt("puntuacio", 0);

        Button guardar = (Button)findViewById(R.id.buttoGuardarPuntuacio);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ActivitatGuardar.this, "Nom " + nom.getText().toString() + " >> " + puntuacio, Toast.LENGTH_SHORT).show();
                ActivitatGuardar.this.finish();

            }
        });
    }
}
