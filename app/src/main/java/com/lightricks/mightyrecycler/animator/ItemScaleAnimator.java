package com.lightricks.mightyrecycler.animator;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

public class ItemScaleAnimator extends DefaultItemAnimator {
    @Override
    public long getAddDuration() {
        return Constants.ADD_ANIMATION_DURATION_MS;
    }

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        super.animateAdd(holder);

        // Reset the opacity that the super method is changing.
        holder.itemView.setAlpha(Constants.ALPHA_OPAQUE);
        // Apply our custom enter animation.
        holder.itemView.setScaleX(Constants.SCALE_NONE);
        holder.itemView.setScaleY(Constants.SCALE_NONE);
        holder.itemView.animate()
                .scaleX(Constants.SCALE_FULL)
                .scaleY(Constants.SCALE_FULL)
                .setDuration(Constants.ADD_ANIMATION_DURATION_MS)
                .withEndAction(() -> dispatchAddFinished(holder))
                .start();

        return true;
    }

    @Override
    public void onAddFinished(RecyclerView.ViewHolder holder) {
        super.onAddFinished(holder);
        // Restore the properties after the animation.
        holder.itemView.setScaleX(Constants.SCALE_FULL);
        holder.itemView.setScaleY(Constants.SCALE_FULL);
    }

    static class Constants {
        static final int ADD_ANIMATION_DURATION_MS = 120;
        static final float SCALE_FULL = 1f;
        static final float SCALE_NONE = 0f;
        static final float ALPHA_OPAQUE = 1f;
    }
}
