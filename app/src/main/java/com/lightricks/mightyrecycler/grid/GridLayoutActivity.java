package com.lightricks.mightyrecycler.grid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.lightricks.mightyrecycler.R;
import com.lightricks.mightyrecycler.model.MaterialColors;
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
    }

    private RecyclerView.Adapter makeAdapter() {
        GridLayoutAdapter adapter = new GridLayoutAdapter();
        MaterialColors colors = new MaterialColors();
        adapter.setColors(colors.getRandomColors(100));
        return adapter;
    }
}
