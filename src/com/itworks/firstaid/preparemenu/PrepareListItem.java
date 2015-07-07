package com.itworks.firstaid.preparemenu;

import android.graphics.drawable.Drawable;

public class PrepareListItem {
    public final Drawable icon;       // the drawable for the ListView item ImageView
    public final String title;        // the text for the ListView item title

    public PrepareListItem(Drawable icon, String title) {
        this.icon = icon;
        this.title = title;
    }
}
