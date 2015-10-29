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

public class EmergencyInfoFragment extends Fragment {

    ListView listview;
    private TextView header;
    static FirstPageFragmentListener firstPageListener;

    public EmergencyInfoFragment() {
    }

    public EmergencyInfoFragment(FirstPageFragmentListener listener) {
        firstPageListener = listener;
    } // TODO po skambucio 112 ir griztant is info lango su native back mygtuku iseina is appso, nebeturi jis stacke tevinio fragmento

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
        header.setText(bundle.getString("header").toUpperCase());

        EmergencyInfoListItemList list = new EmergencyInfoListItemList();
        list.addElement(new EmergencyInfoListItem(1, "pavadinimas", "aprasymas", false));
        list.addPhotoElement(new EmergencyInfoListItem(2, R.drawable.hospital, "fotke", "aprasymas2"));
        list.addButtonElement(new EmergencyInfoListItem(3, "pavadinimas", "aprasyma3", true));
        list.addElement(new EmergencyInfoListItem(4, "pavadinimas", "aprasyma4", false));
        list.addElement(new EmergencyInfoListItem(5, "pavadinimas", "aprasyma4", false));
        list.addElement(new EmergencyInfoListItem(6, "pavadinimas", "aprasyma4", false));
        list.addElement(new EmergencyInfoListItem(7, "pavadinimas", "aprasyma4", false));
        list.addElement(new EmergencyInfoListItem(8, "pavadinimas", "aprasyma4", false));
        list.addElement(new EmergencyInfoListItem(10, "pavadinimas", "aprasyma4hfghg hnhg hghnhhdfdas  f asdsadas das  dasdasd asd sad as dasd asd asd asd ssad sad asds a", false));
        list.addElement(new EmergencyInfoListItem(11, "pavadinimas", "aprasyma4", false));


        listview.setAdapter(new EmergencyInfoListAdapter(getActivity(), list.getList(),list.getPhotosSet(),list.getButtonsSet()));
        return v;
    }
}
