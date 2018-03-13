package com.example.raulm.tetris;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

/**
 * Created by RaulM on 22/02/2018.
 */

public abstract class Figura implements Cloneable{

    private ICuadro[][] imatge;
    private boolean aturada = false;
    private TetrisObject tetrisObject;
    protected View view;
    private int centreX, centreY;
    private double incY;

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
        this.incY = increment;
        for(int y = 0; y < this.imatge.length; y++){
            for(int x = 0; x < this.imatge[y].length; x++){
                this.imatge[y][x].setIncY(increment);
            }
        }
    }

    public int getCentreY() {
        return centreY;
    }

    public int getAlturaArray(){
        return imatge.length;
    }

    public int getNumeroCentreY(int centreY, double tolerancia){
        int cont = 0;
        for(int x = 0; x < imatge.length; x++){
            for(int y = 0; y < imatge[x].length; y++){
                int centreYCuadro = imatge[x][y].getCentreY();
                if(centreYCuadro >= centreY * (1-tolerancia/100) && centreYCuadro <= centreY * (1+tolerancia/100)){
                    cont++;
                }
            }
        }
        return cont;
    }

    public void girarDreta(){
        ICuadro ultim = getUltimCentreX();
        int ultimX = ultim.getCentreX();
        if(ultimX != -1 && ultimX + Cuadro.TAMANY_QUADRAT <= tetrisObject.getAmpladaPantalla()){
            ICuadro c = new Cuadro(view, Color.WHITE);
            boolean colisio = false;
            boolean trobat = false;
            for(int y = imatge[0].length-1; !trobat && y >= 0; y--) {
                for (int x = imatge.length - 1; x >= 0 && !colisio; x--) {
                    if (!(imatge[x][y] instanceof CuadroNull)) {
                        trobat = true;
                        c.setCentreX(imatge[x][y].getCentreX() + Cuadro.TAMANY_QUADRAT);
                        c.setCentreY(imatge[x][y].getCentreY());
                        colisio = possibleColisio(c);
                    }
                }
            }
            if(!colisio){
                setCentreX(this.centreX + Cuadro.TAMANY_QUADRAT);
            }
        }
    }

    public void llevarCuadros(int centreY){
        for(int x = 0; x < imatge.length; x++){
            for(int y = 0; y < imatge[x].length; y++){
                if(imatge[x][y].getCentreY() == centreY){
                    imatge[x][y] = new CuadroNull();
                }
            }
        }
    }

    public void girarEsquerra(){
        if(getPrimerCentreX() - Cuadro.TAMANY_QUADRAT >= 0){
            ICuadro c = new Cuadro(view, Color.WHITE);
            boolean colisio = false;
            boolean trobat = false;
            for(int y = 0; !trobat && y < imatge[0].length; y++) {
                for (int x = imatge.length - 1; x >= 0 && !colisio; x--) {
                    if (!(imatge[x][y] instanceof CuadroNull)) {
                        trobat = true;
                        c.setCentreX(imatge[x][y].getCentreX() - Cuadro.TAMANY_QUADRAT);
                        c.setCentreY(imatge[x][y].getCentreY());
                        colisio = possibleColisio(c);
                    }
                }
            }
            if(!colisio){
                setCentreX(this.centreX - Cuadro.TAMANY_QUADRAT);
            }
        }
    }

    public boolean possibleColisio(ICuadro c){
        int totalFigures = tetrisObject.getFigures().size();
        boolean colisio = false;
        for(int x = 0; x < totalFigures && !colisio; x++){
            Figura ac = tetrisObject.getFigures().get(x);
            for(int y = 0; y < ac.imatge.length && !colisio; y++){
                for(int z = 0; z < ac.imatge[y].length && !colisio; z++){
                    if(c.colisio(ac.imatge[y][z])){
                        return true;
                    }
                }
            }
        }
        return false;
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
        boolean colisio = false;
        Figura copia = this.clone();
        copia.imatge = cuad;
        //Si no està a nes borde actualitzar x
        copia.colocarRotacio();
        int totalF = tetrisObject.getFigures().size();
        for(int x = 0; x < totalF; x++){
            Figura fi = tetrisObject.getFigures().get(x);
            if(copia.colisio(fi)){
                Log.d("Colisio Rotacio", "Hi ha colisio");
                colisio = true;
               break;
            }
        }
        if(!colisio) {
            this.imatge = cuad;
        }
        //Colocar de nou, perque modific sa posicio des cuadros
        this.colocarRotacio();

    }

    private void colocarRotacio(){
        setCentreY(this.centreY);
        setCentreX(this.centreX);
        //Si està a nes borde esquerra sumar una posició i actualitzar
        while (getPrimerCentreX() - Cuadro.TAMANY_QUADRAT / 2 < 0) {
            setCentreX(centreX + Cuadro.TAMANY_QUADRAT);
        }
        //Si està a nes borde dret sumar una posició i actualitzar
        int ultim;
        while ((ultim = getUltimCentreX().getCentreX()) != -1 && ultim + Cuadro.TAMANY_QUADRAT / 2 > tetrisObject.getAmpladaPantalla()) {
            setCentreX(centreX - Cuadro.TAMANY_QUADRAT);
        }
    }

    private int getPrimerCentreX(){
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

    private ICuadro getUltimCentreX(){
        for(int x = imatge.length-1; x >= 0; x--){
            for(int y = imatge[x].length-1; y >= 0; y--){
                //Mirar fila 0 i cada col
                if(!(imatge[y][x] instanceof CuadroNull)){
                    return imatge[y][x];
                }
            }
        }
        return null;
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
        }
    }

    public boolean colisioEnterra(){
        //Si fa contacte amb enterra o amb una altre figura aturar
        for(int y = this.imatge.length-1; y >= 0; y--){
            for(int x = 0; x < this.imatge[y].length; x++){
                ICuadro cuad = imatge[y][x];
                if(cuad.getCentreY()+cuad.getAltura()/2 >= view.getHeight()){
                    return true;
                }
            }
        }

        return false;
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
        /*Figura per defecte millor?¿*/
        return new FiguraS(this.view, tetrisObject);
        //return null;
    }

    public int getAltura() {
        return this.imatge.length*Cuadro.TAMANY_QUADRAT;
    }

    public boolean colisio(Figura f) {
        //Mir si tots es cuadros meus colisionen amb algun d'una altre figura
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

    public void setAturada() {
        if(!this.aturada){
            this.aturada = true;
            //Amb setIncY hi ha un retard a sa última figura
            //setIncY(0d);
        }
    }

    public boolean getAturada(){
        return aturada;
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
