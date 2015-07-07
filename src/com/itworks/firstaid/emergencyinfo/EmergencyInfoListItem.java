package com.itworks.firstaid.emergencyinfo;

import android.widget.Button;

public class EmergencyInfoListItem {
    public String number;       // the drawable for the ListView item ImageView
    public String title;        // the text for the ListView item title
    public Button button;

    public EmergencyInfoListItem(String number, String title) {
        this.number = number;
        this.title = title;
    }

    public EmergencyInfoListItem(Button button) {
        this.button = button;
    }
}