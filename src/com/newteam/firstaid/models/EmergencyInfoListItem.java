package com.newteam.firstaid.models;

import android.widget.Button;

public class EmergencyInfoListItem {

    public int id;
    public int photoId;
    public String title;
    public String subtitle;
    public Button button;

    public EmergencyInfoListItem(int id, int photoId, String title, String subtitle) {
        this.id = id;
        this.photoId = photoId;
        this.title = title;
        this.subtitle = subtitle;
    }

    public EmergencyInfoListItem(int id, int photoId, String title) {
        this.id = id;
        this.photoId = photoId;
        this.title = title;
    }

    public EmergencyInfoListItem(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public EmergencyInfoListItem(int id, String title, String subtitle) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
    }

    public EmergencyInfoListItem(int id, Button button, String subtitle, String title) {
        this.id = id;
        this.button = button;
        this.subtitle = subtitle;
        this.title = title;
    }

    public EmergencyInfoListItem(int id, Button button, String title) {
        this.id = id;
        this.button = button;
        this.title = title;
    }
}