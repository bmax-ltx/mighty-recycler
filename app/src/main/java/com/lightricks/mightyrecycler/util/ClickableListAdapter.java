package com.lightricks.mightyrecycler.util;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.lightricks.mightyrecycler.R;
import com.lightricks.mightyrecycler.databinding.ItemClickableListBinding;

import java.util.Collections;
import java.util.List;

/**
 * Adapter for the Clickable list
 */
public class ClickableListAdapter extends RecyclerView.Adapter<ClickableListAdapter.ViewHolder> {
    private List<String> labels = Collections.emptyList();
    private ClickListener clickListener;

    /**
     * Set the labels to display in the list.
     */
    public void setLabels(List<String> labels) {
        this.labels = labels;

        notifyDataSetChanged();
    }

    /**
     * Set the click listener for list items.
     */
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    /** Adapter implementation */

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_clickable_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        String label = labels.get(position);
        holder.getTextView().setText(label);
        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onClick(holder.getAdapterPosition(), label);
            }
        });
    }

    @Override
    public int getItemCount() {
        return labels.size();
    }

    /**
     * Handles click events
     */
    public interface ClickListener {
        void onClick(int position, String label);
    }

    /**
     * View holder for Clickable list items
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemClickableListBinding dataBinding;

        ViewHolder(@NonNull ItemClickableListBinding dataBinding) {
            super(dataBinding.getRoot());

            this.dataBinding = dataBinding;
        }

        TextView getTextView() {
            return dataBinding.textView;
        }
    }
}
