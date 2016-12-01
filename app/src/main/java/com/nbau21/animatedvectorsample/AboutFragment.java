package com.nbau21.animatedvectorsample;

import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class AboutFragment extends Fragment {

    public AboutFragment() {
    }

    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        ImageView ivDog = (ImageView) view.findViewById(R.id.iv_dog);
        ((Animatable) ivDog.getDrawable()).start();
        return view;
    }
}
