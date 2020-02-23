package com.lightricks.mightyrecycler.linear;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lightricks.mightyrecycler.R;
import com.lightricks.mightyrecycler.model.MaterialColors;
import com.lightricks.mightyrecycler.databinding.ActivityLinearLayoutBinding;

public class LinearLayoutActivity extends AppCompatActivity {
    private ActivityLinearLayoutBinding dataBinding;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_linear_layout);
        setupViews();
    }

    private void setupViews() {
        dataBinding.recyclerViewTop.setLayoutManager(
                new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        dataBinding.recyclerViewTop.addItemDecoration(new DividerItemDecoration(this,
                RecyclerView.HORIZONTAL));
        dataBinding.recyclerViewTop.setHasFixedSize(true);
        dataBinding.recyclerViewTop.setAdapter(makeAdapter());

        dataBinding.recyclerViewBottom.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.recyclerViewBottom.addItemDecoration(new DividerItemDecoration(this,
                RecyclerView.VERTICAL));
        dataBinding.recyclerViewBottom.setHasFixedSize(true);
        dataBinding.recyclerViewBottom.setAdapter(makeAdapter());
    }

    private RecyclerView.Adapter makeAdapter() {
        LinearLayoutAdapter adapter = new LinearLayoutAdapter();
        MaterialColors colors = new MaterialColors();
        adapter.setColors(colors.getRandomColors(100));
        return adapter;
    }
}
