package com.lightricks.mightyrecycler.types;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.lightricks.mightyrecycler.R;
import com.lightricks.mightyrecycler.databinding.ItemViewTypesFirstBinding;
import com.lightricks.mightyrecycler.databinding.ItemViewTypesSecondBinding;
import com.lightricks.mightyrecycler.model.MaterialColor;

import java.util.Collections;
import java.util.List;

/**
 * Adapter for the View Types list
 */
public class ViewTypesAdapter extends RecyclerView.Adapter<ViewTypesAdapter.ViewHolder> {
    private List<MaterialColor> colors = Collections.emptyList();

    /**
     * Set the colors to display in the list.
     */
    void setColors(List<MaterialColor> colors) {
        this.colors = colors;

        notifyDataSetChanged();
    }

    /** Adapter implementation */

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? R.layout.item_view_types_first : R.layout.item_view_types_second;
    }

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
     * View holder for View Types list items
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding dataBinding;

        ViewHolder(ViewDataBinding dataBinding) {
            super(dataBinding.getRoot());

            this.dataBinding = dataBinding;
        }

        void setColor(MaterialColor color) {
            if (dataBinding instanceof ItemViewTypesFirstBinding) {
                ((ItemViewTypesFirstBinding) dataBinding).setColor(color);
            } else if (dataBinding instanceof ItemViewTypesSecondBinding) {
                ((ItemViewTypesSecondBinding) dataBinding).setColor(color);
            }
        }
    }
}
