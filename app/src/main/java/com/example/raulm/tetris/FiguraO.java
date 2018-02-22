package com.example.raulm.tetris;

import android.graphics.Canvas;
import android.util.Log;
import android.view.View;

/**
 * Created by RaulM on 22/02/2018.
 */

public class FiguraO extends Figura{
    private final static int AMPLADA = 2;
    private final static int ALTURA = 2;
    private Cuadro[][] imatge;
    private View view;

    public FiguraO(View view, TetrisObject tetris){
        super(tetris);
        this.view = view;
        imatge = new Cuadro[ALTURA][AMPLADA];
        int color = view.getContext().getResources().getColor(R.color.figuraO);
        for(int y = 0; y < ALTURA; y++){
            for(int x = 0; x < AMPLADA; x++){
                imatge[y][x] = new Cuadro(view, color, this);
            }
        }
    }


    @Override
    protected int getAmpladaArray() {
        return AMPLADA;
    }

    @Override
    protected int getAlturaArray() {
        return ALTURA;
    }

    @Override
    protected Cuadro[][] getImatgeArray() {
        return this.imatge;
    }
}
