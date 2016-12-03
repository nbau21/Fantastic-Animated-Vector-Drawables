package com.nbau21.animatedvectorsample.fragments;

import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nbau21.animatedvectorsample.R;

public class CatFragment extends Fragment {


    public CatFragment() {
    }

    public static CatFragment newInstance() {
        return new CatFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cat, container, false);
        ImageView ivCat = (ImageView) view.findViewById(R.id.iv_cat);
        ((Animatable) ivCat.getDrawable()).start();
        return view;
    }

}
