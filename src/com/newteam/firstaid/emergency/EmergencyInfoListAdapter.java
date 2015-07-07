package com.newteam.firstaid.emergency;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.newteam.firstaid.R;
import com.newteam.firstaid.models.EmergencyInfoListItem;

import java.util.List;

public class EmergencyInfoListAdapter extends ArrayAdapter<EmergencyInfoListItem> {

    private Context context;
    private int buttonId;
    public EmergencyInfoListAdapter(Context context, List<EmergencyInfoListItem> items, int buttonId) {
        super(context, R.layout.emergency_info_list_item, items);
        this.context = context;
        this.buttonId = buttonId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.emergency_info_list_item, parent, false);

            // initialize the view holder
            viewHolder = new ViewHolder();

            if (position != buttonId) {
                viewHolder.tvNumber = (TextView) convertView.findViewById(R.id.item_number);
                viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.item_title);
            } else {
                viewHolder.button = (Button) convertView.findViewById(R.id.image_button);
                LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
                layoutParams.setMargins(30,2,30,2);
                viewHolder.button.setLayoutParams(layoutParams);
                viewHolder.button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String posted_by = v.getContext().getResources().getString(R.string.sos_number);
                        String uri = "tel:" + posted_by.trim();
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse(uri));
                        v.getContext().startActivity(intent);
                    }
                });
            }
            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // update the item view
        EmergencyInfoListItem item = getItem(position);
        if (position != buttonId) {
            viewHolder.tvNumber.setText(position);
            viewHolder.tvTitle.setText(item.title);
        } else {
            viewHolder.button = item.button;
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView tvNumber;
        TextView tvTitle;
        Button button;
    }
}
