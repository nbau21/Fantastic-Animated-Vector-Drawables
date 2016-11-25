package com.nbau21.animatedvectorsample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ItemListFragment extends Fragment {

    public ItemListFragment() {
    }

    public static ItemListFragment newInstance() {
        return new ItemListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        final OverScrollLayoutManager layoutManager = new OverScrollLayoutManager(view.getContext());

        layoutManager.setOverScrollDetector(new OverScrollLayoutManager.OverScrollDetector() {

            @Override
            public void bottomOverScrollDetected(int overscroll) {

            }

            @Override
            public void topOverScrollDetected(int overscroll) {
            }

            @Override
            public void noOverScrollDetected() {
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {

                } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {

                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new ItemRecyclerViewAdapter(AnimalDirectory.ANIMALS));
        return view;
    }
}
