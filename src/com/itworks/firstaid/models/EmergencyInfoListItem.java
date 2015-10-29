package com.itworks.firstaid.models;

import com.itworks.firstaid.R;

public class EmergencyInfoListItem {

    public int id;
    public Integer photoId;
    public String title;
    public String subtitle;
    public Integer button;

    public EmergencyInfoListItem(int id, Integer photoId, String title, String subtitle) {
        this.id = id;
        this.photoId = photoId;
        this.title = title;
        this.subtitle = subtitle;
    }

    public EmergencyInfoListItem(int id, Integer photoId, String title) {
        this.id = id;
        this.photoId = photoId;
        this.title = title;
    }

    public EmergencyInfoListItem(int id, String title, String subtitle, Boolean button) {
        this.id = id;
        if(button)
            this.button = R.drawable.phone_blue;
        this.subtitle = subtitle;
        this.title = title;
    }

    public EmergencyInfoListItem(int id, String title, Boolean button) {
        this.id = id;
        if(button)
            this.button = R.drawable.phone_blue;
        this.title = title;
    }
}