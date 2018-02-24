package com.example.raulm.tetris;

import android.graphics.Canvas;
import android.util.Log;
import android.view.View;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by RaulM on 22/02/2018.
 */

public abstract class Figura implements Cloneable{

    protected abstract Cuadro[][] getImatgeArray();
    private Cuadro[][] imatge;
    private AtomicBoolean aturada = new AtomicBoolean(false);
    private TetrisObject tetrisObject;
    protected View view;

    public Figura(View view, TetrisObject tetrisObject){
        this.view = view;
        this.imatge = getImatgeArray();
        this.tetrisObject = tetrisObject;
    }

    public void dibuixar(Canvas canvas){
        for(int y = 0; y < this.imatge.length; y++){
            for(int x = 0; x < this.imatge[y].length; x++){
                if(this.imatge[y][x] != null) {
                    this.imatge[y][x].dibuixar(canvas);
                }
            }
        }
    }

    public void setCentreX(int centreX) {
        for(int y = 0; y < this.imatge.length; y++){
            int incremental = centreX;
            for(int x = 0; x < this.imatge[y].length; x++){
                if(this.imatge[y][x] != null) {
                    this.imatge[y][x].setCentreX(incremental);
                }
                incremental += Cuadro.TAMANY_QUADRAT;
            }
        }
    }

    public void setCentreY(int centreY) {
        for(int y = 0; y < this.imatge.length; y++){
            for(int x = 0; x < this.imatge[y].length; x++){
                if(this.imatge[y][x] != null) {
                    this.imatge[y][x].setCentreY(centreY);
                }
            }
            centreY += Cuadro.TAMANY_QUADRAT;
        }
    }

    public void setIncY(double increment) {
        for(int y = 0; y < this.imatge.length; y++){
            for(int x = 0; x < this.imatge[y].length; x++){
                if(this.imatge[y][x] != null) {
                    this.imatge[y][x].setIncY(increment);
                }
            }
        }
    }

    public void incrementarPosicio(double increment) {
        if(!this.aturada.get()) {
            for (int y = this.imatge.length - 1; y >= 0; y--) {
                for (int x = this.imatge[y].length - 1; x >= 0; x--) {
                    if(this.imatge[y][x] != null) {
                        this.imatge[y][x].incrementarPosicio(increment);
                    }
                }
            }
        }
    }

    @Override
    public Figura clone() {
        try{
            Figura f = (Figura)super.clone();
            //Es tipus primitius es clonen pero es objectes es donen nomes sa referencia, per tant no es clonen
            f.imatge = f.getImatgeArray();
            f.aturada = new AtomicBoolean(false);
            return f;
        }
        catch (CloneNotSupportedException e){
            Log.e("Error clonació", "No s'ha pogut clonar FiguraO", e);
        }
        //return new FiguraO(this.view, tetrisObject);
        return null;
    }

    public int getAltura() {
        return this.imatge.length*Cuadro.TAMANY_QUADRAT;
    }

    public int getAmplada(){
        int max = this.imatge[0].length;
        for(int x = 1; x < this.imatge.length; x++){
            if(this.imatge[x].length > max){
                max = this.imatge[x].length;
            }
        }
        return max*Cuadro.TAMANY_QUADRAT;
    }

    public boolean colisio(Figura f) {
        return distancia(f) <= 0;
    }

    public int distancia(Figura f) {
        int max = 0;
        for(int x = 0; x < imatge.length; x++){

        }
        return max;
    }

    public void setAturada() {
        if(!this.aturada.get()){
            this.aturada.set(true);
            tetrisObject.random();
        }
    }
}
