package com.itworks.firstaid.models;

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

    public EmergencyInfoListItem(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public EmergencyInfoListItem(int id, String title, String subtitle) {
        this.id = id;
        this.title = title;
        this.subtitle = subtitle;
    }

    public EmergencyInfoListItem(int id, String title, String subtitle, Integer button) {
        this.id = id;
        this.button = button;
        this.subtitle = subtitle;
        this.title = title;
    }

    public EmergencyInfoListItem(int id, String title, Integer button) {
        this.id = id;
        this.button = button;
        this.title = title;
    }
}