package com.lightricks.mightyrecycler.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lightricks.mightyrecycler.R;
import com.lightricks.mightyrecycler.databinding.ActivityHomeBinding;
import com.lightricks.mightyrecycler.drag.DragAndSwipeActivity;
import com.lightricks.mightyrecycler.grid.GridLayoutActivity;
import com.lightricks.mightyrecycler.linear.LinearLayoutActivity;
import com.lightricks.mightyrecycler.snap.SnapHelperActivity;
import com.lightricks.mightyrecycler.staggered.StaggeredGridActivity;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class HomeActivity extends AppCompatActivity {
    private int[] labelResIds = new int[] {
            R.string.linear_layout_label,
            R.string.grid_layout_label,
            R.string.staggered_grid_label,
            R.string.snap_helper_label,
            R.string.drag_n_swipe_label,
    };

    private Class[] activityClasses = new Class[] {
            LinearLayoutActivity.class,
            GridLayoutActivity.class,
            StaggeredGridActivity.class,
            SnapHelperActivity.class,
            DragAndSwipeActivity.class,
    };

    private ActivityHomeBinding dataBinding;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        setupViews();
    }

    private void setupViews() {
        dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataBinding.recyclerView.setHasFixedSize(true);
        dataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(this,
                RecyclerView.VERTICAL));
        int width = getResources().getDimensionPixelSize(R.dimen.sideline_width);
        int color = ContextCompat.getColor(this, R.color.colorAccent);
        dataBinding.recyclerView.addItemDecoration(new SidelineDecoration(width, color));
        dataBinding.recyclerView.setAdapter(makeAdapter());
    }

    private RecyclerView.Adapter makeAdapter() {
        ClickableListAdapter adapter = new ClickableListAdapter();
        adapter.setLabels(getLabels());
        adapter.setClickListener(((position, label) ->
                startActivityClass(activityClasses[position])));
        return adapter;
    }

    private List<String> getLabels() {
        return IntStream.of(labelResIds)
                .mapToObj(this::getString)
                .collect(toList());
    }

    private void startActivityClass(Class activityClass) {
        startActivity(new Intent(getApplicationContext(), activityClass));
    }
}
