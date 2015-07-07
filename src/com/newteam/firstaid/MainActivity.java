package com.newteam.firstaid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;
import com.newteam.firstaid.controllers.TypefaceController;
import com.newteam.firstaid.emergencymenu.EmergencyListAdapterFragment;
import com.newteam.firstaid.hospitalmenu.HospitalListAdapterFragment;
import com.newteam.firstaid.preparemenu.PrepareListAdapterFragment;

public class MainActivity extends FragmentActivity{

    CustomPagerAdapter mCustomPagerAdapter;
    ViewPager mViewPager;
    private String[] tabTitles;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mCustomPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager());
        TypefaceController typefaceController = new TypefaceController(getAssets());
        typefaceController.setThin((TextView) findViewById(R.id.title_text));
        typefaceController.setThin((TextView) findViewById(R.id.textView5));
        tabTitles = getResources().getStringArray(R.array.tabs);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);
        mViewPager.setCurrentItem(0);
    }

    class CustomPagerAdapter extends FragmentPagerAdapter {

        public CustomPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {

            switch (position)
            {
                case 0:
                    return new EmergencyListAdapterFragment();
                case 1:
                    return new HospitalListAdapterFragment();
                case 2:
                    return new PrepareListAdapterFragment();
                case 3:
                    return new PrepareListAdapterFragment(); // todo sirdies masazas
//prideti jeigu atsiranda naujas tab
            }
            return null;
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return tabTitles[0];
                case 1:
                    return tabTitles[1];
                case 2:
                    return tabTitles[2];
                case 3:
                    return tabTitles[3];
                    //prideti jeigu atsiranda naujas tab
            }
            return null;
        }
    }
}
