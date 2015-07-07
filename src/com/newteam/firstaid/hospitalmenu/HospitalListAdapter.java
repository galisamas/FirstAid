package com.newteam.firstaid.hospitalmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.newteam.firstaid.R;

import java.util.List;

public class HospitalListAdapter extends ArrayAdapter<HospitalListItem> {

    public HospitalListAdapter(Context context, List<HospitalListItem> items) {
        super(context, R.layout.hospital_list_item, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.hospital_list_item, parent, false);

            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.item_title);
            viewHolder.tvDistance = (TextView) convertView.findViewById(R.id.item_distance);

            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // update the item view
        HospitalListItem item = getItem(position);
        viewHolder.tvTitle.setText(item.title);
        viewHolder.tvDistance.setText(item.distance);

        return convertView;
    }

    private static class ViewHolder {
        TextView tvTitle;
        TextView tvDistance;
    }
}
