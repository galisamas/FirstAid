package com.itworks.firstaid.repositories;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;

public class JSONRepository {

    private final AssetManager mngr;

    public JSONRepository(Context myContext) {
        mngr = myContext.getAssets();
    }

//    public List<String> getMapLegendFromJSON(){
//        List<PlaceModel> placesJson = getPlacesFromJSON();
//        List<String> legend = new ArrayList<>();
//        for(int i=0;i<placesJson.size();i++){
//            legend.add(placesJson.get(i).id + ". " + placesJson.get(i).name);
//        }
//        return legend;
//    }

    private String loadJSONFromAsset(String name) {
        String json ;
        try {

            InputStream is = mngr.open(name);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}

