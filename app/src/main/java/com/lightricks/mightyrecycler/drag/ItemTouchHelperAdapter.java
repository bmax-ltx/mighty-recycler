package com.lightricks.mightyrecycler.drag;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.lightricks.mightyrecycler.R;
import com.lightricks.mightyrecycler.databinding.ItemDragSwipeBinding;
import com.lightricks.mightyrecycler.model.MaterialColor;

import java.util.Collections;
import java.util.List;

/**
 * Adapter for the Drag & Swipe list
 */
public class ItemTouchHelperAdapter extends RecyclerView.Adapter<ItemTouchHelperAdapter.ViewHolder> {
    private List<MaterialColor> colors = Collections.emptyList();

    /**
     * Set the colors to display in the list.
     */
    void setColors(List<MaterialColor> colors) {
        this.colors = colors;

        notifyDataSetChanged();
    }

    /**
     * An item has been moved.
     * @param fromPosition old position
     * @param toPosition new position
     * @return true if consumed
     */
    boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(colors, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(colors, i, i - 1);
            }
        }

        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    /**
     * An item has been dismissed.
     */
    void onItemDismiss(int position) {
        colors.remove(position);
        notifyItemRemoved(position);
    }

    /** Adapter implementation */

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_drag_swipe, parent, false));
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
     * View holder for Drag & Swipe list items
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemDragSwipeBinding dataBinding;

        ViewHolder(ItemDragSwipeBinding dataBinding) {
            super(dataBinding.getRoot());

            this.dataBinding = dataBinding;
        }

        void setColor(MaterialColor color) {
            dataBinding.setColor(color);
        }
    }
}
