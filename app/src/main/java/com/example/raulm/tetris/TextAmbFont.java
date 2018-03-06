package com.example.raulm.tetris;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by RaulM on 06/03/2018.
 */

public class TextAmbFont extends android.support.v7.widget.AppCompatTextView {

    public TextAmbFont(Context context) {
        super(context);
    }

    public TextAmbFont(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface(new Font().getFontPixelada(context));
    }

    public TextAmbFont(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(new Font().getFontPixelada(context));
    }
}
