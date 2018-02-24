package com.example.raulm.tetris;

import android.view.View;

/**
 * Created by RaulM on 24/02/2018.
 */

public class FiguraT extends Figura {

    private static final int ALTURA = 2;
    private static final int AMPLADA = 3;

    public FiguraT(View view, TetrisObject tetrisObject) {
        super(view, tetrisObject);
    }

    @Override
    protected Cuadro[][] getImatgeArray() {
        Cuadro[][] imatge = new Cuadro[ALTURA][AMPLADA];
        int color = view.getContext().getResources().getColor(R.color.figuraT);
        imatge[0][0] = new Cuadro(view, color, this);
        imatge[0][1] = new Cuadro(view, color, this);
        imatge[0][2] = new Cuadro(view, color, this);

        imatge[1][0] = null;
        imatge[1][1] = new Cuadro(view, color, this);
        imatge[1][2] = null;

        return imatge;
    }
}
