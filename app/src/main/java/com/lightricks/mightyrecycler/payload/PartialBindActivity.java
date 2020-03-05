package com.lightricks.mightyrecycler.payload;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lightricks.mightyrecycler.R;
import com.lightricks.mightyrecycler.databinding.ActivityPartialBindBinding;
import com.lightricks.mightyrecycler.model.ColorPalette;

public class PartialBindActivity extends AppCompatActivity {
    private ActivityPartialBindBinding dataBinding;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_partial_bind);
        setupViews();
    }

    private void setupViews() {
        dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.recyclerView.setHasFixedSize(true);
        dataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(this,
                RecyclerView.VERTICAL));

        dataBinding.recyclerView.setAdapter(makeAdapter());
    }

    private RecyclerView.Adapter makeAdapter() {
        PartialBindAdapter adapter = new PartialBindAdapter();
        ColorPalette palette = new ColorPalette();
        adapter.setColors(palette.getAllColors());
        adapter.setClickListener(((position, color) -> adapter.selectItem(position)));
        return adapter;
    }
}
