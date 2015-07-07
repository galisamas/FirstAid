package com.itworks.firstaid.emergency;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.itworks.firstaid.R;
import com.itworks.firstaid.controllers.TypefaceController;
import com.itworks.firstaid.models.EmergencyListItem;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Naglis on 2015-02-22.
 */
public class EmergencyListAdapter extends ArrayAdapter<EmergencyListItem> {

    private final Context context;

    public EmergencyListAdapter(Context context, List<EmergencyListItem> items) {
        super(context, R.layout.emergency_list_item, items);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.emergency_list_item, parent, false);

            // initialize the view holder
            viewHolder = new ViewHolder();
            viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.item_image);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.item_title);

            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // update the item view
        EmergencyListItem item = getItem(position);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage("drawable://" + item.iconId, viewHolder.ivIcon);
        viewHolder.tvTitle.setText(item.title);
        TypefaceController typefaceController = new TypefaceController(context.getAssets());
        typefaceController.setThin(viewHolder.tvTitle);

        return convertView;
    }

    private static class ViewHolder {
        ImageView ivIcon;
        TextView tvTitle;
    }
}
