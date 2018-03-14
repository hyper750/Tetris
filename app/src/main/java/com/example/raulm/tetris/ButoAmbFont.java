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
        setTypeface(new Font(context).getFontPixelada());
    }

    public ButoAmbFont(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(new Font(context).getFontPixelada());
    }
}
