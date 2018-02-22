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

public class _FiguraZ extends _Figura {
    public _FiguraZ(View view, TetrisObject tetris) {
        super(view, tetris);
    }

    @Override
    protected Drawable setDrawable() {
        Path p = new Path();
        float ampladaTotal = (float) 1/3;
        float alturaTotal = (float) 1/2;
        p.moveTo(0f, 0f);
        p.lineTo((float)(ampladaTotal*2), 0f);
        p.lineTo((float)(ampladaTotal*2), (float) (alturaTotal*1));
        p.lineTo((float)(ampladaTotal*3), (float) (alturaTotal*1));
        p.lineTo((float)(ampladaTotal*3), (float) (alturaTotal*2));
        p.lineTo((float)(ampladaTotal*1), (float) (alturaTotal*2));
        p.lineTo((float)(ampladaTotal*1), (float) (alturaTotal*1));
        p.lineTo(0f, (float) (alturaTotal*1));
        p.lineTo(0f, 0f);


        ShapeDrawable figuraS = new ShapeDrawable(new PathShape(p, 1, 1));
        figuraS.getPaint().setColor(this.view.getContext().getResources().getColor(R.color.figuraZ));
        figuraS.getPaint().setStyle(Paint.Style.FILL);
        figuraS.setIntrinsicWidth(_Figura.TAMANY_QUADRAT*3);
        figuraS.setIntrinsicHeight(_Figura.TAMANY_QUADRAT*2);
        return figuraS;
    }

    @Override
    protected Drawable setContorn() {
        Path p = new Path();
        float ampladaTotal = (float) 1/3;
        float alturaTotal = (float) 1/2;
        p.moveTo(0f, 0f);
        p.lineTo((float)(ampladaTotal*2), 0f);
        p.lineTo((float)(ampladaTotal*2), (float) (alturaTotal*1));
        p.lineTo((float)(ampladaTotal*3), (float) (alturaTotal*1));
        p.lineTo((float)(ampladaTotal*3), (float) (alturaTotal*2));
        p.lineTo((float)(ampladaTotal*1), (float) (alturaTotal*2));
        p.lineTo((float)(ampladaTotal*1), (float) (alturaTotal*1));
        p.lineTo(0f, (float) (alturaTotal*1));
        p.lineTo(0f, 0f);

        //Interior

        ShapeDrawable contorn = new ShapeDrawable(new PathShape(p, 1, 1));
        contorn.getPaint().setColor(Color.BLACK);
        contorn.getPaint().setStyle(Paint.Style.STROKE);
        contorn.setIntrinsicWidth(imatge.getIntrinsicWidth());
        contorn.setIntrinsicHeight(imatge.getIntrinsicHeight());
        return contorn;
    }
}
