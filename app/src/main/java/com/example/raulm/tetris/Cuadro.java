package com.example.raulm.tetris;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * Created by RaulM on 22/02/2018.
 */

public class Cuadro{
    protected static int TAMANY_QUADRAT = 64;

    protected View view;
    protected Drawable imatge;
    protected Drawable contorn;
    protected int amplada, altura;
    private int centreX, centreY;
    private double incX, incY;
    private int anteriorX, anteriorY;
    //Invalidacio anterior
    private int radiInvalidacio;
    private Figura figura;

    public Cuadro(View view, int colorCuadro, Figura f){
        this.view = view;
        this.figura = f;
        //Cuadro
        Path p = new Path();
        p.moveTo(0f, 0f);
        p.lineTo(1f, 0f);
        p.lineTo(1f, 1f);
        p.lineTo(0f, 1f);
        p.lineTo(0f, 0f);
        ShapeDrawable shapeCuadro = new ShapeDrawable(new PathShape(p, 1, 1));
        shapeCuadro.getPaint().setStyle(Paint.Style.FILL);
        shapeCuadro.getPaint().setColor(colorCuadro);
        shapeCuadro.setIntrinsicWidth(TAMANY_QUADRAT);
        shapeCuadro.setIntrinsicHeight(TAMANY_QUADRAT);
        //Dibuixar Contorn
        Path pContorn = new Path();
        pContorn.moveTo(0f, 0f);
        pContorn.lineTo(1f, 0f);
        pContorn.lineTo(1f, 1f);
        pContorn.lineTo(0f, 1f);
        pContorn.lineTo(0f, 0f);
        ShapeDrawable shapeContorn = new ShapeDrawable(new PathShape(pContorn, 1, 1));
        shapeContorn.getPaint().setStyle(Paint.Style.STROKE);
        shapeContorn.getPaint().setColor(Color.BLACK);
        shapeContorn.setIntrinsicWidth(TAMANY_QUADRAT);
        shapeContorn.setIntrinsicHeight(TAMANY_QUADRAT);

        this.imatge = shapeCuadro;
        this.contorn = shapeContorn;
        this.amplada = imatge.getIntrinsicWidth();
        this.altura = imatge.getIntrinsicHeight();
        this.radiInvalidacio = (int)Math.hypot(this.amplada/2, this.altura/2);
    }

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
        //Log.d("Moguent cuadro", "Moguent a " + centreY);
        //Si fa contacte amb enterra o amb una altre figura aturar
        if(centreY+altura/2 > view.getHeight()){
            incY = 0;
            figura.setAturada();
        }
    }

    public double distancia(Cuadro f){
        return (f.centreY+f.altura/2) - centreY;
    }

    public boolean colisio(Cuadro f){
        return distancia(f) <= 10;
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
