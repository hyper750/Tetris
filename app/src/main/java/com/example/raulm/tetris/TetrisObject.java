package com.example.raulm.tetris;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaulM on 18/02/2018.
 */

public class TetrisObject{
    private List<Figura> figuresEnPantalla;
    private int alturaPantalla, ampladaPantalla;
    private double velocitat = 0.2d;
    private Figura figuraActual;
    private FiguraFactory figuraFactory;
    //Modificar per linia feta
    private int puntuacio;

    public TetrisObject(View view){
        figuresEnPantalla = new ArrayList<Figura>();
        this.figuraFactory = new FiguraFactory(view, this);
    }

    public void random(){
        int numRandom = (int)(Math.random()*FiguraFactory.TAMANY);
        if(figuraActual != null){
            figuresEnPantalla.add(figuraActual);
        }
        puntuacio += 100;
        figuraActual = figuraFactory.getFigura(numRandom);
        //figuraActual.setCentreX((int)(Cuadro.TAMANY_QUADRAT/2 + figuraActual.getMaxAmplada()*Cuadro.TAMANY_QUADRAT*2)); //10 cuadros en pantalla per fila
        figuraActual.setCentreX((Cuadro.TAMANY_QUADRAT/2 + Cuadro.TAMANY_QUADRAT*5 - Math.round(figuraActual.getMaxAmplada()/2)*Cuadro.TAMANY_QUADRAT)); //10 cuadros en pantalla per fila
        figuraActual.setCentreY(figuraActual.getAltura()/2);
        figuraActual.setIncY(this.velocitat);
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
        Cuadro.TAMANY_QUADRAT = ampladaPantalla/10;

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

    public void setVelocitat(double velocitat) {
        if(figuraActual != null){
            figuraActual.setIncY(velocitat);
        }
    }

    public void restaurarVelocitat() {
        if(figuraActual != null){
            figuraActual.setIncY(velocitat);
        }
    }

    public int getPuntuacio(){
        return puntuacio;
    }

    public void liniaCompleta(){
        //Mirar si sa figura actual ha format una nova linia
        figuraActual.cuadroPerLinia();
    }
}
