package com.example.raulm.tetris;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

/**
 * Created by RaulM on 15/03/2018.
 */

public class AdaptadorPuntuacions extends FirebaseRecyclerAdapter<Puntuacio, AdaptadorPuntuacions.ViewHolder> {
    private LayoutInflater inflador;
    private Context context;

    public AdaptadorPuntuacions(Context context, DatabaseReference reference){
        super(Puntuacio.class, R.layout.element_puntuacio, AdaptadorPuntuacions.ViewHolder.class, reference);
        inflador = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView contingut;
        public ViewHolder(View itemView) {
            super(itemView);
            contingut = (TextView)itemView.findViewById(R.id.contingut);
        }
    }

    @Override
    public AdaptadorPuntuacions.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflador.inflate(R.layout.element_puntuacio, null);
        return new ViewHolder(v);
    }

    @Override
    protected void populateViewHolder(ViewHolder viewHolder, Puntuacio model, int position) {
        viewHolder.contingut.setText(model.toString());
    }
}
