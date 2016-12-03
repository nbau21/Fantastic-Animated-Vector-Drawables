package com.nbau21.animatedvectorsample.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nbau21.animatedvectorsample.R;

public class DummyFragment extends Fragment {

    int background;
    String dummyText;

    public DummyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dummy, container, false);
        FrameLayout dummyLayout = (FrameLayout) view.findViewById(R.id.dummy_layout);
        TextView tvDummy = (TextView) view.findViewById(R.id.tv_dummy);

        tvDummy.setText(dummyText);
        dummyLayout.setBackgroundColor(background);
        return view;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public void setDummyText(String dummyText) {
        this.dummyText = dummyText;
    }
}
