package com.example.raulm.tetris;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by RaulM on 06/03/2018.
 */

public class Font {
    private Context context;
    public Font(Context context){
        this.context = context;
    }
    public Typeface getFontPixelada(){
        return Typeface.createFromAsset(context.getAssets(), "fonts/Pixel.ttf");
    }
}
