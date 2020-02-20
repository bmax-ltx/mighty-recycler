package com.lightricks.mightyrecycler.grid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.lightricks.mightyrecycler.R;
import com.lightricks.mightyrecycler.data.MaterialColors;
import com.lightricks.mightyrecycler.databinding.ActivityGridBinding;

public class GridActivity extends AppCompatActivity {

    private ActivityGridBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_grid);
        setupViews();
    }

    private void setupViews() {
        dataBinding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        dataBinding.recyclerView.setHasFixedSize(true);
        dataBinding.recyclerView.setAdapter(makeAdapter());
    }

    private RecyclerView.Adapter makeAdapter() {
        GridAdapter adapter = new GridAdapter();
        MaterialColors colors = new MaterialColors();
        adapter.setColors(colors.getRandomColors(100));
        return adapter;
    }
}
