package com.example.raulm.tetris;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaulM on 18/02/2018.
 */

public class TetrisObject{
    public static final int MAXIM_CUADROS_PER_FILA = 10;
    private static final int PUNTUACIO_PER_FILA = 100;
    //Per exemple cada 500 augmenta 0.1d de velocitat
    private static final int AUGMENTAR_VELOCITAT_CADA_PUNTUACIO = 500;
    private static final double AUGMENT_DE_VELOCITAT = 0.01d;

    private List<Figura> figuresEnPantalla;
    private int alturaPantalla, ampladaPantalla;
    private double velocitat = 0.199d;
    private Figura figuraActual;
    private FiguraFactory figuraFactory;
    //Modificar per linia feta
    private int puntuacio;
    private boolean acabat = false;

    public TetrisObject(View view){
        figuresEnPantalla = new ArrayList<Figura>();
        this.figuraFactory = new FiguraFactory(view, this);
    }

    public void random(){
        int numRandom = (int)(Math.random()*FiguraFactory.TAMANY);
        if(figuraActual != null){
            figuresEnPantalla.add(figuraActual);
        }
        figuraActual = figuraFactory.getFigura(numRandom);
        //figuraActual.setCentreX((int)(Cuadro.TAMANY_QUADRAT/2 + figuraActual.getMaxAmplada()*Cuadro.TAMANY_QUADRAT*2)); //10 cuadros en pantalla per fila
        figuraActual.setCentreX((Cuadro.TAMANY_QUADRAT/2 + Cuadro.TAMANY_QUADRAT*5 - Math.round(figuraActual.getMaxAmplada()/2)*Cuadro.TAMANY_QUADRAT)); //10 cuadros en pantalla per fila
        figuraActual.setCentreY(figuraActual.getAltura()/2);
        figuraActual.setIncY(this.velocitat);
        colocarFigura(figuraActual);
    }

    public List<Figura> getFigures(){
        return this.figuresEnPantalla;
    }

    public void setalturaPantalla(int alturaPantalla){
        this.alturaPantalla = alturaPantalla;
    }

    public Figura getFiguraActual(){
        return this.figuraActual;
    }

    public void setAmpladaPantalla(int ampladaPantalla){
        //Dividir sa pantalla per 10 blocs
        Cuadro.TAMANY_QUADRAT = ampladaPantalla/MAXIM_CUADROS_PER_FILA;

        //Amplada de pantalla per saber es centre per treure ses figures
        this.ampladaPantalla = ampladaPantalla;
        //Generar de nou ses figures segons es nou tamany de pantalla
        figuraFactory.generate();
    }

    public int getAlturaPantalla() {
        return alturaPantalla;
    }

    public int getAmpladaPantalla() {
        return ampladaPantalla;
    }

    public void setFiguraActual(Figura f){
        this.figuraActual = f;
    }

    public double getVelocitat(){
        return this.velocitat;
    }

    public boolean pucGirarDreta(Figura f){
        //Mir primer de tot si pot
        int ultimX = f.getUltimCentreX();
        if(ultimX != -1 && ultimX + Cuadro.TAMANY_QUADRAT <= getAmpladaPantalla()) {
            return !(f.girAmbColisio(Cuadro.TAMANY_QUADRAT));
        }

        return false;
    }

    public boolean pucGirarEsquerra(Figura f){
        if(f.getPrimerCentreX() - Cuadro.TAMANY_QUADRAT >= 0) {
            return !(f.girAmbColisio(-1 * Cuadro.TAMANY_QUADRAT));
        }

        return false;
    }

    public void restaurarVelocitat() {
        if(figuraActual != null){
            figuraActual.setIncY(velocitat);
        }
    }

    public int getPuntuacio(){
        return puntuacio;
    }

    public void liniesCompletes(){
        int numFigures = getFigures().size();
        Linia[] linies = contarLinies();
        for(int x = 0; x < linies.length; x++){

            if(linies[x].numeroCuadros == MAXIM_CUADROS_PER_FILA){
                puntuacio += PUNTUACIO_PER_FILA;
                //Augmentar velocitat modul de sa constant
                if(puntuacio % AUGMENTAR_VELOCITAT_CADA_PUNTUACIO == 0){
                    this.velocitat += AUGMENT_DE_VELOCITAT;
                }
                //Llevar es cuadros que toca de sa figura actual
                getFiguraActual().llevarCuadros(linies[x].centreY);
                //Posicionar un cuadro cap avall si ha llevat cuadros de sa linia

                //Llevar es cuadros que toca de cada figura
                for(int p = 0; p < numFigures; p++){
                    Figura f = getFigures().get(p);
                    f.llevarCuadros(linies[x].centreY);
                }


                //No només necessit baixar ses figures que els hi he llevat es cuadros si no també a totes ses altres
                //Fer corre totes ses figures que hi hagi per damunt
                getFiguraActual().ferCorre(linies[x].centreY);

                for(int p = 0; p < numFigures; p++){
                    Figura f = getFigures().get(p);
                    f.ferCorre(linies[x].centreY);
                }

            }
        }
    }

    private Linia[] contarLinies(){
        //Mirar si sa figura actual ha format una nova linia
        //S'encarregarà de posar a CuadroNull si té se nova linia

        int numFigures = getFigures().size();
        int incremental = figuraActual.getCentreY();
        Linia[] result = new Linia[figuraActual.getAlturaArray()];
        for(int p = 0; p < result.length; p++) {
            //Contar de sa figura actual ses linies
            result[p] = new Linia();
            result[p].centreY = incremental;
            result[p].numeroCuadros = figuraActual.getNumeroCentreY(incremental);


            //Mir totes ses files de totes ses figures
            for(int k = 0; k < numFigures; k++){
                Figura f = getFigures().get(k);
                result[p].numeroCuadros += f.getNumeroCentreY(incremental);
            }
            if(result[p].numeroCuadros > MAXIM_CUADROS_PER_FILA) {
                Log.d("Numero cuadros", "Cuadros per linia a " + result[p].centreY + " hi ha " + result[p].numeroCuadros);
            }
            incremental += Cuadro.TAMANY_QUADRAT;
        }
        return result;
    }

    private class Linia{
        private int centreY;
        private int numeroCuadros;
    }

    public void activarTurbo(){
        //Llevat perque se me descoloquen es cuadros
        figuraActual.setIncY(this.velocitat * 2d);
    }

    public boolean getAcabat(){
        return acabat;
    }

    public void setAcabat(){
        this.acabat = true;
    }


    public boolean colisioEnterra(Figura f){
        //Si fa contacte amb enterra o amb una altre figura aturar
        return f.colisioCentreYInferior(getAlturaPantalla());
    }

    public void colocarFigura(Figura f){
        int figuressize = getFigures().size();
        for(int x = 0; x < figuressize; x++){
            Figura fig = getFigures().get(x);
            while(f.getCentreY()+f.getAltura()/2 > fig.getCentreY()-fig.getAltura()/2){
                f.setCentreY(f.getCentreY()-Cuadro.TAMANY_QUADRAT);
            }
        }


    }

    public boolean tocaAdaltSaPantalla(Figura f){
        return f.colisioCentreYSuperior(0);
    }
}
