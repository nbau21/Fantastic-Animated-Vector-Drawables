package com.nbau21.animatedvectorsample.viewpagerutils;

import android.graphics.drawable.Animatable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbau21.animatedvectorsample.R;
import com.nbau21.animatedvectorsample.models.AnimalDirectory;

import java.util.List;

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder> {

    private final List<AnimalDirectory.Animal> mAnimals;

    public ItemRecyclerViewAdapter(List<AnimalDirectory.Animal> animals) {
        mAnimals = animals;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mIdView.setText(mAnimals.get(position).id);
        holder.mType.setText(mAnimals.get(position).type);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.updateLabel();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAnimals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mLabel;
        public final TextView mIdView;
        public final TextView mType;
        private boolean isClicked = false;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mLabel = (ImageView) view.findViewById(R.id.iv_label);
            mType = (TextView) view.findViewById(R.id.tv_animal_type);
        }

        public void updateLabel() {
            if (!isClicked) {
                mLabel.setImageDrawable(mView.getContext().getResources().getDrawable(R.drawable.animated_label, null));
                ((Animatable) mLabel.getDrawable()).start();
                isClicked = true;
            } else {
                mLabel.setImageDrawable(mView.getContext().getResources().getDrawable(R.drawable.animated_label_reverse, null));
                ((Animatable) mLabel.getDrawable()).start();
                isClicked = false;
            }
        }
    }
}
