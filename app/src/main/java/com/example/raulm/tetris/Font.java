package com.example.raulm.tetris;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by RaulM on 06/03/2018.
 */

public class Font {
    public Typeface getFontPixelada(Context context){
        return Typeface.createFromAsset(context.getAssets(), "fonts/Pixel.ttf");
    }
}
