package com.newteam.firstaid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class StartActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        this.finish();
    }
}
