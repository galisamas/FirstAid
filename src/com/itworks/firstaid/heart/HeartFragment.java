package com.itworks.firstaid.heart;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.itworks.firstaid.R;
import com.itworks.firstaid.controllers.TypefaceController;

public class HeartFragment extends Fragment {

    TextView main1, sub1, main2, main3, main4, main5, push, blow, sec;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.heart_fragment, container, false);
        main1 = (TextView) v.findViewById(R.id.main1);
        main2 = (TextView) v.findViewById(R.id.main2);
        main3 = (TextView) v.findViewById(R.id.main3);
        main4 = (TextView) v.findViewById(R.id.main4);
        main5 = (TextView) v.findViewById(R.id.main5);
        sub1 = (TextView) v.findViewById(R.id.sub1);
        push = (TextView) v.findViewById(R.id.push);
        blow = (TextView) v.findViewById(R.id.blow);
        sec = (TextView) v.findViewById(R.id.sec);
        setTypefaces();
        return v;
    }

    private void setTypefaces() {
        TypefaceController typefaceController = new TypefaceController(getActivity().getAssets());
        typefaceController.setRoman(main1);
        typefaceController.setRoman(main2);
        typefaceController.setRoman(main3);
        typefaceController.setRoman(main4);
        typefaceController.setRoman(main5);
        typefaceController.setRoman(sub1);
        typefaceController.setRoman(push);
        typefaceController.setRoman(blow);
        typefaceController.setRoman(sec);
    }
}