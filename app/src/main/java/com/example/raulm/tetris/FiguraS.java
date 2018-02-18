package com.example.raulm.tetris;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.view.View;

/**
 * Created by RaulM on 08/02/2018.
 */

public class FiguraS extends Figura {
    public FiguraS(View view) {
        super(view);
    }

    @Override
    protected Drawable setDrawable() {
        Path p = new Path();
        p.moveTo(0f, 0f);
        p.lineTo(2f, 0f);
        p.lineTo(2f, -1f);
        p.lineTo(3f, -1f);
        p.lineTo(3f, -2f);
        p.lineTo(1f, -2f);
        p.lineTo(1f, -1f);
        p.lineTo(0f, -1f);
        p.lineTo(0f, 0f);


        ShapeDrawable figuraS = new ShapeDrawable(new PathShape(p, 1, 1));
        figuraS.getPaint().setColor(this.view.getContext().getResources().getColor(R.color.FiguraS));
        figuraS.getPaint().setStyle(Paint.Style.FILL);
        figuraS.setIntrinsicWidth(Figura.TAMANY_QUADRAT);
        figuraS.setIntrinsicHeight(Figura.TAMANY_QUADRAT);
        return figuraS;
    }

    @Override
    protected Drawable setContorn() {
        Path p = new Path();
        p.moveTo(0f, 0f);
        p.moveTo(0f, 0f);
        p.lineTo(2f, 0f);
        p.lineTo(2f, -1f);
        p.lineTo(3f, -1f);
        p.lineTo(3f, -2f);
        p.lineTo(1f, -2f);
        p.lineTo(1f, -1f);
        p.lineTo(0f, -1f);
        p.lineTo(0f, 0f);

        //Interior
        p.moveTo(1f, 0f);
        p.lineTo(1f, -1f);
        p.lineTo(2f, -1f);
        p.lineTo(2f, -2f);

        ShapeDrawable contorn = new ShapeDrawable(new PathShape(p, 1, 1));
        contorn.getPaint().setColor(Color.BLACK);
        contorn.getPaint().setStyle(Paint.Style.STROKE);
        contorn.setIntrinsicWidth(Figura.TAMANY_QUADRAT);
        contorn.setIntrinsicHeight(Figura.TAMANY_QUADRAT);
        return contorn;
    }
}
