package mx.edu.upqroo.kristenandroid.ui.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.adapters.NewsItemAdapter;
import mx.edu.upqroo.kristenandroid.viewModels.NewsViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsListFragment extends Fragment {

    private NewsViewModel mViewModel;

    private NewsItemAdapter mAdapter;
    private ProgressBar mProgressBar;
    private SwipeRefreshLayout mSwipeContainer;

    public static NewsListFragment newInstance() {
        return new NewsListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // View model instance
        mViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news_list, container, false);

        mProgressBar = v.findViewById(R.id.progress_news);
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisibility(View.VISIBLE);

        mSwipeContainer = v.findViewById(R.id.refreshLayout_NewsList);
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


        TextView mTextErrorMessage = v.findViewById(R.id.text_error_message);
        mTextErrorMessage.setVisibility(View.INVISIBLE);

        RecyclerView mRecyclerNews = v.findViewById(R.id.recycler_news);
        mRecyclerNews.setHasFixedSize(true);
        mRecyclerNews.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new NewsItemAdapter(getContext());
        mRecyclerNews.setAdapter(mAdapter);

        mViewModel.getNews().observe(this, news -> {
            mAdapter.submitList(news);
            mProgressBar.setVisibility(View.GONE);
            mSwipeContainer.setRefreshing(false);
        });
        return v;
    }
}
