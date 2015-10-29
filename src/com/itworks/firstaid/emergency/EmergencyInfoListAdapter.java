package com.itworks.firstaid.emergency;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.itworks.firstaid.R;
import com.itworks.firstaid.controllers.CallController;
import com.itworks.firstaid.controllers.TypefaceController;
import com.itworks.firstaid.models.EmergencyInfoListItem;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;
import java.util.TreeSet;

public class EmergencyInfoListAdapter extends ArrayAdapter<EmergencyInfoListItem> {

    private static final int TYPE_MAX_COUNT = 3;
    private static final int TYPE_NONE = 0;
    private static final int TYPE_PHOTO = 1;
    private static final int TYPE_BUTTON = 2;
    private TreeSet photosSet = new TreeSet();
    private TreeSet buttonsSet = new TreeSet();
    private Context context;

    public EmergencyInfoListAdapter(Context context, List<EmergencyInfoListItem> items, TreeSet photosSet, TreeSet buttonsSet) {
        super(context, 0, items);
        this.context = context;
        this.photosSet = photosSet;
        this.buttonsSet = buttonsSet;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        int type = getItemViewType(position);
        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(context);
            viewHolder = new ViewHolder();
            switch(type){
                case TYPE_BUTTON:
                    convertView = inflater.inflate(R.layout.emergency_info_list_button_item, parent, false);
                    viewHolder.photo = (ImageView) convertView.findViewById(R.id.buttonImage);
                    viewHolder.button = (LinearLayout) convertView.findViewById(R.id.item_button);
                    CallController.dial(viewHolder.button);
                    break;
                case TYPE_PHOTO:
                    convertView = inflater.inflate(R.layout.emergency_info_list_image_item, parent, false);
                    viewHolder.photo = (ImageView) convertView.findViewById(R.id.item_image);
                    break;
                case TYPE_NONE:
                    convertView = inflater.inflate(R.layout.emergency_info_list_item, parent, false);
                    break;
            }
            // initialize the view holder

            viewHolder.tvNumber = (TextView) convertView.findViewById(R.id.item_number);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.item_title);
            viewHolder.tvSubtitle = (TextView) convertView.findViewById(R.id.item_description);
            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // update the item view
        EmergencyInfoListItem item = getItem(position);
        ImageLoader imageLoader = ImageLoader.getInstance();
        TypefaceController typefaceController = new TypefaceController(context.getAssets());
        viewHolder.tvNumber.setText(String.valueOf(item.id));
        viewHolder.tvTitle.setText(item.title);
        viewHolder.tvSubtitle.setText(item.subtitle);
        typefaceController.setRoman(viewHolder.tvNumber);
        typefaceController.setRoman(viewHolder.tvTitle);
        typefaceController.setRoman(viewHolder.tvSubtitle);
        if(item.photoId != null)
            imageLoader.displayImage("drawable://" + item.photoId, viewHolder.photo);
        else if(item.button != null)
            imageLoader.displayImage("drawable://" + item.button, viewHolder.photo);

        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return photosSet.contains(position) ? TYPE_PHOTO : (buttonsSet.contains(position) ? TYPE_BUTTON : TYPE_NONE);
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_MAX_COUNT;
    }

    private static class ViewHolder {
        TextView tvNumber;
        TextView tvTitle;
        TextView tvSubtitle;
        ImageView photo;
        LinearLayout button;
    }
}
