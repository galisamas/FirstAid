package com.itworks.firstaid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;
import com.itworks.firstaid.controllers.TypefaceController;
import com.itworks.firstaid.emergency.EmergencyInfoFragment;
import com.itworks.firstaid.emergency.EmergencyListFragment;
import com.itworks.firstaid.emergency.FirstPageFragmentListener;
import com.itworks.firstaid.heart.HeartFragment;
import com.itworks.firstaid.hospitalmenu.HospitalInfoFragment;
import com.itworks.firstaid.hospitalmenu.HospitalListFragment;

public class MainActivity extends FragmentActivity {

    // TODO: isimti balta telefoniuka ir ideti per UIL
    CustomPagerAdapter mCustomPagerAdapter;
    ViewPager mViewPager;
    private String[] tabTitles;
    public Fragment mFragmentAtPos0, mFragmentAtPos1;
    public static FragmentManager fm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        fm = getSupportFragmentManager();
        mCustomPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager());
        TypefaceController typefaceController = new TypefaceController(getAssets());
        typefaceController.setThin((TextView) findViewById(R.id.title_text));
        typefaceController.setThin((TextView) findViewById(R.id.textView5));
        tabTitles = getResources().getStringArray(R.array.tabs);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                switch (arg0){
                    case 1:
                        if(mFragmentAtPos0 != null && mFragmentAtPos0 instanceof EmergencyInfoFragment){
                            ((EmergencyInfoFragment)mFragmentAtPos0).backPressed();
                        }
                        break;
                    case 0:
                    case 2:
                        if(mFragmentAtPos1 != null && mFragmentAtPos1 instanceof HospitalInfoFragment){
                            ((HospitalInfoFragment)mFragmentAtPos1).backPressed();
                        }
                        break;
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {}

            @Override
            public void onPageScrollStateChanged(int arg0) {}
        });
    }

    @Override
    public void onBackPressed() {
        if(mFragmentAtPos0 != null && mFragmentAtPos0 instanceof EmergencyInfoFragment){
            ((EmergencyInfoFragment)mFragmentAtPos0).backPressed();
        }
        else if(mFragmentAtPos1 != null && mFragmentAtPos1 instanceof HospitalInfoFragment){
            ((HospitalInfoFragment)mFragmentAtPos1).backPressed();
        }else{
            super.onBackPressed();
        }
    }

    class CustomPagerAdapter extends FragmentPagerAdapter {

        private final class FirstPageListener implements
                FirstPageFragmentListener {
            public void onSwitchToNextFragment(Bundle bundle) {
                mFragmentManager.beginTransaction().remove(mFragmentAtPos0)
                        .commit();
                if (mFragmentAtPos0 instanceof EmergencyListFragment){
                    mFragmentAtPos0 = new EmergencyInfoFragment(firstPageListener);
                    mFragmentAtPos0.setArguments(bundle);
                }else{
                    mFragmentAtPos0 = new EmergencyListFragment(firstPageListener);
                }
                notifyDataSetChanged();
            }
        }

        private final class SecondPageListener implements
                FirstPageFragmentListener {
            public void onSwitchToNextFragment(Bundle bundle) {
                mFragmentManager.beginTransaction().remove(mFragmentAtPos1)
                        .commit();
                if (mFragmentAtPos1 instanceof HospitalListFragment){
                    mFragmentAtPos1 = new HospitalInfoFragment(secondPageListener);
                    mFragmentAtPos1.setArguments(bundle);
                }else{
                    mFragmentAtPos1 = new HospitalListFragment(secondPageListener);
                }
                notifyDataSetChanged();
            }
        }

        private final FragmentManager mFragmentManager;
        FirstPageListener firstPageListener = new FirstPageListener();
        SecondPageListener secondPageListener = new SecondPageListener();

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
                        mFragmentAtPos0 = new EmergencyListFragment(firstPageListener);
                    }
                    return mFragmentAtPos0;
                case 1:
                    if (mFragmentAtPos1 == null)
                    {
                        mFragmentAtPos1 = new HospitalListFragment(secondPageListener);
                    }
                    return mFragmentAtPos1;
                case 2:
//                    return new PrepareListAdapterFragment();
//                case 3:
                    return new HeartFragment();
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
            if (object instanceof EmergencyListFragment &&
                    mFragmentAtPos0 instanceof EmergencyInfoFragment) {
                return POSITION_NONE;
            }
            if (object instanceof EmergencyInfoFragment &&
                    mFragmentAtPos0 instanceof EmergencyListFragment) {
                return POSITION_NONE;
            }
            if (object instanceof HospitalListFragment &&
                    mFragmentAtPos1 instanceof HospitalInfoFragment) {
                return POSITION_NONE;
            }
            if (object instanceof HospitalInfoFragment &&
                    mFragmentAtPos1 instanceof HospitalListFragment) {
                return POSITION_NONE;
            }
            return POSITION_UNCHANGED;
        }
    }
}
