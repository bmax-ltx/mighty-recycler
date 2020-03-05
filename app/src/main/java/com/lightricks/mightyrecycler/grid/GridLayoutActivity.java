package com.lightricks.mightyrecycler.grid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.lightricks.mightyrecycler.R;
import com.lightricks.mightyrecycler.model.ColorPalette;
import com.lightricks.mightyrecycler.databinding.ActivityGridLayoutBinding;

public class GridLayoutActivity extends AppCompatActivity {

    private ActivityGridLayoutBinding dataBinding;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_grid_layout);
        setupViews();
    }

    private void setupViews() {
        dataBinding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        dataBinding.recyclerView.setHasFixedSize(true);
        dataBinding.recyclerView.setAdapter(makeAdapter());
        int space = getResources().getDimensionPixelSize(R.dimen.grid_spacing);
        dataBinding.recyclerView.addItemDecoration(new SpacesItemDecoration(space));
    }

    private RecyclerView.Adapter makeAdapter() {
        GridLayoutAdapter adapter = new GridLayoutAdapter();
        ColorPalette palette = new ColorPalette();
        adapter.setColors(palette.getRandomColors(100));
        return adapter;
    }
}
