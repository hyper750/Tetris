package com.example.raulm.tetris;

import android.view.View;

/**
 * Created by RaulM on 24/02/2018.
 */

public class FiguraL extends Figura {

    private static final int ALTURA = 3;
    private static final int AMPLADA = 3;

    public FiguraL(View view, TetrisObject tetrisObject) {
        super(view, tetrisObject);
    }

    @Override
    protected ICuadro[][] getImatgeArray() {
        ICuadro[][] imatge = new ICuadro[ALTURA][AMPLADA];
        int color = view.getContext().getResources().getColor(R.color.figuraL);
        imatge[0][0] = new CuadroNull();
        imatge[0][1] = new Cuadro(view, color, this);
        imatge[0][2] = new CuadroNull();

        imatge[1][0] = new CuadroNull();
        imatge[1][1] = new Cuadro(view, color, this);
        imatge[1][2] = new CuadroNull();

        imatge[2][0] = new CuadroNull();
        imatge[2][1] = new Cuadro(view, color, this);
        imatge[2][2] = new Cuadro(view, color, this);
        return imatge;
    }
}
