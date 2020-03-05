package com.lightricks.mightyrecycler.staggered;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.lightricks.mightyrecycler.R;
import com.lightricks.mightyrecycler.databinding.ActivityStaggeredGridBinding;
import com.lightricks.mightyrecycler.model.ColorPalette;

/**
 * Activity showing the Staggered Grid Layout screen
 */
public class StaggeredGridActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);

        setupViews();
    }

    private void setupViews() {
        ActivityStaggeredGridBinding dataBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_staggered_grid);

        dataBinding.recyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

        dataBinding.recyclerView.setAdapter(makeAdapter());
    }

    private RecyclerView.Adapter makeAdapter() {
        StaggeredGridAdapter adapter = new StaggeredGridAdapter();
        ColorPalette palette = new ColorPalette();
        adapter.setColors(palette.getRandomColors(150));
        return adapter;
    }
}
