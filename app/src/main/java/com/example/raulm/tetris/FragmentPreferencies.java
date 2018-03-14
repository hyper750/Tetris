package com.example.raulm.tetris;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by RaulM on 14/03/2018.
 */

public class FragmentPreferencies extends PreferenceFragment {
    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.preferencies);

    }
}
