package mx.edu.upqroo.kristenandroid.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.dkzwm.widget.srl.SmoothRefreshLayout;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.adapters.NoticesItemAdapter;
import mx.edu.upqroo.kristenandroid.viewModels.NoticesViewModel;

public class NoticesFragment extends Fragment {

    private NoticesItemAdapter mAdapter;

    private ProgressBar mProgressBar;
    private SmoothRefreshLayout mRefreshLayout;

    public static NoticesFragment newInstance() {
        return new NoticesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // View model instance
        NoticesViewModel mViewModel = ViewModelProviders.of(this).get(NoticesViewModel.class);
        mViewModel.getNotices().observe(this, notices -> {
            mAdapter.submitList(notices);
            mProgressBar.setVisibility(View.GONE);
        });
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
        return v;
    }

}
