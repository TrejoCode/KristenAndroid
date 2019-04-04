package mx.edu.upqroo.kristenandroid.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.adapters.NoticesItemAdapter;
import mx.edu.upqroo.kristenandroid.viewModels.NoticesViewModel;

public class NoticesFragment extends Fragment {

    private NoticesViewModel mViewModel;
    private NoticesItemAdapter mAdapter;

    private ProgressBar mProgressBar;
    private SwipeRefreshLayout mSwipeContainer;
    private ConstraintLayout mImageEmptyNotices;

    public static NoticesFragment newInstance() {
        return new NoticesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // View model instance
        mViewModel = ViewModelProviders.of(this).get(NoticesViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notices, container, false);
        RecyclerView mRecyclerNotices = v.findViewById(R.id.recycler_notices);
        mRecyclerNotices.setLayoutManager(new LinearLayoutManager(v.getContext()));
        mRecyclerNotices.setVisibility(View.VISIBLE);
        mAdapter = new NoticesItemAdapter(getContext());
        mRecyclerNotices.setAdapter(mAdapter);
        mProgressBar = v.findViewById(R.id.progress_notices);
        mImageEmptyNotices = v.findViewById(R.id.image_empty_notices);

        mSwipeContainer = v.findViewById(R.id.refreshLayout_notices);
        // Setup refresh listener which triggers new data loading
        mSwipeContainer.setOnRefreshListener(() -> Objects.requireNonNull(
                mViewModel.getDataSourceFactory()
                        .getLiveDataSource()
                        .getValue())
                .invalidate());
        // Configure the refreshing colors
        mSwipeContainer.setColorSchemeResources(R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorPrimaryDark,
                R.color.colorPrimaryDarker);

        mViewModel.getNotices().observe(this, notices -> {
            mAdapter.submitList(notices);
            mProgressBar.setVisibility(View.GONE);
            mSwipeContainer.setRefreshing(false);
        });
        return v;
    }

}
