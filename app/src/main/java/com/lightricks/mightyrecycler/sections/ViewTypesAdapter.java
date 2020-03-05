package com.lightricks.mightyrecycler.sections;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.lightricks.mightyrecycler.R;
import com.lightricks.mightyrecycler.databinding.ViewTypesItem1Binding;
import com.lightricks.mightyrecycler.databinding.ViewTypesItem2Binding;
import com.lightricks.mightyrecycler.model.MaterialColor;

import java.util.Collections;
import java.util.List;

public class ViewTypesAdapter extends RecyclerView.Adapter<ViewTypesAdapter.ViewHolder> {
    private List<MaterialColor> colors = Collections.emptyList();

    /**
     * Set the colors to display in the list.
     */
    void setColors(List<MaterialColor> colors) {
        this.colors = colors;

        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? R.layout.view_types_item_1 : R.layout.view_types_item_2;
    }

    /** Adapter implementation */

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                viewType, parent, false));
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
        private final ViewDataBinding dataBinding;

        ViewHolder(ViewDataBinding dataBinding) {
            super(dataBinding.getRoot());

            this.dataBinding = dataBinding;
        }

        void setColor(MaterialColor color) {
            if (dataBinding instanceof ViewTypesItem1Binding) {
                ((ViewTypesItem1Binding) dataBinding).setColor(color);
            } else if (dataBinding instanceof ViewTypesItem2Binding) {
                ((ViewTypesItem2Binding) dataBinding).setColor(color);
            }
        }
    }
}
