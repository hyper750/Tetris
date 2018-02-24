package com.example.raulm.tetris;

import android.graphics.Canvas;
import android.view.View;

/**
 * Created by RaulM on 22/02/2018.
 */

public class FiguraI extends Figura {

    public FiguraI(View view, TetrisObject tetrisObject) {
        super(view, tetrisObject);
    }

    @Override
    protected int getAmpladaArray() {
        return 0;
    }

    @Override
    protected int getAlturaArray() {
        return 0;
    }

    @Override
    protected Cuadro[][] getImatgeArray() {
        return new Cuadro[0][];
    }
}
