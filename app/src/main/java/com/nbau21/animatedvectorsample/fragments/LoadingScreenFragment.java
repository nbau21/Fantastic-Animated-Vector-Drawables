package com.nbau21.animatedvectorsample.fragments;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbau21.animatedvectorsample.R;

import java.lang.ref.WeakReference;

public class LoadingScreenFragment extends Fragment {

    ImageView ivLoading;
    TextView tvLoading;
    DummyLoadingTask loadingTask;

    public LoadingScreenFragment() {
    }

    public static LoadingScreenFragment newInstance() {
        return new LoadingScreenFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loading_screen, container, false);
        ivLoading = (ImageView) view.findViewById(R.id.iv_loading);
        tvLoading = (TextView) view.findViewById(R.id.tv_loading);
        loadingTask = new DummyLoadingTask(view);
        loadingTask.execute(5);
        return view;
    }

    @Override
    public void onDestroy() {
        loadingTask.cancel(true);
        super.onDestroy();
    }

    static class DummyLoadingTask extends AsyncTask<Integer, Integer, String> {

        int count;
        ImageView ivLoading;
        TextView tvLoading;

        WeakReference<View> weakView;
        View referencedView;

        DummyLoadingTask(View view) {
            weakView = new WeakReference<>(view);
            referencedView = weakView.get();

            ivLoading = (ImageView) referencedView.findViewById(R.id.iv_loading);
            tvLoading = (TextView) referencedView.findViewById(R.id.tv_loading);
        }

        @Override
        protected String doInBackground(Integer... params) {
            for (; count <= params[0]; count++) {
                try {
                    Thread.sleep(1000);
                    publishProgress(count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Task Completed.";
        }

        @Override
        protected void onPostExecute(String result) {
            if (referencedView == null) {

            }

            count = 0;
            tvLoading.setText("Finished!");
            ivLoading.setImageDrawable(referencedView.getContext().getDrawable(R.drawable.animated_loading_final));
            ((Animatable) ivLoading.getDrawable()).start();
        }

        @Override
        protected void onPreExecute() {
            if (referencedView == null) {

            }

            ivLoading.setImageDrawable(referencedView.getContext().getDrawable(R.drawable.animated_loading));
            ((Animatable) ivLoading.getDrawable()).start();
            tvLoading.setText("Loading..");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            tvLoading.setText("Loading.. " + count + " seconds passed!");
        }
    }
}
