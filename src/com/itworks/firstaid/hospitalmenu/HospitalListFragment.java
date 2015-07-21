package com.itworks.firstaid.hospitalmenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.itworks.firstaid.R;
import com.itworks.firstaid.emergency.FirstPageFragmentListener;
import com.itworks.firstaid.models.HospitalListItem;
import com.itworks.firstaid.repositories.HospitalRepository;

import java.util.List;

public class HospitalListFragment extends Fragment { // todo luzta, nes du fragmentai

    private FirstPageFragmentListener secondPageListener;
    private List<HospitalListItem> mItems;
    private ListView listview;
    private TextView header;
    private HospitalRepository hospitalRepository;

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

//    @Override
//    public void onDestroyView() {
//       super.onDestroyView();
//       Fragment fragment = (getActivity().getSupportFragmentManager().findFragmentById(R.id.map));
//       FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//       ft.remove(fragment);
//       ft.commit();
//    }
}
