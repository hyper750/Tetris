package com.example.raulm.tetris;

import android.graphics.Canvas;
import android.view.View;

/**
 * Created by RaulM on 22/02/2018.
 */

public class FiguraI extends Figura {

    private static final int AMPLADA = 1;
    private static final int ALTURA = 4;


    public FiguraI(View view, TetrisObject tetrisObject) {
        super(view, tetrisObject);
    }

    @Override
    protected Cuadro[][] getImatgeArray() {
        Cuadro[][] imatge = new Cuadro[ALTURA][AMPLADA];
        int color = view.getContext().getResources().getColor(R.color.figuraI);
        for(int y = 0; y < ALTURA; y++){
            for(int x = 0; x < AMPLADA; x++){
                imatge[y][x] = new Cuadro(view, color, this);
            }
        }
        return imatge;
    }
}
