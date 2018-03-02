package com.example.raulm.tetris;

import android.view.View;

/**
 * Created by RaulM on 02/03/2018.
 */

public class FiguraFactory {
    public static final int TAMANY = 7;
    private final Figura[] figura = new Figura[TAMANY];
    private View view;
    private TetrisObject tetrisObject;
    public FiguraFactory(View view, TetrisObject tetrisObject){
        this.view = view;
        this.tetrisObject = tetrisObject;
        generate();
    }

    public void generate(){
        //Generar-les de nou per es nou tamany de pantalla
        figura[0] = new FiguraO(view, tetrisObject);
        figura[1] = new FiguraI(view, tetrisObject);
        figura[2] = new FiguraS(view, tetrisObject);
        figura[3] = new FiguraZ(view, tetrisObject);
        figura[4] = new FiguraL(view, tetrisObject);
        figura[5] = new FiguraJ(view, tetrisObject);
        figura[6] = new FiguraT(view, tetrisObject);
    }

    public Figura getFigura(int num){
        if(num >= 0 && num < figura.length){
            return figura[num].clone();
        }
        return null;
    }
}
