package com.example.raulm.tetris;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by RaulM on 25/01/2018.
 */

public final class FiguraO extends Figura{
    public FiguraO(View view) {
        super(view);
    }
    /*Ara detectarà sa figura com un quadrat ja que només tenc s'altura i amplada que ocupa
    Pero es tamany de sa figura i es traços de com a que són puc saber es tamany per traç
    1f, 0 seria que ocupa 100% des tamany assignat de x que seria Figura.TAMANY_QUADRAT*2, dos quadrats
    de qualque manera tenc que guardar es quadrats per sa hitbox
     */

    @Override
    protected Drawable setDrawable(){
        Path path = new Path();
        //Dibuix es tipu en concret de figura
        path.moveTo(0f, 0f);
        path.lineTo(1f, 0.0f);
        path.lineTo(1f, 1f);
        path.lineTo(0f, 1f);
        path.lineTo(0f, 0f);
        ShapeDrawable figura_o = new ShapeDrawable(new PathShape(path, 1f, 1f));
        figura_o.getPaint().setColor(view.getResources().getColor(R.color.figuraO));
        figura_o.getPaint().setStyle(Paint.Style.FILL);
        figura_o.setIntrinsicWidth(Figura.TAMANY_QUADRAT*2);
        figura_o.setIntrinsicHeight(Figura.TAMANY_QUADRAT*2);
        return figura_o;
    }

    @Override
    protected Drawable setContorn(){
        //Es contorn
        Path pathContorn = new Path();
        //Vores
        pathContorn.moveTo(0f, 0f);
        pathContorn.lineTo(1f, 0.0f);
        pathContorn.lineTo(1f, 1f);
        pathContorn.lineTo(0.0f, 1f);
        pathContorn.lineTo(0f, 0f);
        //En mitj
        pathContorn.moveTo(0.5f, 0f);
        pathContorn.lineTo(0.5f, 1f);
        pathContorn.moveTo(0f, 0.5f);
        pathContorn.lineTo(1f, 0.5f);
        ShapeDrawable contorn = new ShapeDrawable(new PathShape(pathContorn, 1, 1));
        contorn.getPaint().setColor(Color.BLACK);
        contorn.getPaint().setStyle(Paint.Style.STROKE);
        contorn.setIntrinsicHeight(imatge.getIntrinsicHeight());
        contorn.setIntrinsicWidth(imatge.getIntrinsicWidth());
        return contorn;
    }

}
