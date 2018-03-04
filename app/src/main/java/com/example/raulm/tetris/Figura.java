package com.example.raulm.tetris;

import android.graphics.Canvas;
import android.util.Log;
import android.view.View;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by RaulM on 22/02/2018.
 */

public abstract class Figura implements Cloneable{

    private ICuadro[][] imatge;
    private boolean aturada = false;
    private TetrisObject tetrisObject;
    protected View view;
    private int centreX, centreY;

    public Figura(View view, TetrisObject tetrisObject){
        this.view = view;
        this.imatge = getImatgeArray();
        this.tetrisObject = tetrisObject;
    }

    protected abstract ICuadro[][] getImatgeArray();

    public void dibuixar(Canvas canvas){
        for(int y = 0; y < this.imatge.length; y++){
            for(int x = 0; x < this.imatge[y].length; x++){
                this.imatge[y][x].dibuixar(canvas);
            }
        }
    }

    public void girarDreta(){
        int primerX = getUltimCentreX();
        if(primerX != -1 && primerX + Cuadro.TAMANY_QUADRAT <= tetrisObject.getAmpladaPantalla()){
            setCentreX(this.centreX + Cuadro.TAMANY_QUADRAT);
        }
    }

    public void girarEsquerra(){
        if(getPrimerCentreX() - Cuadro.TAMANY_QUADRAT >= 0){
            setCentreX(this.centreX - Cuadro.TAMANY_QUADRAT);
        }
    }

    public void rotar() {
        /*
        null cuad null
        null cuad null
        null cuad cuad

        ----------------
        null null null
        cuad cuad cuad
        null null cuad

        -----------------
        null cuad null
        null cuad null
        null cuad cuad
         */

        int amplada = getMaxAmpladaAmbNull();
        ICuadro[][] cuad = new ICuadro[amplada][imatge.length];
        //Rotar, mesclar una columna i una fila
        int col = 0;
        for (int y = 0; y < imatge.length; y++) {
            int fila = imatge.length-1;
            for (int x = 0; x < amplada; x++) {
                cuad[col][fila] = imatge[x][y];
                fila--;
            }
            col++;
        }

        this.imatge = cuad;
        //Si no està a nes borde actualitzar x
        this.setCentreX(this.centreX);
        this.setCentreY(this.centreY);
        //Si està a nes borde esquerra sumar una posició i actualitzar
        while(getPrimerCentreX()-Cuadro.TAMANY_QUADRAT/2 < 0){
            setCentreX(this.centreX+Cuadro.TAMANY_QUADRAT);
        }
        //Si està a nes borde dret sumar una posició i actualitzar
        int ultim;
        while ((ultim = getUltimCentreX()) != -1 && ultim+Cuadro.TAMANY_QUADRAT/2 > tetrisObject.getAmpladaPantalla()){
            setCentreX(this.centreX-Cuadro.TAMANY_QUADRAT);
        }
    }

    public void setCentreX(int centreX) {
        //Si no ha arribat a nes mínim ni màxim de sa pantalla
        this.centreX = centreX;
        for (int y = 0; y < this.imatge.length; y++) {
            int incremental = this.centreX;
            for (int x = 0; x < this.imatge[y].length; x++) {
                this.imatge[y][x].setCentreX(incremental);
                incremental += Cuadro.TAMANY_QUADRAT;
            }
        }
    }

    public int getPrimerCentreX(){
        for(int x = 0; x < imatge.length; x++){
            for(int y = 0; y < imatge[x].length; y++){
                //Mirar fila 0 i cada col
                if(!(imatge[y][x] instanceof CuadroNull)){
                    return imatge[y][x].getCentreX();
                }
            }
        }
        return -1;
    }

    public int getUltimCentreX(){
        for(int x = imatge.length-1; x >= 0; x--){
            for(int y = imatge[x].length-1; y >= 0; y--){
                //Mirar fila 0 i cada col
                if(!(imatge[y][x] instanceof CuadroNull)){
                    return imatge[y][x].getCentreX();
                }
            }
        }
        return -1;
    }

    public void setCentreY(int centreY) {
        this.centreY = centreY;
        for(int y = 0; y < this.imatge.length; y++){
            for(int x = 0; x < this.imatge[y].length; x++){
                this.imatge[y][x].setCentreY(centreY);
            }
            centreY += Cuadro.TAMANY_QUADRAT;
        }
    }

    public void setIncY(double increment) {
        for(int y = 0; y < this.imatge.length; y++){
            for(int x = 0; x < this.imatge[y].length; x++){
                this.imatge[y][x].setIncY(increment);
            }
        }
    }

    public void incrementarPosicio(double increment) {
        //Si no està aturada incrementar Y
        if(!this.aturada) {
            centreY += tetrisObject.getVelocitat() * increment;
            for (int y = this.imatge.length - 1; y >= 0; y--) {
                for (int x = this.imatge[y].length - 1; x >= 0; x--) {
                    this.imatge[y][x].incrementarPosicio(increment);
                }
            }

            //Mirar colisió amb altres figures
            int totalFigures = tetrisObject.getFigures().size();
            boolean colisio = false;
            for(int x = 0; x < totalFigures && !colisio; x++){
                colisio = this.colisio(tetrisObject.getFigures().get(x));
            }

            if(colisio){
                this.setAturada();
            }

        }
    }

    @Override
    public Figura clone() {
        try{
            Figura f = (Figura)super.clone();
            //Es tipus primitius es clonen pero es objectes es donen nomes sa referencia, per tant no es clonen
            f.imatge = f.getImatgeArray();
            f.aturada = false;
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
        for(int y = 0; y < this.imatge.length; y++){
            for(int x = 0; x < this.imatge[y].length; x++){
                //Per cada cuadro de sa figura invocada
                if(!(this.imatge[y][x] instanceof CuadroNull)) {
                    for (int q = 0; q < f.imatge.length; q++) {
                        for (int z = 0; z < f.imatge[q].length; z++) {
                            //Per cada cuadro de sa figura pasada per parametre
                            if (!(f.imatge[q][z] instanceof CuadroNull)) {
                            //Segueix mirant col·lisions si no té col·lisió aquest cuadro
                                if(this.imatge[y][x].colisio(f.imatge[q][z])){
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public synchronized void setAturada() {
        if(!this.aturada){
            this.aturada = true;
            //setIncY(0d);
            tetrisObject.random();
        }
    }

    public int getMaxAmplada() {
        int max = -1;
        for(int y = 0; y < imatge.length; y++) {
            int cont = 0;
            for (int x = 0; x < imatge[y].length; x++) {
                if(!(imatge[y][x] instanceof CuadroNull)){
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
