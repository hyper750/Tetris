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

    public double getIncY() {
        return incY;
    }

    public int getCentreY() {
        return centreY;
    }

    public void colocarSenseAtravesar(Figura f){
        //Colcoar this figura perque no atravesi f
        for(int x = 0; x < imatge.length; x++){
            for(int y = 0; y < imatge[x].length; y++){
                //Per cada cuadro de this figura
                ICuadro thisCuad = imatge[x][y];
                if(!(thisCuad instanceof CuadroNull)) {
                    for (int i = 0; i < f.imatge.length; i++) {
                        for (int o = 0; o < f.imatge[i].length; o++) {
                            ICuadro fCuad = f.imatge[i][o];
                            //Mirar si està atravesada
                            if (!(fCuad instanceof CuadroNull) && thisCuad.getCentreX() == fCuad.getCentreX()) {
                                //Si estan a ses mateixes x pero atravesades, colocar
                                while ((thisCuad.getCentreY() - thisCuad.getAltura() / 2 < fCuad.getCentreY() - fCuad.getAltura() / 2 &&
                                        thisCuad.getCentreY() + thisCuad.getAltura() / 2 > fCuad.getCentreY() - fCuad.getAltura() / 2)
                                        ) {
                                    setCentreY(centreY - 1);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void senseAtravesarBordeInferior(int senseAtravesarY){
        for(int x = 0; x < imatge.length; x++){
            for(int y = 0; y < imatge[x].length; y++){
                ICuadro cuad = imatge[x][y];
                //Si es borde infeior atravessa
                while(cuad.getCentreY() + cuad.getAltura()/2 > senseAtravesarY){
                    setCentreY(getCentreY() - 1);
                }
            }
        }
    }

    public int getAlturaArray(){
        return imatge.length;
    }

    public int getNumeroCentreY(int centreY){
        int cont = 0;
        for(int x = 0; x < imatge.length; x++){
            for(int y = 0; y < imatge[x].length; y++){
                int centreYCuadro = imatge[x][y].getCentreY();
                if(!(imatge[x][y] instanceof CuadroNull) && centreY == centreYCuadro){
                    cont++;
                }
            }
        }

        return cont;
    }

    public void girarDreta(){
        setCentreX(this.centreX + Cuadro.TAMANY_QUADRAT);
    }

    public void girarEsquerra(){
        setCentreX(this.centreX - Cuadro.TAMANY_QUADRAT);
    }

    public boolean girAmbColisio(int diferenciaX){
        ICuadro c = new Cuadro(view, Color.WHITE);
        for(int y = 0; y < imatge[0].length; y++) {
            for (int x = imatge.length - 1; x >= 0; x--) {
                if (!(imatge[x][y] instanceof CuadroNull)) {
                    c.setCentreX(imatge[x][y].getCentreX() + diferenciaX);
                    c.setCentreY(imatge[x][y].getCentreY());
                    if(possibleColisio(c)){
                        return  true;
                    }
                }
            }
        }

        return false;
    }

    public boolean totsNull(){
        for(int x = 0; x < imatge.length; x++){
            for(int y = 0; y < imatge[x].length; y++){
                if(!(imatge[x][y] instanceof CuadroNull)){
                    return false;
                }
            }
        }

        return true;
    }

    public void llevarCuadros(int centreY){
        for(int x = 0; x < imatge.length; x++){
            for(int y = 0; y < imatge[x].length; y++){
                if(centreY == imatge[x][y].getCentreY()){
                    imatge[x][y] = new CuadroNull();
                }
            }
        }
    }

    public void ferCorre(int centreY){
        for(int x = 0; x < imatge.length; x++){
            for(int y = 0; y < imatge[x].length; y++){
                //Fer corre un quadrat totes ses figures que estiguin damunt sa zona
                if(imatge[x][y].getCentreY() < centreY){
                    imatge[x][y].setCentreY(imatge[x][y].getCentreY() + Cuadro.TAMANY_QUADRAT);
                }
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

    private void colocarRotacio(){
        setCentreY(this.centreY);
        setCentreX(this.centreX);
        //Si està a nes borde esquerra sumar una posició i actualitzar
        while (getPrimerCentreX() - Cuadro.TAMANY_QUADRAT / 2 < 0) {
            setCentreX(centreX + Cuadro.TAMANY_QUADRAT);
        }
        //Si està a nes borde dret sumar una posició i actualitzar
        int ultim;
        while ((ultim = getUltimCentreX()) != -1 && ultim + Cuadro.TAMANY_QUADRAT / 2 > tetrisObject.getAmpladaPantalla()) {
            setCentreX(centreX - Cuadro.TAMANY_QUADRAT);
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
        return -100;
    }

    public void incrementarPosicio(double increment) {
        //Si no està aturada incrementar Y
        //if(!this.aturada) {
        centreY += incY * increment;
        for (int y = this.imatge.length - 1; y >= 0; y--) {
            for (int x = this.imatge[y].length - 1; x >= 0; x--) {
                this.imatge[y][x].incrementarPosicio(increment);
            }
        }
        //}
    }

    @Override
    public Figura clone() {
        try{
            Figura f = (Figura)super.clone();
            //Es tipus primitius es clonen pero es objectes es donen nomes sa referencia, per tant no es clonen
            f.imatge = f.getImatgeArray();
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

    public int getAlturaSenseNull(){
        int res = 0;
        int cont = 0;
        for(int x = 0; x < imatge.length; x++){
            for(int y = 0; y < imatge[x].length; y++){
                if(!(imatge[y][x] instanceof CuadroNull)){
                    cont++;
                }
            }

            if(cont > res){
                res = cont;
            }
            cont = 0;
        }
        return res;
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

    public boolean colisioCentreYInferior(int col){
        for(int y = this.imatge.length-1; y >= 0; y--){
            for(int x = 0; x < this.imatge[y].length; x++){
                ICuadro cuad = imatge[y][x];
                if(cuad.getCentreY()+cuad.getAltura()/2 >= col){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean colisioCentreYSuperior(int col){
        for(int x = 0; x < imatge.length; x++){
            for(int y = 0; y < imatge[x].length; y++){
                ICuadro cuad = imatge[x][y];
                if(!(cuad instanceof CuadroNull) && cuad.getCentreY() - cuad.getAltura()/2 <= col){
                    return true;
                }
            }
        }
        return false;
    }
}