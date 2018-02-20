package com.example.raulm.tetris;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.view.View;
import android.widget.Toast;

/**
 * Created by RaulM on 07/02/2018.
 */

public class FiguraI extends Figura {
    public FiguraI(View view) {
        super(view);
    }

    @Override
    protected Drawable setDrawable() {
        Path p = new Path();
        p.moveTo(0f, 0f);
        p.lineTo(1f, 0f);
        p.lineTo(1f, 1f);
        p.lineTo(0f, 1f);
        p.lineTo(0f, 0f);
        ShapeDrawable figurai = new ShapeDrawable(new PathShape(p, 1, 1));
        figurai.getPaint().setColor(this.view.getContext().getResources().getColor(R.color.figuraI));
        figurai.getPaint().setStyle(Paint.Style.FILL);
        figurai.setIntrinsicWidth(Figura.TAMANY_QUADRAT);
        figurai.setIntrinsicHeight(Figura.TAMANY_QUADRAT*4);
        return figurai;
    }

    @Override
    protected Drawable setContorn() {
        Path p = new Path();
        p.moveTo(0f, 0f);
        p.lineTo(1f, 0f);
        p.lineTo(1f, 1f);
        p.lineTo(0f, 1f);
        p.lineTo(0f, 0f);

        for(float x = 0.25f; x < 1; x+=0.25f) {
            p.moveTo(0f, (float)x);
            p.lineTo(1f, (float)x);
        }

        ShapeDrawable contorn = new ShapeDrawable(new PathShape(p, 1, 1));
        contorn.getPaint().setColor(Color.BLACK);
        contorn.getPaint().setStyle(Paint.Style.STROKE);
        contorn.setIntrinsicHeight(imatge.getIntrinsicHeight());
        contorn.setIntrinsicWidth(imatge.getIntrinsicWidth());
        return contorn;
    }
}
