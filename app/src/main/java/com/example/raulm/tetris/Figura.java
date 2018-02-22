package com.example.raulm.tetris;

import android.graphics.Canvas;
import android.util.Log;

/**
 * Created by RaulM on 22/02/2018.
 */

public abstract class Figura implements Cloneable{
    /*void dibuixar(Canvas canvas);
    void setCentreX(int centreX);
    void setCentreY(int centreY);
    void setIncY(double increment);
    void incrementarPosicio(double increment);
    Figura clone();
    int getAltura();
    boolean colisio(Figura f);
    int distancia(Figura f);
    void setAturada(boolean aturada);*/

    protected abstract int getAmpladaArray();
    protected abstract int getAlturaArray();
    protected abstract Cuadro[][] getImatgeArray();
    private boolean aturada = false;
    private TetrisObject tetrisObject;

    public Figura(TetrisObject tetrisObject){
        this.tetrisObject = tetrisObject;
    }

    public void dibuixar(Canvas canvas){
        for(int y = 0; y < getAlturaArray(); y++){
            for(int x = 0; x < getAmpladaArray(); x++){
                getImatgeArray()[y][x].dibuixar(canvas);
            }
        }
    }

    public void setCentreX(int centreX) {
        for(int y = 0; y < getAlturaArray(); y++){
            int incremental = centreX;
            for(int x = 0; x < getAmpladaArray(); x++){
                getImatgeArray()[y][x].setCentreX(incremental);
                incremental += Cuadro.TAMANY_QUADRAT;
            }
        }
    }

    public void setCentreY(int centreY) {
        for(int y = 0; y < getAlturaArray(); y++){
            for(int x = 0; x < getAmpladaArray(); x++){
                getImatgeArray()[y][x].setCentreY(centreY);
            }
            centreY += Cuadro.TAMANY_QUADRAT;
        }
    }

    public void setIncY(double increment) {
        for(int y = 0; y < getAlturaArray(); y++){
            for(int x = 0; x < getAmpladaArray(); x++){
                getImatgeArray()[y][x].setIncY(increment);
            }
        }
    }

    public void incrementarPosicio(double increment) {
        for(int y = 0; y < getAlturaArray(); y++){
            for(int x = 0; x < getAmpladaArray(); x++){
                getImatgeArray()[y][x].incrementarPosicio(increment);
            }
        }
    }

    @Override
    public Figura clone() {
        try{
            return (Figura)super.clone();
        }
        catch (CloneNotSupportedException e){
            Log.e("Error clonació", "No s'ha pogut clonar FiguraO", e);
        }
        return null;
    }

    public int getAltura() {
        return getAlturaArray()*Cuadro.TAMANY_QUADRAT;
    }

    public boolean colisio(Figura f) {
        return false;
    }

    public int distancia(Figura f) {
        return 0;
    }

    public void setAturada(boolean aturat) {
        if(!this.aturada){
            this.aturada = aturat;
            tetrisObject.random();
        }
    }
}
