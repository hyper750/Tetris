package com.example.raulm.tetris;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String ultimNom = pref.getString("ultimNom", "");

        final EditText nom = (EditText)findViewById(R.id.nom);
        nom.setText(ultimNom);
        Intent i = getIntent();
        final int puntuacio = i.getExtras().getInt("puntuacio", 0);

        Button guardar = (Button)findViewById(R.id.buttoGuardarPuntuacio);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(ActivitatGuardar.this, "Nom " + nom.getText().toString() + " >> " + puntuacio, Toast.LENGTH_SHORT).show();
                String nomObtingut = nom.getText().toString();
                pref.edit().putString("ultimNom", nomObtingut).commit();
                ActivitatGuardar.this.finish();
                PuntuacionsSingleton.getInstance(ActivitatGuardar.this).getPuntsReference().push()
                        .setValue(new Puntuacio(puntuacio, nomObtingut, System.currentTimeMillis()));
            }
        });
    }
}
