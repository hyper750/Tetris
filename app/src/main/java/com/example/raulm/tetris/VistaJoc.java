package com.example.raulm.tetris;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

/**
 * Created by RaulM on 20/01/2018.
 */

public class VistaJoc extends View {
    //Llista de figures, ja estiguin caiguent o posades
    //Quant faixin una linia s'han d'eliminar

    private TetrisObject tetris;
    private Activity activity;

    //Que cada Xms s'actualitzi
    //Reeduit perque feia un afecta com si anigues a tirons quan ses peces avançaven rapidament
    //Hi ha d'haver alguna relació segons sa velocitat maxima de ses peces i es temps d'actualització
    private final static int PERIODE_PROCES = 10;
    private final ThreadFisica fil = new ThreadFisica();
    private long darrerProces = 0;
    private float ditAnteriorX = 0, ditAnteriorY = 0;
    private boolean rotar = false;
    private boolean girarDreta = false;
    private boolean girarEsquerra = false;
    private boolean turbo = false;
    private String scoreIdioma = "";

    //Camp puntuacio
    private TextAmbFont puntuacio;

    public VistaJoc(Context context, AttributeSet attrs) {
        super(context, attrs);
        //Crear objectes de sa vista
        this.tetris = new TetrisObject(this);
        scoreIdioma = context.getResources().getString(R.string.puntuacio);
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
            setPuntuacio(tetris.getPuntuacio());
        }
    }

    @Override
    public void onSizeChanged(int width, int height, int old_width, int old_height){
        super.onSizeChanged(width, height, old_width, old_height);
        //Crear es drawable a partir des tamany de sa vista
        //Canviar tamany objectes de sa vista

        //Necesit cercar sa vista de puntuacions després de haver-la creat
        View layout = (View) getParent();
        //Frame layout pare
        if(layout != null) {
            puntuacio = layout.findViewById(R.id.puntuacio);
        }

        tetris.setalturaPantalla(height);
        tetris.setAmpladaPantalla(width);
        tetris.random();


        darrerProces = System.currentTimeMillis();
        fil.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motion){
        float x = motion.getX();
        float y = motion.getY();

        if(tetris.getAcabat()){
            return true;
        }

        switch (motion.getAction()){
            case MotionEvent.ACTION_DOWN:
                rotar = true;
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = x - ditAnteriorX;
                float dy = y - ditAnteriorY;
                float diferenciaX = Math.abs(x - ditAnteriorX);
                float diferenciaY = Math.abs(y - ditAnteriorY);
                //Si esta en turbo no fer res
                if(turbo){
                    break;
                }
                if(diferenciaX > 3) {
                    if (dx > 0) {
                        //tetris.getFiguraActual().moureDreta();
                        //Log.d("Moure", "DRETA");
                        girarDreta = true;
                        girarEsquerra = false;
                        rotar = false;
                    } else if (dx < 0) {
                        //tetris.getFiguraActual().moureEsquerra();
                        //Log.d("Moure", "ESQUERRA");
                        rotar = false;
                        girarDreta = false;
                        girarEsquerra = true;
                    }
                }
                else if(diferenciaY > 3 && dy > 0){
                    rotar = false;
                    girarDreta = false;
                    girarEsquerra = false;
                    //Desactivat perque me descoloca ses figures un poc per sa velocitat
                    //Tindria que cercar es temps de periode de procesa i sa velocitat de que du
                    tetris.activarTurbo();
                    turbo = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                if(rotar){
                    tetris.getFiguraActual().rotar();
                }
                else if(girarDreta){
                    if(tetris.pucGirarDreta(tetris.getFiguraActual())){
                        tetris.getFiguraActual().girarDreta();
                    }
                }
                else if(girarEsquerra){
                    if(tetris.pucGirarEsquerra(tetris.getFiguraActual())) {
                        tetris.getFiguraActual().girarEsquerra();
                    }
                }
                else if(turbo){
                    tetris.restaurarVelocitat();
                    turbo = false;
                }
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
        //Tarda un poc en veure ses oolisions
        synchronized (this) {
            //Moure figura, només es mou una figura a l'hora
            Figura factual = tetris.getFiguraActual();
            factual.incrementarPosicio(retard);



            //Seguir si no ha acabat

            //Mirar si colisiona amb enterra
            if (tetris.colisioEnterra(factual)) {
                factual.setIncY(0d);
            }

            //Mirar si aquesta figura té colisio amb altres figures
            if(factual.getIncY() != 0) {
                int totalFigures = tetris.getFigures().size();
                for (int x = 0; x < totalFigures; x++) {
                    if (factual.colisio(tetris.getFigures().get(x))) {
                        factual.setIncY(0d);
                        break;
                    }
                }
            }

            //Pot estar colisionant per una altre figura o quan incrementes sa posicio amb enterra
            if (factual.getIncY() == 0d && !tetris.getAcabat()) {
                //Colocar figura perque no es surti un poc de sa pantalla o estigui dins una altre figura
                tetris.colocarFiguraSenseAtravesarEnterra(tetris.getFiguraActual());
                tetris.colocarFiguresSenseAtravesar(tetris.getFiguraActual());
                tetris.liniesCompletes();
                tetris.random();
                //Si colisiona amb adalt acaba es joc
                if(tetris.tocaAdaltSaPantalla(factual)){
                    acabar();
                }
            }
        }
    }



    private void acabar(){
        tetris.setAcabat();
        fil.aturar();
        Intent i = new Intent(activity, ActivitatGuardar.class);
        i.putExtra("puntuacio", tetris.getPuntuacio());
        activity.startActivity(i);
        activity.finish();
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


    private void setPuntuacio(int puntuacio){
        this.puntuacio.setText(scoreIdioma + " " + puntuacio);
    }

    public void setActivity(Activity activity){
        this.activity = activity;
    }
}
