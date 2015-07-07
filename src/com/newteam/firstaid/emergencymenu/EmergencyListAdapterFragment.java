package com.newteam.firstaid.emergencymenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.newteam.firstaid.R;
import com.newteam.firstaid.emergencyinfo.EmergencyInfoFragment;
import com.newteam.firstaid.repositories.JSONRepository;

import java.util.ArrayList;
import java.util.List;

public class EmergencyListAdapterFragment extends Fragment {

    private List<EmergencyListItem> mItems;        // ListView items list
    String[] titles;
    private ListView listview;
    private TextView header;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.emergency_list_fragment, container, false);
        listview = (ListView) v.findViewById(R.id.steps_listView);
        header = (TextView) v.findViewById(R.id.textView6);
        header.setText(getString(R.string.emergency_header));
        mItems = new ArrayList<>();
        mItems.add(new EmergencyListItem( R.drawable.forward_arrow, "rodykle"));
        listview.setAdapter(new EmergencyListAdapter(getActivity(), mItems));
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                if (fm.findFragmentById(android.R.id.content) == null) {
                    EmergencyInfoFragment fragment = new EmergencyInfoFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", position);
                    bundle.putString("header", mItems.get(position).title);
                    fragment.setArguments(bundle);
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(android.R.id.content, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });
        return v;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JSONRepository jsonRepository = new JSONRepository(getActivity());

//        mItems = jsonRepository.

//        titles = getResources().getStringArray(R.array.name_emergency);
//        TypedArray images = getResources().obtainTypedArray(R.array.icon_emergency);
//
//        for(int i=0; i<titles.length;i++)
//            mItems.add(new EmergencyListItem(getResources().getDrawable(images.getResourceId(i, -1)),titles[i]));

        // initialize and set the list adapter

    }
}