package com.itworks.firstaid.hospitalmenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.itworks.firstaid.R;
import com.itworks.firstaid.emergency.FirstPageFragmentListener;

public class HospitalInfoFragment extends Fragment {

    private TextView header, title, distance, phone, web, coordinates;
    static FirstPageFragmentListener secondPageListener;

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
        View v = inflater.inflate(R.layout.hospital_list_fragment, container, false);
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


        return v;
    }
}
