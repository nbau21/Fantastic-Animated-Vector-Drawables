package com.nbau21.animatedvectorsample;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nbau21.animatedvectorsample.AnimalDirectory.Animal;

import java.util.List;

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder> {

    private final List<Animal> mAnimals;
    private Context mContext;

    public ItemRecyclerViewAdapter(Context context, List<Animal> animals) {
        mContext = context;
        this.mAnimals = animals;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.animal = mAnimals.get(position);
        holder.tvAnimalType.setText(mAnimals.get(position).type);

        if (mAnimals.get(position).type.equals("Cat")) {
            holder.ivAnimal.setImageDrawable(mContext.getResources().getDrawable(R.drawable.animated_cat, null));
        } else {
            holder.ivAnimal.setImageDrawable(mContext.getResources().getDrawable(R.drawable.animated_sue, null));
        }

        holder.llViewHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 1.5f, 1f, 1.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                scaleAnimation.setDuration(500);
                scaleAnimation.setFillAfter(true);
                if (holder.isClicked()) {
                    scaleAnimation.setInterpolator(new ReverseInterpolator());
                    ((Animatable) holder.ivAnimal.getDrawable()).stop();
                } else {
                    ((Animatable) holder.ivAnimal.getDrawable()).start();
                }
                holder.ivAnimal.startAnimation(scaleAnimation);
                holder.updateClickedStatus();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAnimals.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final LinearLayout llViewHolder;
        public final ImageView ivAnimal;
        public final TextView tvAnimalType;
        public Animal animal;

        private boolean clicked = false;

        public ViewHolder(View view) {
            super(view);
            llViewHolder = (LinearLayout) view.findViewById(R.id.ll_viewholder);
            ivAnimal = (ImageView) view.findViewById(R.id.iv_animal);
            tvAnimalType = (TextView) view.findViewById(R.id.tv_animal_type);
        }

        public void updateClickedStatus() {
            if (clicked) {
                clicked = false;
            } else {
                clicked = true;
            }
        }

        public boolean isClicked() {
            return clicked;
        }
    }
}
