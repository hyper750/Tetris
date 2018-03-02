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
    protected ICuadro[][] getImatgeArray() {
        ICuadro[][] imatge = new ICuadro[ALTURA][AMPLADA];
        int color = view.getContext().getResources().getColor(R.color.figuraI);
        for(int y = 0; y < ALTURA; y++){
            imatge[y][0] = new CuadroNull();
            imatge[y][1] = new Cuadro(view, color, this);
            imatge[y][2] = new CuadroNull();
            imatge[y][3] = new CuadroNull();
        }
        return imatge;
    }
}
