package com.lightricks.mightyrecycler.animator;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lightricks.mightyrecycler.R;
import com.lightricks.mightyrecycler.databinding.ActivityItemAnimatorBinding;

/**
 * Activity showing the Item Animator screen.
 */
public class ItemAnimatorActivity extends AppCompatActivity {
    private ActivityItemAnimatorBinding dataBinding;
    private ItemAnimatorViewModel viewModel;
    private ItemAnimatorAdapter adapter;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);

        setupViewModel();
        setupAdapter();
        setupViews();
    }

    private void setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(ItemAnimatorViewModel.class);
        viewModel.getMaterialColors().observe(this, colors -> adapter.setColors(colors));
    }

    private void setupAdapter() {
        adapter = new ItemAnimatorAdapter();
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                int lastPosition = adapter.getItemCount() - 1;
                dataBinding.recyclerView.scrollToPosition(lastPosition);
            }
        });
    }

    private void setupViews() {
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_item_animator);
        dataBinding.setLifecycleOwner(this);
        dataBinding.setVm(viewModel);
        dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.recyclerView.setHasFixedSize(true);
        dataBinding.recyclerView.setItemAnimator(new ScaleUpItemAnimator());
        dataBinding.recyclerView.setAdapter(adapter);
    }
}
