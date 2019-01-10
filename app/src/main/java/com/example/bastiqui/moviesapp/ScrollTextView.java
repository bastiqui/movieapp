package com.example.bastiqui.moviesapp;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;

public class ScrollTextView extends android.support.v7.widget.AppCompatTextView {

    public ScrollTextView(Context context) {
        super(context);
    }

    public ScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        if (focused)
            super.onFocusChanged(true, direction, previouslyFocusedRect);
    }

    @Override
    public void onWindowFocusChanged(boolean focused) {
        if (focused)
            super.onWindowFocusChanged(true);
    }

    @Override
    public boolean isFocused() {
        return true;
    }

}