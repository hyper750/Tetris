package com.example.raulm.tetris;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

/**
 * Created by RaulM on 12/03/2018.
 */

public class HomeListener {
    public interface onHomeListener{
        void onHomePress();
    }

    private Context context;
    private IntentFilter filter;
    private onHomeListener listener;
    private Receptor receptor;

    public HomeListener(Context context){
        this.context = context;
        filter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
    }

    public void setOnHomePressedListener(onHomeListener listener){
        this.listener = listener;
        receptor = new Receptor();
    }

    public void iniciar(){
        if(receptor != null){
            context.registerReceiver(receptor, filter);
        }
    }

    public void aturar(){
        if(receptor != null){
            context.unregisterReceiver(receptor);
        }
    }

    private class Receptor extends BroadcastReceiver{

        private static final String SYSTEM_DIALOG_REASON_KEY = "reason";
        private static final String SYSTEM_DIALOG_REASON_HOME_KEY  = "homekey";
        private static final String SYSTEM_DIALOG_REASON_RECENTAPPS = "recentapps";

        @Override
        public void onReceive(Context context, Intent intent) {
            String accio = intent.getAction();
            if(accio.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)){
                String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
                if(listener != null && reason != null){
                    //Log.d("Reason", "Rao >> " + reason);
                    if(reason.equals(SYSTEM_DIALOG_REASON_HOME_KEY) || reason.equals(SYSTEM_DIALOG_REASON_RECENTAPPS)){
                        listener.onHomePress();
                    }
                }
            }
        }
    }
}
