package com.itworks.firstaid.emergency;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.itworks.firstaid.R;
import com.itworks.firstaid.models.EmergencyListItem;
import com.itworks.firstaid.repositories.JSONRepository;

import java.util.ArrayList;
import java.util.List;

public class EmergencyListAdapterFragment extends Fragment {

    static FirstPageFragmentListener firstPageListener;
    private List<EmergencyListItem> mItems;
    private ListView listview;
    private TextView header;

    public EmergencyListAdapterFragment() {
    }

    public EmergencyListAdapterFragment(FirstPageFragmentListener listener) {
        firstPageListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.emergency_list_fragment, container, false);
        listview = (ListView) v.findViewById(R.id.steps_listView);
        header = (TextView) v.findViewById(R.id.textView6);
        JSONRepository jsonRepository = new JSONRepository(getActivity());
        header.setText(getString(R.string.emergency_header));
        mItems = new ArrayList<>();
        mItems.add(new EmergencyListItem( R.drawable.forward_arrow, "rodykle")); // TODO reikia tikru duomenu
        listview.setAdapter(new EmergencyListAdapter(getActivity(), mItems));
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", position);
                bundle.putString("header", mItems.get(position).title);
                firstPageListener.onSwitchToNextFragment(bundle);
            }
        });
        return v;
    }
}