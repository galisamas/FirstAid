package com.itworks.firstaid.hospitalmenu;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import com.itworks.firstaid.R;

import java.util.ArrayList;
import java.util.List;

public class HospitalListAdapterFragment extends ListFragment {
    private List<HospitalListItem> mItems;        // ListView items list
    String[] titles, distances;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialize the items list
        mItems = new ArrayList<HospitalListItem>();

        //FIXME turi buti is kazkur nuskaitomos hospital ligonines ir juos atfiltruojamos pagal artuma iki vartotojo buvimo vietos

//        for(int i=0; i<titles.length;i++)
//            mItems.add(new HospitalListItem(titles[i], distances[i]));

        // initialize and set the list adapter
        setListAdapter(new HospitalListAdapter(getActivity(), mItems));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getListView().setDivider(getResources().getDrawable(R.color.light_red));
        getListView().setDividerHeight(2);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // retrieve theListView item
        HospitalListItem item = mItems.get(position);

//        Intent intent = new Intent(getActivity(),HospitalActivity.class);
//        intent.putExtra("id", position);
//        getActivity().startActivity(intent);

    }
}
