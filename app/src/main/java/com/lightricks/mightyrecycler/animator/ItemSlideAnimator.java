package com.lightricks.mightyrecycler.animator;

import android.view.ViewPropertyAnimator;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ItemSlideAnimator extends DefaultItemAnimator {
    private List<ViewPropertyAnimator> animators = new ArrayList<>();

    @Override
    public long getAddDuration() {
        return Constants.ADD_ANIMATION_DURATION_MS;
    }

    @Override
    public long getRemoveDuration() {
        return Constants.REMOVE_ANIMATION_DURATION_MS;
    }

    @Override
    public long getChangeDuration() {
        return Constants.CHANGE_ANIMATION_DURATION_MS;
    }

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        super.animateAdd(holder);

        // Reset the opacity that the super method is changing.
        holder.itemView.setAlpha(Constants.ALPHA_OPAQUE);
        // Apply our custom enter animation.
        holder.itemView.setScaleX(Constants.SCALE_NONE);
        holder.itemView.setScaleY(Constants.SCALE_NONE);
        ViewPropertyAnimator animator = holder.itemView.animate();
        animator.scaleX(Constants.SCALE_FULL)
                .scaleY(Constants.SCALE_FULL)
                .withEndAction(() -> animators.remove(animator));

        animators.add(animator);
        return true;
    }

    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        super.animateRemove(holder);

        // Reset the opacity that the super method is changing.
        holder.itemView.setAlpha(Constants.ALPHA_OPAQUE);
        // Apply our custom exit animation.
        holder.itemView.setScaleX(Constants.SCALE_FULL);
        holder.itemView.setScaleY(Constants.SCALE_FULL);
        ViewPropertyAnimator animator = holder.itemView.animate();
        animator.scaleX(Constants.SCALE_NONE)
                .scaleY(Constants.SCALE_NONE)
                .withEndAction(() -> animators.remove(animator));

        animators.add(animator);
        return true;
    }

    @Override
    public boolean isRunning() {
        return super.isRunning() || !animators.isEmpty();
    }

    @Override
    public void endAnimation(RecyclerView.ViewHolder item) {
        super.endAnimation(item);
    }

    @Override
    public void endAnimations() {
        super.endAnimations();

        animators.forEach(ViewPropertyAnimator::cancel);
    }

    @Override
    public void onRemoveFinished(RecyclerView.ViewHolder holder) {
        super.onAddFinished(holder);

        // Restore the properties after the animation.
        holder.itemView.setScaleX(Constants.SCALE_FULL);
        holder.itemView.setScaleY(Constants.SCALE_FULL);
    }

    static class Constants {
        static final int REMOVE_ANIMATION_DURATION_MS = 120;
        static final int ADD_ANIMATION_DURATION_MS = 120;
        static final int CHANGE_ANIMATION_DURATION_MS = 250;
        static final float SCALE_FULL = 1f;
        static final float SCALE_NONE = 0f;
        static final float ALPHA_OPAQUE = 1f;
    }
}
