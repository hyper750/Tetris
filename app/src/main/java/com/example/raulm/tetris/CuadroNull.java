package com.example.raulm.tetris;

import android.graphics.Canvas;

/**
 * Created by RaulM on 02/03/2018.
 */

public class CuadroNull implements ICuadro {
    @Override
    public void dibuixar(Canvas canvas) {

    }

    @Override
    public void incrementarPosicio(double retard) {

    }

    @Override
    public double distancia(ICuadro f) {
        return 0;
    }

    @Override
    public boolean colisio(ICuadro f) {
        return false;
    }

    @Override
    public void setCentreX(int centreX) {

    }

    @Override
    public void setCentreY(int centreY) {

    }

    @Override
    public int getCentreX() {
        return 0;
    }

    @Override
    public int getCentreY() {
        return 0;
    }

    @Override
    public double getIncX() {
        return 0;
    }

    @Override
    public void setIncX(double incX) {

    }

    @Override
    public double getIncY() {
        return 0;
    }

    @Override
    public void setIncY(double incY) {

    }

    @Override
    public int getAmplada() {
        return 0;
    }

    @Override
    public int getAltura() {
        return 0;
    }
}
