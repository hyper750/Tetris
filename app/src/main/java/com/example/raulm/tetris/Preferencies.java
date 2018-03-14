package com.example.raulm.tetris;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by RaulM on 14/03/2018.
 */

public class Preferencies extends Activity {
    @Override
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new FragmentPreferencies()).commit();
    }
}
