package com.itworks.firstaid.preparemenu;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import com.itworks.firstaid.R;

import java.util.ArrayList;
import java.util.List;

public class PrepareListAdapterFragment extends ListFragment {

    private List<PrepareListItem> mItems;        // ListView items list
    String[] titles;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialize the items list
        mItems = new ArrayList<PrepareListItem>();

//        titles = getResources().getStringArray(R.array.name_prepare);
//        TypedArray images = getResources().obtainTypedArray(R.array.icon_prepare);
//
//        for(int i=0; i<titles.length;i++)
//            mItems.add(new PrepareListItem(getResources().getDrawable(images.getResourceId(i, -1)),titles[i]));

        // initialize and set the list adapter
        setListAdapter(new PrepareListAdapter(getActivity(), mItems));
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
        PrepareListItem item = mItems.get(position);

//        Intent intent = new Intent(getActivity(),PrepareActivity.class);
//        getActivity().startActivity(intent);

    }
}
