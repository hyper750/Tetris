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
    private int centreX, centreY;
    private byte rotacio = 0;

    private static byte NORMAL = 0;
    private static byte DRETA = 1;
    private static byte ABAIX = 2;
    private static byte ESQUERRA = 3;

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

    public void rotar() {
        /*
        null cuad null
        null cuad null
        null cuad cuad

        ----------------
        null null null
        cuad cuad cuad
        cuad null null

        ----------------
        cuad cuad null
        null cuad null
        null cuad null

        ----------------
        null null cuad
        cuad cuad cuad
        null null null

         */
        /*if(rotacio == NORMAL || rotacio == ABAIX){
            //TENC SA FIGURA NORMAL ROTAR A LA DRETA

        }
        else if(rotacio == DRETA || rotacio == ESQUERRA){
            //tenc sa figura a la dreta rotar a abaix

        }*/

        int amplada = getMaxAmpladaAmbNull();
        Cuadro[][] cuad = new Cuadro[amplada][imatge.length];
        //Rotar, mesclar una columna i una fila
        for (int y = 0; y < imatge.length; y++) {
            for (int x = 0; x < amplada; x++) {
                cuad[x][y] = imatge[y][x];
            }
        }


        this.imatge = cuad;
        this.rotacio = (byte) ((this.rotacio + 1) % 4);
        //Posicion es quadros de nou
        this.setCentreX(this.centreX);
        this.setCentreY(this.centreY);
    }

    public void setCentreX(int centreX) {
        if (centreX-getMaxAmplada()*Cuadro.TAMANY_QUADRAT/2 < 0) {
            //this.centreX = 0;
        } else if (centreX + getMaxAmpladaAmbNull()*Cuadro.TAMANY_QUADRAT > tetrisObject.getAmpladaPantalla()) {
            //this.centreX = tetrisObject.getAmpladaPantalla() - getAmplada();
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
            //if(trobat){
                centreY += Cuadro.TAMANY_QUADRAT;
            //}
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
        int max = -1;
        for(int y = 0; y < imatge.length; y++) {
            int cont = 0;
            for (int x = 1; x < imatge[y].length; x++) {
                if(imatge[y][x] != null){
                    cont++;
                }
            }
            if(cont > max){
                max = cont;
            }
        }
        return max;
    }

    public int getMaxAmpladaAmbNull() {
        int maxAmpladaAmbNull = imatge[0].length;
        for(int x = 1; x < imatge.length; x++){
            if(imatge[x].length > maxAmpladaAmbNull){
                maxAmpladaAmbNull = imatge[x].length;
            }
        }
        return maxAmpladaAmbNull;
    }
}
