package com.example.raulm.tetris;

import android.graphics.Canvas;

/**
 * Created by RaulM on 02/03/2018.
 */

public interface ICuadro {
    public void dibuixar(Canvas canvas);
    public void incrementarPosicio(double retard);
    public double distancia(ICuadro f);
    public boolean colisio(ICuadro f);
    public void setCentreX(int centreX);
    public void setCentreY(int centreY);
    public int getCentreX();
    public int getCentreY();
    public double getIncX();
    public void setIncX(double incX);
    public double getIncY();
    public void setIncY(double incY);
    public int getAmplada();
    public int getAltura();
}
