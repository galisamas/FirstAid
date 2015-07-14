package com.itworks.firstaid.controllers;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.widget.TextView;

public class TypefaceController {

    private final Typeface thin;
    private final Typeface roman;

    public TypefaceController(AssetManager asset) {
        thin = Typeface.createFromAsset(asset, "HelveticaNeue-Thin.otf");
        roman = Typeface.createFromAsset(asset, "HelveticaNeueLTStd-Roman.otf");
    }

    public void setThin(TextView view){
        view.setTypeface(thin);
    }

    public void setRoman(TextView view){
        view.setTypeface(roman);
    }
}
