package com.images.krith.mosaic.Utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by krith on 30/11/16.
 */

public class LatoRegularTextView extends TextView {
    public LatoRegularTextView(Context context) {
        super(context);
        applyCustomFont(context);
    }

    public LatoRegularTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context);
    }

    public LatoRegularTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        applyCustomFont(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LatoRegularTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface customFont = FontCache.getTypeface("Lato-Regular.ttf", context);
        setTypeface(customFont);
    }
}
