package com.lightricks.mightyrecycler.grid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.lightricks.mightyrecycler.R;
import com.lightricks.mightyrecycler.model.ColorPalette;
import com.lightricks.mightyrecycler.databinding.ActivityGridLayoutBinding;
import com.lightricks.mightyrecycler.util.OffsetItemDecoration;

/**
 * Activity showing the Grid Layout screen
 */
public class GridLayoutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);

        setupViews();
    }

    private void setupViews() {
        ActivityGridLayoutBinding dataBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_grid_layout);

        dataBinding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        dataBinding.recyclerView.setHasFixedSize(true);
        dataBinding.recyclerView.setAdapter(makeAdapter());
        int space = getResources().getDimensionPixelSize(R.dimen.grid_spacing);
        dataBinding.recyclerView.addItemDecoration(new OffsetItemDecoration(space));
    }

    private RecyclerView.Adapter makeAdapter() {
        GridLayoutAdapter adapter = new GridLayoutAdapter();
        ColorPalette palette = new ColorPalette();
        adapter.setColors(palette.getRandomColors(100));
        return adapter;
    }
}
