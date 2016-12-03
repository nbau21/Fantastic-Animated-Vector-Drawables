package com.nbau21.animatedvectorsample.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nbau21.animatedvectorsample.R;
import com.nbau21.animatedvectorsample.models.AnimalDirectory;
import com.nbau21.animatedvectorsample.viewpagerutils.ItemRecyclerViewAdapter;

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

    public static class OverScrollLayoutManager extends LinearLayoutManager {

        private OverScrollDetector mOverScrollDetector;
        private boolean mBottomOverScrollDetected = false;
        private boolean mTopOverScrollDetected = false;

        public OverScrollLayoutManager(Context context) {
            super(context);
        }

        public OverScrollLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        public OverScrollLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }

        interface OverScrollDetector {
            void bottomOverScrollDetected(int overscroll);

            void topOverScrollDetected(int overscroll);

            void noOverScrollDetected();
        }

        @Override
        public int scrollVerticallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
            int scrollRange = super.scrollVerticallyBy(dx, recycler, state);
            int overscroll = dx - scrollRange;
            if (overscroll > 0) {
                setDetectedBottomOverScroll(overscroll);
            } else if (overscroll < 0) {
                setDetectedTopOverScroll(overscroll);
            } else {
                setDetectedNoOverScroll();
            }
            return scrollRange;
        }

        private void setDetectedNoOverScroll() {
            mBottomOverScrollDetected = false;
            mTopOverScrollDetected = false;

            mOverScrollDetector.noOverScrollDetected();
        }

        private void setDetectedTopOverScroll(int overscroll) {
            if (!mTopOverScrollDetected) {
                mOverScrollDetector.topOverScrollDetected(overscroll < -50 ? -50 : overscroll);
                mTopOverScrollDetected = true;
            }
        }

        private void setDetectedBottomOverScroll(int overscroll) {
            if (!mBottomOverScrollDetected) {
                mOverScrollDetector.bottomOverScrollDetected(overscroll > 50 ? 50 : overscroll);
                mBottomOverScrollDetected = true;
            }
        }

        public void setOverScrollDetector(OverScrollDetector overScrollDetector) {
            this.mOverScrollDetector = overScrollDetector;
        }
    }
}
