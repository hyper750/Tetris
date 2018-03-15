package com.example.raulm.tetris;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.widget.Toast;

/**
 * Created by RaulM on 14/03/2018.
 */

public class FragmentPreferencies extends PreferenceFragment {
    private int id;
    private boolean activat;

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.preferencies);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        id = Integer.parseInt(pref.getString("tipusMusica", "0"));
        activat = pref.getBoolean("musicaActivada", true);

        ListPreference llistaMusica = (ListPreference)findPreference("tipusMusica");
        llistaMusica.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                int nouValor = Integer.parseInt((String)newValue);
                if(nouValor != id) {
                    id = nouValor;
                    SeleccioMusica.getInstance(getActivity()).aturar();
                    SeleccioMusica.getInstance(getActivity()).iniciarMusica(nouValor);
                }
                return true;
            }
        });

        CheckBoxPreference activada = (CheckBoxPreference)findPreference("musicaActivada");
        activada.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Boolean activada = (Boolean)newValue;
                if(activada != activat){
                    activat = activada;
                    if(activada){
                        SeleccioMusica.getInstance(getActivity()).iniciarMusica(true);
                    }
                    else{
                        SeleccioMusica.getInstance(getActivity()).aturar();
                    }
                }
                return true;
            }
        });
    }
}
