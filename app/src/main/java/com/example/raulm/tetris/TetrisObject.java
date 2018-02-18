package com.example.raulm.tetris;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaulM on 18/02/2018.
 */

public class TetrisObject {
    private static List<Figura> totalFigures = new ArrayList<Figura>();
    private List<Figura> figures;
    private int alturaPantalla, ampladaPantalla;
    private double velocitat = 0.5d;
    private Figura figuraActual;

    public TetrisObject(View view){
        figures = new ArrayList<Figura>();
        totalFigures.add(new FiguraO(view));
        totalFigures.add(new FiguraI(view));
        totalFigures.add(new FiguraS(view));
        totalFigures.add(new FiguraZ(view));
    }

    public void random(){
        int numRandom = (int)(Math.random())*totalFigures.size();
        try {
            figuraActual = totalFigures.get(numRandom).clone();
            figuraActual.setCentreX(this.ampladaPantalla/2);
            figuraActual.setCentreY(Figura.TAMANY_QUADRAT);
            figuraActual.setIncY(this.velocitat);
        }
        catch (CloneNotSupportedException e){
            Log.e("Clone", "Error no es pot clonar l'objecte figura", e);
        }
    }

    public void canviarVelocitat(double velocitat){
        this.velocitat = velocitat;
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
        this.ampladaPantalla = ampladaPantalla;
    }
}
