package com.itworks.firstaid.emergency;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.itworks.firstaid.R;
import com.itworks.firstaid.models.EmergencyListItem;
import com.itworks.firstaid.repositories.JSONRepository;

import java.util.ArrayList;
import java.util.List;

public class EmergencyListFragment extends Fragment {

    static FirstPageFragmentListener firstPageListener;
    private List<EmergencyListItem> mItems;
    private ListView listview;
    private TextView header;
    SearchView search;

    public EmergencyListFragment() {
    }

    public EmergencyListFragment(FirstPageFragmentListener listener) {
        firstPageListener = listener;
    }
// TODO : searchui https://github.com/felipecsl/QuickReturn
    // https://android-arsenal.com/details/1/2552
    // https://android-arsenal.com/details/1/2542
    // https://android-arsenal.com/details/1/1723
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.emergency_list_fragment, container, false);
        listview = (ListView) v.findViewById(R.id.steps_listView);
        header = (TextView) v.findViewById(R.id.textView6);
        JSONRepository jsonRepository = new JSONRepository(getActivity());
        header.setText(getString(R.string.emergency_header));
        mItems = new ArrayList<>();
        mItems.add(new EmergencyListItem(R.drawable.forward_arrow, "rodykle")); // TODO reikia tikru duomenu
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
        search=(SearchView) v.findViewById(R.id.searchView);
        search.setQueryHint(getResources().getString(R.string.search));

        search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

            }
        });

        //*** setOnQueryTextListener ***
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                // TODO Auto-generated method stub

                Toast.makeText(getActivity().getBaseContext(), query+ " submit",
                        Toast.LENGTH_SHORT).show();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // TODO Auto-generated method stub

                	Toast.makeText(getActivity().getBaseContext(), newText + " Change", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return v;
    }
}