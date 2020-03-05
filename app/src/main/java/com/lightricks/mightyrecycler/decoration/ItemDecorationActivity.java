package com.lightricks.mightyrecycler.decoration;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lightricks.mightyrecycler.R;
import com.lightricks.mightyrecycler.databinding.ActivityItemDecorationBinding;
import com.lightricks.mightyrecycler.model.ColorPalette;

public class ItemDecorationActivity extends AppCompatActivity {
    private ActivityItemDecorationBinding dataBinding;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_item_decoration);
        setupViews();
    }

    private void setupViews() {
        dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.recyclerView.setHasFixedSize(true);

        int width = getResources().getDimensionPixelSize(R.dimen.sideline_width);
        int color = ContextCompat.getColor(this, R.color.colorPrimary);
        dataBinding.recyclerView.addItemDecoration(new StepperDecoration(width, color));

        dataBinding.recyclerView.setAdapter(makeAdapter());
    }

    private RecyclerView.Adapter makeAdapter() {
        ItemDecorationAdapter adapter = new ItemDecorationAdapter();
        ColorPalette palette = new ColorPalette();
        adapter.setColors(palette.getNColors(5));
        return adapter;
    }
}
