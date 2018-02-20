package com.example.raulm.tetris;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.constraint.solver.widgets.Rectangle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by RaulM on 25/01/2018.
 */

public abstract class Figura implements Cloneable{
    protected static int TAMANY_QUADRAT = 64;

    protected View view;
    protected Drawable imatge;
    protected Drawable contorn;
    protected int amplada, altura;
    private int centreX, centreY;
    private double incX, incY;
    private int anteriorX, anteriorY;
    private double radiColisio;
    //Invalidacio anterior
    private int radiInvalidacio;

    public Figura(View view){
        this.view = view;
        this.imatge = setDrawable();
        this.contorn = setContorn();
        this.amplada = imatge.getIntrinsicWidth();
        this.altura = imatge.getIntrinsicHeight();
        this.radiInvalidacio = (int)Math.hypot(this.amplada/2, this.altura/2);
        this.radiColisio = (altura+amplada)/4;
    }

    @Override
    public Figura clone() throws CloneNotSupportedException{
        try {
            return (Figura) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    //A ses noves figures me tenen que dir com dibuixar-les
    protected abstract Drawable setDrawable();
    protected abstract Drawable setContorn();

    public void dibuixar(Canvas canvas){
        int x = centreX-amplada/2;
        int y = centreY-altura/2;
        this.imatge.setBounds(x, y, x+amplada, y+altura);
        this.imatge.draw(canvas);
        this.contorn.setBounds(x, y, x+amplada, y+altura);
        this.contorn.draw(canvas);
        //Actualitzar quadrant per dibuixar s'imatge actual
        this.view.invalidate(centreX-radiInvalidacio, centreY-radiInvalidacio, centreX+radiInvalidacio, centreY+radiInvalidacio);
        //Actualitz es quadrant anterior per llevar sa figura que s'ha mogut i no està en aquella posició
        this.view.invalidate(anteriorX-radiInvalidacio, anteriorY-radiInvalidacio, anteriorX+radiInvalidacio, anteriorY+radiInvalidacio);
        //view.invalidate();
        anteriorX = centreX;
        anteriorY = centreY;
    }

    public void incrementarPosicio(double retard){
        //centreX += incX * retard;
        centreY += incY * retard;

        //Si fa contacte amb enterra o amb una altre figura aturar
        if(centreY+(altura/2) > view.getHeight()){
            incY = 0;
        }
    }

    public double distancia(Figura f){
        return Math.hypot(centreX - f.centreX, centreY - f.centreY);
    }

    public boolean colisio(Figura f){
        return distancia(f) < (radiInvalidacio + f.radiInvalidacio);
    }

    public void setCentreX(int centreX) {
        this.centreX = centreX;
    }

    public void setCentreY(int centreY) {
        this.centreY = centreY;
    }

    public int getCentreX() {
        return centreX;
    }

    public int getCentreY() {
        return centreY;
    }

    public double getIncX() {
        return incX;
    }

    public void setIncX(double incX) {
        this.incX = incX;
    }

    public double getIncY() {
        return incY;
    }

    public void setIncY(double incY) {
        this.incY = incY;
    }

    public int getAmplada() {
        return amplada;
    }

    public int getAltura() {
        return altura;
    }
}
