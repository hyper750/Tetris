package com.example.raulm.tetris;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by RaulM on 15/03/2018.
 */

public class AdaptadorPuntuacions extends RecyclerView.Adapter<AdaptadorPuntuacions.ViewHolder> {
    private LayoutInflater inflador;
    private List<Puntuacio> arrayPuntuacions;
    private Context context;

    public AdaptadorPuntuacions(Context context, List<Puntuacio> arrayPuntuacions){
        inflador = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.arrayPuntuacions = arrayPuntuacions;
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
    public void onBindViewHolder(AdaptadorPuntuacions.ViewHolder holder, int position) {
        holder.contingut.setText(arrayPuntuacions.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return arrayPuntuacions.size();
    }
}
