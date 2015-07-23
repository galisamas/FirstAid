package com.itworks.firstaid.hospitalmenu;

import android.app.Dialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.itworks.firstaid.MainActivity;
import com.itworks.firstaid.R;
import com.itworks.firstaid.controllers.TypefaceController;
import com.itworks.firstaid.emergency.FirstPageFragmentListener;

public class HospitalInfoFragment extends Fragment implements android.location.LocationListener{

    TextView header, title, distance, phone, web, coordinates;
    static FirstPageFragmentListener secondPageListener;
    private GoogleMap googleMap;
    private Location location;
    private double latitude, longitude;

    public HospitalInfoFragment() {
    }

    public HospitalInfoFragment(FirstPageFragmentListener listener) {
        secondPageListener = listener;
    } // TODO po skambucio, webo ir mapso griztant is info lango su native back mygtuku iseina is appso, nebeturi jis stacke tevinio fragmento

    public void backPressed() {
        secondPageListener.onSwitchToNextFragment(null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.hospital_info_fragment, container, false);
        header = (TextView) v.findViewById(R.id.header);
        title = (TextView) v.findViewById(R.id.item_title);
        distance = (TextView) v.findViewById(R.id.item_description);
        phone = (TextView) v.findViewById(R.id.tel_title);
        web = (TextView) v.findViewById(R.id.web_title);
        coordinates = (TextView) v.findViewById(R.id.maps_title);
        Bundle bundle = this.getArguments();
        int id = bundle.getInt("id", -1);
        String headerText = bundle.getString("header");
        header.setText(headerText.toUpperCase());
        title.setText(headerText);
        distance.setText(bundle.getString("distance"));
        coordinates.setText("Google Maps - GPS: " + bundle.getDouble("lat", 0.0) + ", " + bundle.getDouble("lng", 0.0));
        phone.setText(bundle.getString("phone"));
        web.setText(bundle.getString("web"));
        setTypefaces();
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try{
            initilizeMap();
            googleMapSetting();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((SupportMapFragment) MainActivity.fm.findFragmentById(R.id.mapinfo)).getMap();

            if (googleMap == null) {
                Toast.makeText(getActivity().getApplicationContext(), "Sorry! unable to create maps", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void googleMapSetting(){

        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity().getBaseContext());
        if(status!= ConnectionResult.SUCCESS){
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, getActivity(), requestCode);
            dialog.show();

        } else {
            LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 60000, 10, this);
            if (locationManager != null) {
                location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                }
                if (location == null) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 10, this);

                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }
            }
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setRotateGesturesEnabled(false);
            googleMap.getUiSettings().setTiltGesturesEnabled(false);
            googleMap.getUiSettings().setCompassEnabled(false);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(latitude,longitude)));
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(8));
        }
    }

    private void setTypefaces() {
        TypefaceController typefaceController = new TypefaceController(getActivity().getAssets());
        typefaceController.setRoman(header);
        typefaceController.setRoman(title);
        typefaceController.setRoman(distance);
        typefaceController.setRoman(phone);
        typefaceController.setRoman(web);
        typefaceController.setRoman(coordinates);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Fragment fragment = (MainActivity.fm.findFragmentById(R.id.mapinfo));
        FragmentTransaction ft = MainActivity.fm.beginTransaction();
        ft.remove(fragment);
        ft.commit();
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
