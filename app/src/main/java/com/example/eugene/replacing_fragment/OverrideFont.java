package com.example.eugene.replacing_fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Eugene on 6/3/2016.
 */
public class OverrideFont {
    public void overrideFont(final Context context, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFont(context, child);
                }
            } else if (v instanceof TextView) {

                Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/font3.otf");
                ((TextView) v).setTypeface(font);
            }
        } catch (Exception e) {
        }
    }
}
