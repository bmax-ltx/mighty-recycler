package com.lightricks.mightyrecycler.nested;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lightricks.mightyrecycler.R;
import com.lightricks.mightyrecycler.databinding.ActivityNestedScrollBinding;
import com.lightricks.mightyrecycler.model.ColorPalette;

import java.util.Objects;

/**
 * Activity showing the Nested Scroll screen
 */
public class NestedScrollActivity extends AppCompatActivity {
    private ActivityNestedScrollBinding dataBinding;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);

        setupViews();
    }

    private void setupViews() {
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_nested_scroll);

        setSupportActionBar(dataBinding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.recyclerView.setHasFixedSize(true);
        dataBinding.recyclerView.setNestedScrollingEnabled(false);
        dataBinding.recyclerView.setAdapter(makeAdapter());

        dataBinding.toggle.setOnCheckedChangeListener((btn, isChecked) ->
                dataBinding.recyclerView.setNestedScrollingEnabled(isChecked));
    }

    private RecyclerView.Adapter makeAdapter() {
        NestedScrollAdapter adapter = new NestedScrollAdapter();
        ColorPalette palette = new ColorPalette();
        adapter.setColors(palette.getRandomColors(50));
        return adapter;
    }
}
