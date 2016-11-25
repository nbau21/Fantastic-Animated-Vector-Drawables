package com.nbau21.animatedvectorsample;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nbau21.animatedvectorsample.dummy.DummyContent;
import com.nbau21.animatedvectorsample.dummy.DummyContent.DummyItem;

public class ItemListFragment extends Fragment {

    private OnListFragmentInteractionListener mListener;

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
        final ImageView ivSue = (ImageView) view.findViewById(R.id.iv_sue);
        ivSue.bringToFront();
        ivSue.setPivotY(ivSue.getHeight());
        ivSue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutManager.scrollToPositionWithOffset(0, 0);
            }
        });
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
                    ((Animatable) (ivSue.getDrawable())).start();
                } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    ((Animatable) (ivSue.getDrawable())).stop();
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new ItemRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(DummyItem item);
    }
}
