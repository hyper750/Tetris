package com.example.raulm.tetris;

import android.graphics.Canvas;
import android.view.View;

/**
 * Created by RaulM on 22/02/2018.
 */

public class FiguraI extends Figura {

    private static final int AMPLADA = 4;
    private static final int ALTURA = 4;


    public FiguraI(View view, TetrisObject tetrisObject) {
        super(view, tetrisObject);
    }

    @Override
    protected Cuadro[][] getImatgeArray() {
        Cuadro[][] imatge = new Cuadro[ALTURA][AMPLADA];
        int color = view.getContext().getResources().getColor(R.color.figuraI);
        for(int y = 0; y < ALTURA; y++){
            imatge[y][0] = null;
            imatge[y][1] = new Cuadro(view, color, this);
            imatge[y][2] = null;
            imatge[y][3] = null;
        }
        return imatge;
    }
}
