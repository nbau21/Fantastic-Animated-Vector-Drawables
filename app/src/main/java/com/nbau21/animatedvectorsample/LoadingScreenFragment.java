package com.nbau21.animatedvectorsample;

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

public class LoadingScreenFragment extends Fragment {

    int count;
    ImageView ivLoading;
    TextView tvLoading;
    Button btnRestart;

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
        btnRestart = (Button) view.findViewById(R.id.btn_restart);
        tvLoading = (TextView) view.findViewById(R.id.tv_loading);

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DummyLoadingTask().execute(3);
            }
        });
        new DummyLoadingTask().execute(5);
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
            btnRestart.setVisibility(View.VISIBLE);

            for (Drawable drawable : btnRestart.getCompoundDrawables()) {
                if (drawable instanceof Animatable) {
                    ((Animatable) drawable).start();
                }
            }
        }

        @Override
        protected void onPreExecute() {
            ivLoading.setImageDrawable(getResources().getDrawable(R.drawable.animated_loading, null));
            btnRestart.setVisibility(View.INVISIBLE);
            ((Animatable) ivLoading.getDrawable()).start();
            tvLoading.setText("Loading..");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            tvLoading.setText("Loading.. " + count + " seconds passed!");
        }
    }


}
