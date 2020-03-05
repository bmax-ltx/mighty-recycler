package com.lightricks.mightyrecycler.util;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Item Decoration that offsets list items equally in all directions
 */
public class OffsetItemDecoration extends RecyclerView.ItemDecoration {
    private final int offset;

    public OffsetItemDecoration(int offset) {
        this.offset = offset;
    }

    @Override
    public void getItemOffsets(Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        outRect.left = offset;
        outRect.right = offset;
        outRect.bottom = offset;
        outRect.top = offset;
    }
}
