package com.example.raulm.tetris;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaulM on 18/02/2018.
 */

public class TetrisObject{
    private static List<Figura> totalFigures = new ArrayList<Figura>();
    private List<Figura> figures;
    private int alturaPantalla, ampladaPantalla;
    private double velocitat = 0.5d;
    private Figura figuraActual;
    private View view;

    public TetrisObject(View view){
        this.view = view;
        figures = new ArrayList<Figura>();
    }

    public void random(){
        int numRandom = (int)(Math.random()*totalFigures.size());
        if(figuraActual != null){
            figures.add(figuraActual);
        }
        figuraActual = totalFigures.get(numRandom).clone();
        figuraActual.setCentreX(this.ampladaPantalla/2);
        figuraActual.setCentreY(figuraActual.getAltura()/2);
        figuraActual.setIncY(this.velocitat);
    }

    public List<Figura> getFigures(){
        return this.figures;
    }

    public void setalturaPantalla(int alturaPantalla){
        this.alturaPantalla = alturaPantalla;
    }

    public Figura getFiguraActual(){
        return this.figuraActual;
    }

    public void setAmpladaPantalla(int ampladaPantalla){
        //Dividir sa pantalla per 10 blocs
        Cuadro.TAMANY_QUADRAT = ampladaPantalla/10;

        //Amplada de pantalla per saber es centre per treure ses figures
        this.ampladaPantalla = ampladaPantalla;

        //Nou tamany de figures
        totalFigures.clear();
        totalFigures.add(new FiguraO(this.view, this));
    }

    public int getAlturaPantalla() {
        return alturaPantalla;
    }

    public int getAmpladaPantalla() {
        return ampladaPantalla;
    }
}
