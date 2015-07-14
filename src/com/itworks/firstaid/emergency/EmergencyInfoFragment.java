package com.itworks.firstaid.emergency;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.itworks.firstaid.R;
import com.itworks.firstaid.models.EmergencyInfoListItem;
import com.itworks.firstaid.repositories.JSONRepository;

import java.util.ArrayList;

public class EmergencyInfoFragment extends Fragment {

    ListView listview;
    private TextView header;
    static FirstPageFragmentListener firstPageListener;

    public EmergencyInfoFragment() {
    }

    public EmergencyInfoFragment(FirstPageFragmentListener listener) {
        firstPageListener = listener;
    }

    public void backPressed() {
        firstPageListener.onSwitchToNextFragment(null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.emergency_list_fragment, container, false);
        listview = (ListView) v.findViewById(R.id.steps_listView);
        header = (TextView) v.findViewById(R.id.textView6);
        Bundle bundle = this.getArguments();
        int id = bundle.getInt("id", -1);
//        String[] buttonIds = getResources().getStringArray(R.array.buttons_emergency);
//        int buttonId = Integer.parseInt(buttonIds[id]);
        header.setText(bundle.getString("header").toUpperCase());

        JSONRepository jsonRepository = new JSONRepository(getActivity());

//        String[] titles = jsonRepository.getEmergencyInfo

        ArrayList<EmergencyInfoListItem> list = new ArrayList<>();
        list.add(new EmergencyInfoListItem(1,"pavadinimas","aprasymas"));
        list.add(new EmergencyInfoListItem(2,R.drawable.hospital,"fotke","aprasymas2"));
        list.add(new EmergencyInfoListItem(3,"pavadinimas","aprasyma3",R.drawable.hospital_cloud));
        list.add(new EmergencyInfoListItem(4,"pavadinimas","aprasyma4"));
        list.add(new EmergencyInfoListItem(5,"pavadinimas","aprasyma4"));
        list.add(new EmergencyInfoListItem(6,"pavadinimas","aprasyma4"));
        list.add(new EmergencyInfoListItem(7,"pavadinimas","aprasyma4"));
        list.add(new EmergencyInfoListItem(8,"pavadinimas","aprasyma4"));
        list.add(new EmergencyInfoListItem(10,"pavadinimas","aprasyma4hfghg hnhg hghnhhdfdas  f asdsadas das  dasdasd asd sad as dasd asd asd asd ssad sad asds a"));
        list.add(new EmergencyInfoListItem(11,"pavadinimas","aprasyma4"));
//        for(int i=0; i<titles.length;i++){
//            if(buttonId == i)
//                list.add(new EmergencyInfoListItem(new Button(this)));
//            else
//                list.add(new EmergencyInfoListItem(String.valueOf(i+1),titles[i]));
//        }

        listview.setAdapter(new EmergencyInfoListAdapter(getActivity(), list));
        return v;
    }
}
