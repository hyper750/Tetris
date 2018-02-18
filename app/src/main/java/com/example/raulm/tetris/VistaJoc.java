package com.example.raulm.tetris;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RaulM on 20/01/2018.
 */

public class VistaJoc extends View {
    //Llista de figures, ja estiguin caiguent o posades
    //Quant faixin una linia s'han d'eliminar

    private final TetrisObject tetris = new TetrisObject(this);

    //Que cada 50ms s'actualitzi
    private final static int PERIODE_PROCES = 50;
    private final ThreadFisica fil = new ThreadFisica();
    private long darrerProces = 0;

    public VistaJoc(Context context, AttributeSet attrs) {
        super(context, attrs);
        //Crear objectes de sa vista
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        //Mostrar objectes de sa vista
        synchronized (this) {
            int tamanyFigures = tetris.getFigures().size();
            List<Figura> llista = tetris.getFigures();
            for (int x = 0; x < tamanyFigures; x++) {
                llista.get(x).dibuixar(canvas);
            }

            tetris.getFiguraActual().dibuixar(canvas);
        }
    }

    @Override
    public void onSizeChanged(int width, int height, int old_width, int old_height){
        super.onSizeChanged(width, height, old_width, old_height);
        //Crear es drawable a partir des tamany de sa vista
        //Canviar tamany objectes de sa vista
        synchronized (this){
            tetris.setalturaPantalla(height);
            tetris.setAmpladaPantalla(width);
            tetris.random();
        }
        darrerProces = System.currentTimeMillis();
        fil.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motion){
        float x = motion.getX();
        float y = motion.getY();

        return true;
    }

    private void actualitzarFisica(){
        long ara = System.currentTimeMillis();
        if(darrerProces + PERIODE_PROCES > ara){
            return;
        }
        double retard = (ara - darrerProces);
        darrerProces = ara;
        synchronized (this) {
            int total = tetris.getFigures().size();
            //Moure figura, només es mou una figura a l'hora
            tetris.getFiguraActual().incrementarPosicio(retard);

            //Mirar colisio
            for(int x = 0; x < total; x++){
                Figura seguent = tetris.getFigures().get(x);
                if(tetris.getFiguraActual().colisio(seguent)){
                    tetris.getFiguraActual().setIncY(0d);
                    tetris.random();
                }
            }

        }
    }

    public ThreadFisica getFil(){
        return this.fil;
    }

    protected class ThreadFisica extends Thread{
        private boolean seguir = true;
        private boolean pause = false;

        public synchronized void aturar(){
            seguir = false;
            if(pause){
                reanudar();
            }
        }

        public synchronized void pausar(){
            pause = true;
        }

        public synchronized void reanudar(){
            pause = false;
            notify();
        }

        @Override
        public void run(){
            while (seguir){
                actualitzarFisica();
                synchronized (this){
                    while(pause){
                        try {
                            wait();
                        } catch (InterruptedException e) {

                        }
                    }
                }
            }
        }
    }
}
