package com.itworks.firstaid.hospitalmenu;

public class HospitalListItem {
    public final String title;       // the drawable for the ListView item ImageView
    public final String distance;        // the text for the ListView item title

    public HospitalListItem(String title, String distance) {
        this.title = title;
        this.distance = distance;
    }
}
