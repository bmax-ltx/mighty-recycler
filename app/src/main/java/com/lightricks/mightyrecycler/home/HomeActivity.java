package com.lightricks.mightyrecycler.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lightricks.mightyrecycler.R;
import com.lightricks.mightyrecycler.animator.ItemAnimatorActivity;
import com.lightricks.mightyrecycler.databinding.ActivityHomeBinding;
import com.lightricks.mightyrecycler.decoration.ItemDecorationActivity;
import com.lightricks.mightyrecycler.drag.ItemTouchHelperActivity;
import com.lightricks.mightyrecycler.grid.GridLayoutActivity;
import com.lightricks.mightyrecycler.linear.LinearLayoutActivity;
import com.lightricks.mightyrecycler.nested.NestedScrollActivity;
import com.lightricks.mightyrecycler.payload.PartialBindActivity;
import com.lightricks.mightyrecycler.types.ViewTypesActivity;
import com.lightricks.mightyrecycler.snap.SnapHelperActivity;
import com.lightricks.mightyrecycler.staggered.StaggeredGridActivity;
import com.lightricks.mightyrecycler.util.ClickableListAdapter;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * Activity showing the Home screen
 */
public class HomeActivity extends AppCompatActivity {
    private int[] labelResIds = new int[] {
            R.string.linear_layout_label,
            R.string.grid_layout_label,
            R.string.staggered_grid_label,
            R.string.snap_helper_label,
            R.string.item_touch_helper_label,
            R.string.item_decoration_label,
            R.string.view_types_label,
            R.string.item_animator_label,
            R.string.partial_bind_label,
            R.string.nested_scrolling_label,
    };

    private Class[] activityClasses = new Class[] {
            LinearLayoutActivity.class,
            GridLayoutActivity.class,
            StaggeredGridActivity.class,
            SnapHelperActivity.class,
            ItemTouchHelperActivity.class,
            ItemDecorationActivity.class,
            ViewTypesActivity.class,
            ItemAnimatorActivity.class,
            PartialBindActivity.class,
            NestedScrollActivity.class,
    };

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);

        setupViews();
    }

    private void setupViews() {
        ActivityHomeBinding dataBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_home);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        dataBinding.recyclerView.setLayoutManager(layoutManager);
        dataBinding.recyclerView.setHasFixedSize(true);
        dataBinding.recyclerView.addItemDecoration(
                new DividerItemDecoration(this, layoutManager.getOrientation()));

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
