package com.example.raulm.tetris;

import android.graphics.Canvas;
import android.util.Log;
import android.view.View;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by RaulM on 22/02/2018.
 */

public abstract class Figura implements Cloneable{

    private int maxAmplada;

    protected abstract Cuadro[][] getImatgeArray();
    private Cuadro[][] imatge;
    private AtomicBoolean aturada = new AtomicBoolean(false);
    private TetrisObject tetrisObject;
    protected View view;
    private int centreX, centreY;

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

    public void girarDreta(){
        setCentreX(this.centreX + Cuadro.TAMANY_QUADRAT);
    }

    public void girarEsquerra(){
        setCentreX(this.centreX - Cuadro.TAMANY_QUADRAT);
    }

    public void rotar(){

        /*
        Nomes es canviar de on començ a llegir s'array
        En aquest cas tenc que canviar es centreX i centreY
        ja que canviat sa forma de llegir tindria sa mateixa figura

        -------------------
        null null null
        cuad cuad cuad
        null cuad null

        -------------------

        */
        int max = getMaxAmplada();
        Cuadro[][] cuad = new Cuadro[imatge.length][max];
        for(int x = 0; x < max; x++){
            for(int y = 0; y < imatge.length; y++){
                cuad[x][y] = imatge[y][x];
            }
        }
        this.imatge = cuad;
        //Posicion es quadros de nou
        this.setCentreX(this.centreX);
        this.setCentreY(this.centreY);
    }

    public void setCentreX(int centreX) {
        if (centreX < 0) {
            this.centreX = 0;
        } else if (centreX + getAmplada() > tetrisObject.getAmpladaPantalla()) {
            this.centreX = tetrisObject.getAmpladaPantalla() - getAmplada();
        }
        else{
            this.centreX = centreX;
        }
        for (int y = 0; y < this.imatge.length; y++) {
            int incremental = centreX;
            for (int x = 0; x < this.imatge[y].length; x++) {
                if (this.imatge[y][x] != null) {
                    this.imatge[y][x].setCentreX(incremental);
                }
                incremental += Cuadro.TAMANY_QUADRAT;
            }
        }
    }

    public void setCentreY(int centreY) {
        this.centreY = centreY;
        boolean trobat = false;
        for(int y = 0; y < this.imatge.length; y++){
            for(int x = 0; x < this.imatge[y].length; x++){
                if(this.imatge[y][x] != null) {
                    this.imatge[y][x].setCentreY(centreY);
                    trobat = true;
                }
            }
            //Si tota sa fila es null no contar
            if(trobat){
                centreY += Cuadro.TAMANY_QUADRAT;
            }
            trobat = false;
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
            centreY += tetrisObject.getVelocitat() * increment;
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
        return getMaxAmplada()*Cuadro.TAMANY_QUADRAT;
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

    public int getMaxAmplada() {
        int max = imatge[0].length;
        for(int x = 1; x < imatge.length; x++){
            if(imatge[x].length > max){
                max = imatge[x].length;
            }
        }
        return max;
    }
}
