package com.nbau21.animatedvectorsample.viewpagerutils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class OverScrollLayoutManager extends LinearLayoutManager {

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
