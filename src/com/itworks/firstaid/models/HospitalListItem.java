package com.itworks.firstaid.models;

public class HospitalListItem {

    public final String title;
    public final String distance;
    public final String phone;
    public final String web;
    public final double latitude;
    public final double longitude;

    public HospitalListItem(String title, String distance, String phone, String web, double latitude, double longitude) {
        this.title = title;
        this.distance = distance;
        this.phone = phone;
        this.web = web;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
