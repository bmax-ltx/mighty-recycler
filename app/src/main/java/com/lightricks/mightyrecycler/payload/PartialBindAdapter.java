package com.lightricks.mightyrecycler.payload;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.lightricks.mightyrecycler.R;
import com.lightricks.mightyrecycler.databinding.ItemPartialBindBinding;
import com.lightricks.mightyrecycler.model.MaterialColor;

import java.util.Collections;
import java.util.List;

/**
 * Adapter for the Partial Bind list
 */
public class PartialBindAdapter extends RecyclerView.Adapter<PartialBindAdapter.ViewHolder> {
    private List<MaterialColor> colors = Collections.emptyList();
    private int selectedPosition = RecyclerView.NO_POSITION;
    private ClickListener clickListener;

    /**
     * Set the colors to display in the list.
     */
    void setColors(List<MaterialColor> colors) {
        this.colors = colors;

        notifyDataSetChanged();
    }

    /**
     * Select the item at the given position.
     */
    void selectItem(int position) {
        int oldPosition = selectedPosition;
        selectedPosition = position;

        if (oldPosition != position) {
            notifyItemChanged(oldPosition, new SelectionUpdate(false));
            notifyItemChanged(position, new SelectionUpdate(true));
        }
    }

    /**
     * Set the click listener.
     */
    void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    /** Adapter implementation */

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_partial_bind, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MaterialColor color = colors.get(position);
        holder.setColor(color);
        holder.setIsSelected(position == selectedPosition);
        holder.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onClick(holder.getAdapterPosition(), color);
            }
        });
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position,
                                 @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
            return;
        }

        for (Object payload : payloads) {
            if (payload instanceof SelectionUpdate) {
                holder.setIsSelected(((SelectionUpdate) payload).isSelected);
            }
        }
    }

    @Override
    public int getItemCount() {
        return colors.size();
    }

    /**
     * View holder for Partial Bind list items
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemPartialBindBinding dataBinding;

        ViewHolder(ItemPartialBindBinding dataBinding) {
            super(dataBinding.getRoot());

            this.dataBinding = dataBinding;
        }

        void setColor(MaterialColor color) {
            dataBinding.setColor(color);
        }

        void setIsSelected(boolean isSelected) {
            dataBinding.setIsSelected(isSelected);
        }

        void setOnClickListener(View.OnClickListener listener) {
            dataBinding.constrainsLayout.setOnClickListener(listener);
        }
    }

    /**
     * Payload for updating the selected state.
     */
    static class SelectionUpdate {
        final boolean isSelected;

        SelectionUpdate(boolean isSelected) {
            this.isSelected = isSelected;
        }
    }

    /**
     * Handles click events
     */
    interface ClickListener {
        void onClick(int position, MaterialColor color);
    }
}
