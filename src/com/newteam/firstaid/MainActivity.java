package com.newteam.firstaid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;
import com.newteam.firstaid.controllers.TypefaceController;
import com.newteam.firstaid.emergency.EmergencyInfoFragment;
import com.newteam.firstaid.emergency.EmergencyListAdapterFragment;
import com.newteam.firstaid.emergency.FirstPageFragmentListener;
import com.newteam.firstaid.hospitalmenu.HospitalListAdapterFragment;
import com.newteam.firstaid.preparemenu.PrepareListAdapterFragment;

public class MainActivity extends FragmentActivity {

    CustomPagerAdapter mCustomPagerAdapter;
    ViewPager mViewPager;
    private String[] tabTitles;
    public Fragment mFragmentAtPos0;

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

    @Override
    public void onBackPressed() {
        if(mFragmentAtPos0 != null && mFragmentAtPos0 instanceof EmergencyInfoFragment){
            ((EmergencyInfoFragment)mFragmentAtPos0).backPressed();
        }
        else{
            super.onBackPressed();
        }
    }

    class CustomPagerAdapter extends FragmentPagerAdapter {

        private final class FirstPageListener implements
                FirstPageFragmentListener {
            public void onSwitchToNextFragment(Bundle bundle) {
                mFragmentManager.beginTransaction().remove(mFragmentAtPos0)
                        .commit();
                if (mFragmentAtPos0 instanceof EmergencyListAdapterFragment){
                    mFragmentAtPos0 = new EmergencyInfoFragment(listener);
                    mFragmentAtPos0.setArguments(bundle);
                }else{ // Instance of NextFragment
                    mFragmentAtPos0 = new EmergencyListAdapterFragment(listener);
                }
                notifyDataSetChanged();
            }
        }

        private final FragmentManager mFragmentManager;
        FirstPageListener listener = new FirstPageListener();

        public CustomPagerAdapter(FragmentManager fm) {
            super(fm);
            mFragmentManager = fm;
        }
        @Override
        public Fragment getItem(int position) {

            switch (position)
            {
                case 0:
                    if (mFragmentAtPos0 == null)
                    {
                        mFragmentAtPos0 = new EmergencyListAdapterFragment(listener);
                    }
                    return mFragmentAtPos0;
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
            return tabTitles[position];
        }

        @Override
        public int getItemPosition(Object object)
        {
            if (object instanceof EmergencyListAdapterFragment &&
                    mFragmentAtPos0 instanceof EmergencyInfoFragment) {
                return POSITION_NONE;
            }
            if (object instanceof EmergencyInfoFragment &&
                    mFragmentAtPos0 instanceof EmergencyListAdapterFragment) {
                return POSITION_NONE;
            }
            return POSITION_UNCHANGED;
        }
    }
}
