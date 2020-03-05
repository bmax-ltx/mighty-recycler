package com.lightricks.mightyrecycler.linear;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.lightricks.mightyrecycler.R;
import com.lightricks.mightyrecycler.databinding.ItemLinearLayoutBinding;
import com.lightricks.mightyrecycler.model.MaterialColor;

import java.util.Collections;
import java.util.List;

/**
 * Adapter for the Linear Layout list
 */
public class LinearLayoutAdapter extends RecyclerView.Adapter<LinearLayoutAdapter.ViewHolder> {
    private List<MaterialColor> colors = Collections.emptyList();

    /**
     * Set the colors to display in the list.
     */
    void setColors(List<MaterialColor> colors) {
        this.colors = colors;

        notifyDataSetChanged();
    }

    /** Adapter implementation */

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_linear_layout, parent, false));
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
     * View holder for the Linear Layout list items
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemLinearLayoutBinding dataBinding;

        ViewHolder(ItemLinearLayoutBinding dataBinding) {
            super(dataBinding.getRoot());

            this.dataBinding = dataBinding;
        }

        void setColor(MaterialColor color) {
            dataBinding.setColor(color);
        }
    }
}
