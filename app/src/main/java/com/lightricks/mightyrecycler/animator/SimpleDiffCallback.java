package com.lightricks.mightyrecycler.animator;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;
import java.util.Objects;

public class SimpleDiffCallback<T extends Identifiable> extends DiffUtil.Callback {
    private List<T> oldList;
    private List<T> newList;

    SimpleDiffCallback(List<T> oldList, List<T> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        T oldItem = oldList.get(oldItemPosition);
        T newItem = newList.get(newItemPosition);
        return Objects.equals(oldItem.getId(), newItem.getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        T oldItem = oldList.get(oldItemPosition);
        T newItem = newList.get(newItemPosition);
        return Objects.equals(oldItem, newItem);
    }
}
