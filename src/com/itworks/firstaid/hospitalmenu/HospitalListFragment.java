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
import android.widget.AdapterView;
import android.widget.ListView;
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
import com.itworks.firstaid.emergency.FirstPageFragmentListener;
import com.itworks.firstaid.models.HospitalListItem;
import com.itworks.firstaid.repositories.HospitalRepository;

import java.util.List;

public class HospitalListFragment extends Fragment implements android.location.LocationListener {

    private FirstPageFragmentListener secondPageListener;
    private List<HospitalListItem> mItems;
    private ListView listview;
    private TextView header;
    private HospitalRepository hospitalRepository;
    private GoogleMap googleMap;
    private Location location;
    private double latitude, longitude;

    public HospitalListFragment() {
    }

    public HospitalListFragment(FirstPageFragmentListener listener) {
        secondPageListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.hospital_list_fragment, container, false);
        listview = (ListView) v.findViewById(R.id.steps_listView);
        header = (TextView) v.findViewById(R.id.textView6);
        header.setText(getString(R.string.hospital_header));
        hospitalRepository = new HospitalRepository();
        mItems = hospitalRepository.getHospitalList();
        listview.setAdapter(new HospitalListAdapter(getActivity(), mItems));
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", position);
                bundle.putString("distance", mItems.get(position).distance);
                bundle.putString("header", mItems.get(position).title);
                bundle.putString("web", mItems.get(position).web);
                bundle.putString("phone", mItems.get(position).phone);
                bundle.putDouble("lat", mItems.get(position).latitude);
                bundle.putDouble("lng", mItems.get(position).longitude);
                secondPageListener.onSwitchToNextFragment(bundle);
            }
        });
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
            googleMap = ((SupportMapFragment) MainActivity.fm.findFragmentById(R.id.map)).getMap();

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
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(latitude, longitude)));
            googleMap.animateCamera(CameraUpdateFactory.zoomTo(8));
        }
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

    @Override
    public void onDestroyView() {
        try {
            Fragment fragment = (MainActivity.fm.findFragmentById(R.id.map));
            FragmentTransaction ft = MainActivity.fm.beginTransaction();
            ft.remove(fragment);
            ft.commit();
        }
        catch (Exception e){
            e.printStackTrace(); // TODO luzta kai iseini is programeles, nes removina map fragmenta jau kai issijungia appsas
        }finally {
            super.onDestroyView();
        }
    }
}
