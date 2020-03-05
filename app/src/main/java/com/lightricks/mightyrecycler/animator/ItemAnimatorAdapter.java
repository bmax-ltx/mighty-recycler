package com.lightricks.mightyrecycler.animator;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.lightricks.mightyrecycler.R;
import com.lightricks.mightyrecycler.databinding.ItemAnimatorItemBinding;
import com.lightricks.mightyrecycler.model.MaterialColor;

import java.util.ArrayList;
import java.util.List;

public class ItemAnimatorAdapter extends RecyclerView.Adapter<ItemAnimatorAdapter.ViewHolder> {
    private List<MaterialColor> colors = new ArrayList<>();

    /**
     * Set the colors to display in the list.
     */
    void setColors(List<MaterialColor> newColors) {
        DiffUtil.DiffResult diffResult =
                DiffUtil.calculateDiff(new SimpleDiffCallback<>(colors, newColors));

        colors.clear();
        colors.addAll(newColors);

        diffResult.dispatchUpdatesTo(this);
    }

    /** Adapter implementation */

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_animator_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MaterialColor color = colors.get(position);
        holder.setColor(color);
    }

    @Override
    public int getItemCount() {
        return colors.size();
    }

    /**
     * Drag & Swipe item view holder
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemAnimatorItemBinding dataBinding;

        ViewHolder(ItemAnimatorItemBinding dataBinding) {
            super(dataBinding.getRoot());

            this.dataBinding = dataBinding;
        }

        void setColor(MaterialColor color) {
            dataBinding.setColor(color);
        }
    }
}
