package com.lightricks.mightyrecycler.snap;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.lightricks.mightyrecycler.R;
import com.lightricks.mightyrecycler.databinding.ActivitySnapHelperBinding;
import com.lightricks.mightyrecycler.model.ColorPalette;

/**
 * Activity showing the Snap Helper screen
 */
public class SnapHelperActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);

        setupViews();
    }

    private void setupViews() {
        ActivitySnapHelperBinding dataBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_snap_helper);

        // Top Recycler View
        LinearLayoutManager layoutManagerTop =
                new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        dataBinding.recyclerViewTop.setLayoutManager(layoutManagerTop);
        dataBinding.recyclerViewTop.addItemDecoration(
                new DividerItemDecoration(this, layoutManagerTop.getOrientation()));

        dataBinding.recyclerViewTop.setHasFixedSize(true);
        dataBinding.recyclerViewTop.setAdapter(makeAdapter());

        SnapHelper linearSnapHelper = new LinearSnapHelper();
        linearSnapHelper.attachToRecyclerView(dataBinding.recyclerViewTop);

        // Bottom Recycler View
        LinearLayoutManager layoutManagerBottom =
                new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        dataBinding.recyclerViewBottom.setLayoutManager(layoutManagerBottom);
        dataBinding.recyclerViewBottom.addItemDecoration(
                new DividerItemDecoration(this, layoutManagerBottom.getOrientation()));

        dataBinding.recyclerViewBottom.setHasFixedSize(true);
        dataBinding.recyclerViewBottom.setAdapter(makeAdapter());

        SnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(dataBinding.recyclerViewBottom);
    }

    private RecyclerView.Adapter makeAdapter() {
        SnapHelperAdapter adapter = new SnapHelperAdapter();
        ColorPalette palette = new ColorPalette();
        adapter.setColors(palette.getRandomColors(100));
        return adapter;
    }

}
