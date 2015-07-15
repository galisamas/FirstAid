package com.itworks.firstaid.hospitalmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.itworks.firstaid.R;
import com.itworks.firstaid.controllers.TypefaceController;
import com.itworks.firstaid.models.HospitalListItem;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class HospitalListAdapter extends ArrayAdapter<HospitalListItem> {

    private final Context context;

    public HospitalListAdapter(Context context, List<HospitalListItem> items) {
        super(context, R.layout.hospital_list_item, items);
        this.context = context;
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
            viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.item_image);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.item_title);
            viewHolder.tvDistance = (TextView) convertView.findViewById(R.id.item_description);

            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // update the item view
        HospitalListItem item = getItem(position);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage("drawable://" + R.drawable.hospital, viewHolder.ivIcon);
        viewHolder.tvTitle.setText(item.title);
        viewHolder.tvDistance.setText(item.distance);
        TypefaceController typefaceController = new TypefaceController(context.getAssets());
        typefaceController.setRoman(viewHolder.tvTitle);
        typefaceController.setRoman(viewHolder.tvDistance);
        return convertView;
    }

    private static class ViewHolder {
        TextView tvTitle;
        TextView tvDistance;
        ImageView ivIcon;
    }
}
