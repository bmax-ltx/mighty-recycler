package com.lightricks.mightyrecycler.decoration;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.lightricks.mightyrecycler.R;
import com.lightricks.mightyrecycler.databinding.ActivityItemDecorationBinding;
import com.lightricks.mightyrecycler.model.MaterialColors;

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
        dataBinding.recyclerView.addItemDecoration(new SidelineDecoration(width, color));

        dataBinding.recyclerView.setAdapter(makeAdapter());
    }

    private ItemDecorationAdapter makeAdapter() {
        ItemDecorationAdapter adapter = new ItemDecorationAdapter();
        MaterialColors colors = new MaterialColors();
        adapter.setColors(colors.getNColors(5));
        return adapter;
    }
}
