package com.example.raulm.tetris;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

/**
 * Created by RaulM on 22/02/2018.
 */

public class FiguraO extends Figura{
    private final static int AMPLADA = 2;
    private final static int ALTURA = 2;

    public FiguraO(View view, TetrisObject tetris){
        super(view, tetris);
    }

    @Override
    protected Cuadro[][] getImatgeArray() {
        Cuadro[][] imatge = new Cuadro[ALTURA][AMPLADA];
        int color = view.getContext().getResources().getColor(R.color.figuraO);
        for(int y = 0; y < ALTURA; y++){
            for(int x = 0; x < AMPLADA; x++){
                imatge[y][x] = new Cuadro(view, color, this);
            }
        }
        return imatge;
    }
}
