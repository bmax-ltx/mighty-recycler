package com.lightricks.mightyrecycler.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.lightricks.mightyrecycler.R;
import com.lightricks.mightyrecycler.databinding.ClickableListItemBinding;

import java.util.Collections;
import java.util.List;

public class ClickableListAdapter extends RecyclerView.Adapter<ClickableListAdapter.ViewHolder> {
    private List<String> labels = Collections.emptyList();
    private ClickListener clickListener;

    void setLabels(List<String> labels) {
        this.labels = labels;

        notifyDataSetChanged();
    }

    void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.clickable_list_item, parent, false));
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

    public interface ClickListener {
        void onClick(int position, String label);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ClickableListItemBinding dataBinding;

        ViewHolder(@NonNull ClickableListItemBinding dataBinding) {
            super(dataBinding.getRoot());

            this.dataBinding = dataBinding;
        }

        TextView getTextView() {
            return dataBinding.textView;
        }
    }
}
