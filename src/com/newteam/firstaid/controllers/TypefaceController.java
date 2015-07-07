package com.newteam.firstaid.controllers;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.widget.TextView;

public class TypefaceController {

    private final Typeface thin;

    public TypefaceController(AssetManager asset) {
        thin = Typeface.createFromAsset(asset, "HelveticaNeue-Thin.otf");
    }

    public void setThin(TextView view){
        view.setTypeface(thin);
    }
}
