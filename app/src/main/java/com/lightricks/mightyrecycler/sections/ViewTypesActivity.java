package com.lightricks.mightyrecycler.sections;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lightricks.mightyrecycler.R;
import com.lightricks.mightyrecycler.databinding.ActivityViewTypesBinding;
import com.lightricks.mightyrecycler.model.ColorPalette;

public class ViewTypesActivity extends AppCompatActivity {
    private ActivityViewTypesBinding dataBinding;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_view_types);
        setupViews();
    }

    private void setupViews() {
        dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.recyclerView.setHasFixedSize(true);
        dataBinding.recyclerView.setAdapter(makeAdapter());
    }

    private RecyclerView.Adapter makeAdapter() {
        ViewTypesAdapter adapter = new ViewTypesAdapter();
        ColorPalette palette = new ColorPalette();
        adapter.setColors(palette.getAllColors());
        return adapter;
    }
}
