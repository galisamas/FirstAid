package com.itworks.firstaid.emergency;

import com.itworks.firstaid.models.EmergencyInfoListItem;

import java.util.ArrayList;
import java.util.TreeSet;

public class EmergencyInfoListItemList {

    private ArrayList<EmergencyInfoListItem> list;

    private TreeSet photosSet;
    private TreeSet buttonsSet;
    public EmergencyInfoListItemList() {
        list = new ArrayList<>();
        photosSet = new TreeSet();
        buttonsSet = new TreeSet();
    }

    public void addElement(EmergencyInfoListItem item){
        list.add(item);
    }

    public void addPhotoElement(EmergencyInfoListItem item){
        list.add(item);
        photosSet.add(list.size()-1);
    }

    public void addButtonElement(EmergencyInfoListItem item){
        list.add(item);
        buttonsSet.add(list.size()-1);
    }

    public TreeSet getPhotosSet() {
        return photosSet;
    }

    public ArrayList<EmergencyInfoListItem> getList() {
        return list;
    }

    public TreeSet getButtonsSet() {
        return buttonsSet;
    }
}
