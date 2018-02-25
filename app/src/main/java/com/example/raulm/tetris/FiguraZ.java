package com.example.raulm.tetris;

import android.view.View;

/**
 * Created by RaulM on 24/02/2018.
 */

public class FiguraZ extends Figura {
    private static final int ALTURA = 3;
    private static final int AMPLADA = 3;

    public FiguraZ(View view, TetrisObject tetrisObject) {
        super(view, tetrisObject);
    }

    @Override
    protected Cuadro[][] getImatgeArray() {
        Cuadro[][] imatge = new Cuadro[ALTURA][AMPLADA];
        int color = view.getContext().getResources().getColor(R.color.figuraZ);
        imatge[0][0] = null;
        imatge[0][1] = null;
        imatge[0][2] = null;

        imatge[1][0] = new Cuadro(view, color, this);
        imatge[1][1] = new Cuadro(view, color, this);
        imatge[1][2] = null;

        imatge[2][0] = null;
        imatge[2][1] = new Cuadro(view, color, this);
        imatge[2][2] = new Cuadro(view, color, this);

        return imatge;
    }
}
