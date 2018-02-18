package com.example.raulm.tetris;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.view.Gravity;
import android.view.View;

/**
 * Created by RaulM on 15/02/2018.
 */

public class FiguraZ extends Figura {

    public FiguraZ(View v){
        super(v);
    }

    @Override
    protected Drawable setDrawable() {
        /*Path p = new Path();
        p.moveTo(0f, 0f);*/


        /*ShapeDrawable drawable = new ShapeDrawable(new PathShape(p, 1, 1));
        drawable.getPaint().setColor(this.view.getResources().getColor(R.color.FiguraZ));
        drawable.getPaint().setStyle(Paint.Style.FILL);
        drawable.setIntrinsicHeight(Figura.TAMANY_QUADRAT);
        drawable.setIntrinsicWidth(Figura.TAMANY_QUADRAT);*/
        Figura s = new FiguraS(this.view);
        //Girar i canviar color
        return s.imatge;
    }

    @Override
    protected Drawable setContorn() {
        Figura s = new FiguraS(this.view);
        return s.contorn;
    }
}
