package com.lightricks.mightyrecycler.drag;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lightricks.mightyrecycler.R;
import com.lightricks.mightyrecycler.model.ColorPalette;
import com.lightricks.mightyrecycler.databinding.ActivityDragSwipeBinding;

public class DragAndSwipeActivity extends AppCompatActivity {
    private ActivityDragSwipeBinding dataBinding;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_drag_swipe);
        setupViews();
    }

    private void setupViews() {
        dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.recyclerView.setHasFixedSize(true);
        DragAndSwipeAdapter adapter = makeAdapter();
        dataBinding.recyclerView.setAdapter(adapter);

        final int DRAG_DIRS = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        final int SWIPE_DIRS = ItemTouchHelper.START | ItemTouchHelper.END;
        ItemTouchHelper touchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(DRAG_DIRS, SWIPE_DIRS) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                                          @NonNull RecyclerView.ViewHolder source,
                                          @NonNull RecyclerView.ViewHolder target) {
                        return source.getItemViewType() == target.getItemViewType() &&
                                adapter.onItemMove(source.getAdapterPosition(),
                                        target.getAdapterPosition());
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder,
                                         int direction) {
                        adapter.onItemDismiss(viewHolder.getAdapterPosition());
                    }
                });

        touchHelper.attachToRecyclerView(dataBinding.recyclerView);
    }

    private DragAndSwipeAdapter makeAdapter() {
        DragAndSwipeAdapter adapter = new DragAndSwipeAdapter();
        ColorPalette palette = new ColorPalette();
        adapter.setColors(palette.getRandomColors(100));
        return adapter;
    }
}
