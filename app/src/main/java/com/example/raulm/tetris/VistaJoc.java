package com.example.raulm.tetris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.List;

/**
 * Created by RaulM on 20/01/2018.
 */

public class VistaJoc extends View {
    //Llista de figures, ja estiguin caiguent o posades
    //Quant faixin una linia s'han d'eliminar

    private TetrisObject tetris;

    //Que cada Xms s'actualitzi
    //Reeduit perque feia un afecta com si anigues a tirons quan ses peces avançaven rapidament
    //Hi ha d'haver alguna relació segons sa velocitat maxima de ses peces i es temps d'actualització
    private final static int PERIODE_PROCES = 5;
    private final ThreadFisica fil = new ThreadFisica();
    private long darrerProces = 0;
    private float ditAnteriorX = 0, ditAnteriorY = 0;
    private boolean rotar = false;
    private boolean girarDreta = false;
    private boolean girarEsquerra = false;
    private static double VELOCITAT_TURBO = 2d;

    public VistaJoc(Context context, AttributeSet attrs) {
        super(context, attrs);
        //Crear objectes de sa vista
        this.tetris = new TetrisObject(this);
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        //Mostrar objectes de sa vista
        synchronized (this) {
            int tamanyFigures = tetris.getFigures().size();
            for (int x = 0; x < tamanyFigures; x++) {
                tetris.getFigures().get(x).dibuixar(canvas);
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

        switch (motion.getAction()){
            case MotionEvent.ACTION_DOWN:
                rotar = true;
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = x - ditAnteriorX;
                float dy = y - ditAnteriorY;
                if(dx > 0){
                    //tetris.getFiguraActual().moureDreta();
                    //Log.d("Moure", "DRETA");
                    girarDreta = true;
                    girarEsquerra = false;
                    rotar = false;
                }
                else if(dx < 0){
                    //tetris.getFiguraActual().moureEsquerra();
                    //Log.d("Moure", "ESQUERRA");
                    rotar = false;
                    girarDreta = false;
                    girarEsquerra = true;
                }
                else if(dy > 0){
                    rotar = false;
                    girarDreta = false;
                    girarEsquerra = false;
                    tetris.setVelocitat(VELOCITAT_TURBO);
                }
                break;
            case MotionEvent.ACTION_UP:
                if(rotar){
                    tetris.getFiguraActual().rotar();
                }
                else if(girarDreta){
                    tetris.getFiguraActual().girarDreta();
                }
                else if(girarEsquerra){
                    tetris.getFiguraActual().girarEsquerra();
                }
                tetris.restaurarVelocitat();
                break;
        }
        ditAnteriorX = x;
        ditAnteriorY = y;
        return true;
    }

    private void actualitzarFisica(){
        long ara = System.currentTimeMillis();
        if(darrerProces + PERIODE_PROCES > ara){
            return;
        }
        double retard = (ara - darrerProces);
        darrerProces = ara;
        boolean colisio = false;
        synchronized (this) {
            //Moure figura, només es mou una figura a l'hora
            Figura factual = tetris.getFiguraActual();
            factual.incrementarPosicio(retard);
            int totalFigures = tetris.getFigures().size();
            for(int x = 0; x < totalFigures && !colisio; x++){
                colisio = factual.colisio(tetris.getFigures().get(x));
            }

            if(colisio){
                factual.setAturada();
            }


        }
    }

    /*Guardar estat?
    public TetrisObject getTetris(){
        return tetris;
    }

    public void setTetris(TetrisObject tetris){
        this.tetris = tetris;
    }*/

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
