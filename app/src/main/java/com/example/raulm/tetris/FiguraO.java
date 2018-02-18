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
        path.lineTo((float)2, 0.0f);
        path.lineTo((float)2, (float)2);
        path.lineTo(0.0f, (float)2);
        path.lineTo(0f, 0f);
        ShapeDrawable figura_o = new ShapeDrawable(new PathShape(path, 1, 1));
        figura_o.getPaint().setColor(Color.YELLOW);
        figura_o.getPaint().setStyle(Paint.Style.FILL);
        figura_o.setIntrinsicWidth(Figura.TAMANY_QUADRAT);
        figura_o.setIntrinsicHeight(Figura.TAMANY_QUADRAT);
        return figura_o;
    }

    @Override
    protected Drawable setContorn(){
        //Es contorn
        Path pathContorn = new Path();
        //Vores
        pathContorn.moveTo(0f, 0f);
        pathContorn.lineTo((float)2, 0.0f);
        pathContorn.lineTo((float)2, (float)2);
        pathContorn.lineTo(0.0f, (float)2);
        pathContorn.lineTo(0f, 0f);
        //En mitj
        pathContorn.moveTo((float)1, 0f);
        pathContorn.lineTo((float)1, (float)2);
        pathContorn.moveTo(0.0f, (float)1);
        pathContorn.lineTo((float)2, (float)1);
        ShapeDrawable contorn = new ShapeDrawable(new PathShape(pathContorn, 1, 1));
        contorn.getPaint().setColor(Color.BLACK);
        contorn.getPaint().setStyle(Paint.Style.STROKE);
        contorn.setIntrinsicHeight(Figura.TAMANY_QUADRAT);
        contorn.setIntrinsicWidth(Figura.TAMANY_QUADRAT);
        return contorn;
    }

}
