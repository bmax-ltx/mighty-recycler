package com.lightricks.mightyrecycler.linear;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lightricks.mightyrecycler.R;
import com.lightricks.mightyrecycler.model.ColorPalette;
import com.lightricks.mightyrecycler.databinding.ActivityLinearLayoutBinding;

/**
 * Activity showing the Linear Layout screen
 */
public class LinearLayoutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);

        setupViews();
    }

    private void setupViews() {
        ActivityLinearLayoutBinding dataBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_linear_layout);

        // Top Recycler View
        LinearLayoutManager layoutManagerTop =
                new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        dataBinding.recyclerViewTop.setLayoutManager(layoutManagerTop);
        dataBinding.recyclerViewTop.addItemDecoration(
                new DividerItemDecoration(this, layoutManagerTop.getOrientation()));

        dataBinding.recyclerViewTop.setHasFixedSize(true);
        dataBinding.recyclerViewTop.setAdapter(makeAdapter());

        // Bottom Recycler View
        LinearLayoutManager layoutManagerBottom = new LinearLayoutManager(this);
        dataBinding.recyclerViewBottom.setLayoutManager(layoutManagerBottom);
        dataBinding.recyclerViewBottom.addItemDecoration(
                new DividerItemDecoration(this, layoutManagerBottom.getOrientation()));

        dataBinding.recyclerViewBottom.setHasFixedSize(true);
        dataBinding.recyclerViewBottom.setAdapter(makeAdapter());
    }

    private RecyclerView.Adapter makeAdapter() {
        LinearLayoutAdapter adapter = new LinearLayoutAdapter();
        ColorPalette palette = new ColorPalette();
        adapter.setColors(palette.getRandomColors(100));
        return adapter;
    }
}
