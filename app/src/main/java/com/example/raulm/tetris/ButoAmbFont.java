package com.example.raulm.tetris;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by RaulM on 20/01/2018.
 */

public class ButoAmbFont extends android.support.v7.widget.AppCompatButton {

    public ButoAmbFont(Context context) {
        super(context);
    }

    public ButoAmbFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public ButoAmbFont(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context context, AttributeSet attrs){
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/Pixel.ttf");
        setTypeface(font);
    }
}
