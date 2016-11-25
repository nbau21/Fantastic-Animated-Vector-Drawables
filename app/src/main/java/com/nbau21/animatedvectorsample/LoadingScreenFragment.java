package com.nbau21.animatedvectorsample;

import android.graphics.drawable.Animatable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class LoadingScreenFragment extends Fragment {

    int count;
    ImageView ivLoading;
    TextView tvLoading;

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
        new DummyLoadingTask().execute(10);
        ((Animatable) ivLoading.getDrawable()).start();
        return view;
    }

    class DummyLoadingTask extends AsyncTask<Integer, Integer, String> {

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
            count = 0;
            tvLoading.setText("Finished!");
            ivLoading.setImageDrawable(getResources().getDrawable(R.drawable.animated_loading_final, null));
            ((Animatable) ivLoading.getDrawable()).start();
        }

        @Override
        protected void onPreExecute() {
            tvLoading.setText("Loading..");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            tvLoading.setText("Loading.. " + count + " seconds passed!");
        }
    }


}
