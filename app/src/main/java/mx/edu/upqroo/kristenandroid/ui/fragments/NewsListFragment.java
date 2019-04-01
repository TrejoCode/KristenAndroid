package mx.edu.upqroo.kristenandroid.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.dkzwm.widget.srl.RefreshingListenerAdapter;
import me.dkzwm.widget.srl.SmoothRefreshLayout;
import me.dkzwm.widget.srl.extra.header.ClassicHeader;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.managers.SessionManager;
import mx.edu.upqroo.kristenandroid.ui.activities.NewsDetailActivity;
import mx.edu.upqroo.kristenandroid.adapters.NewsItemAdapter;
import mx.edu.upqroo.kristenandroid.helpers.EndlessRecyclerViewScrollListener;
import mx.edu.upqroo.kristenandroid.util.Serializer;
import mx.edu.upqroo.kristenandroid.data.models.News;
import mx.edu.upqroo.kristenandroid.services.kristen.KristenApiServices;
import mx.edu.upqroo.kristenandroid.services.kristen.messages.NewsListMessage;
import mx.edu.upqroo.kristenandroid.viewModels.NewsViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsListFragment extends Fragment {
    private NewsItemAdapter mAdapter;
    private ProgressBar mProgressBar;

    public static NewsListFragment newInstance() {
        return new NewsListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // View model instance
        NewsViewModel mViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
        mViewModel.getNews().observe(this, notices -> {
            mAdapter.submitList(notices);
            mProgressBar.setVisibility(View.GONE);
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news_list, container, false);

        mProgressBar = v.findViewById(R.id.progress_news);
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisibility(View.VISIBLE);
        TextView mTextErrorMessage = v.findViewById(R.id.text_error_message);
        mTextErrorMessage.setVisibility(View.INVISIBLE);
        SmoothRefreshLayout mRefreshLayout = v.findViewById(R.id.refreshLayout_NewsList);
        //mRefreshLayout.setHeaderView(new ClassicHeader(getContext()));
        mAdapter = new NewsItemAdapter(getContext());

        RecyclerView mRecyclerNews = v.findViewById(R.id.recycler_news);
        mRecyclerNews.setHasFixedSize(true);
        mRecyclerNews.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerNews.setAdapter(mAdapter);
        return v;
    }

    private void generateCover() {
        Date date = java.util.Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, d MMMM", Locale.US);
        /*mNewsList.add(new News("0", "", 0, getString(R.string.news_header_title),
                getString(R.string.news_header_desc),
                "COVER",
                "http://www.upqroo.edu.mx/wp-content/uploads/2018/01/bslider_b03.jpg",
                formatter.format(date)));*/
    }
}
