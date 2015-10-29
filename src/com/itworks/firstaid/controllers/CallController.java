package com.itworks.firstaid.controllers;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import com.itworks.firstaid.R;

public class CallController {
    public static void dial(View v){
        v.setOnClickListener(new View.OnClickListener() {
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
}
