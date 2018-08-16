package mx.edu.upqroo.kristenandroid.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.dkzwm.widget.srl.RefreshingListenerAdapter;
import me.dkzwm.widget.srl.SmoothRefreshLayout;
import me.dkzwm.widget.srl.extra.header.ClassicHeader;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.activities.NewsDetailActivity;
import mx.edu.upqroo.kristenandroid.adapters.NewsItemAdapter;
import mx.edu.upqroo.kristenandroid.common.EndlessRecyclerViewScrollListener;
import mx.edu.upqroo.kristenandroid.common.Serializer;
import mx.edu.upqroo.kristenandroid.common.SessionHelper;
import mx.edu.upqroo.kristenandroid.models.News;
import mx.edu.upqroo.kristenandroid.service.ApiServices;
import mx.edu.upqroo.kristenandroid.service.messages.NewsListMessage;
import mx.edu.upqroo.kristenandroid.service.messages.NewsListMessageError;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsListFragment extends Fragment {
    private RecyclerView mRecyclerNews;
    private NewsItemAdapter mNewsAdapter;
    private ArrayList<News> mNewsList;
    private EndlessRecyclerViewScrollListener mScrollListener;
    private SmoothRefreshLayout mRefreshLayout;
    private ProgressBar mProgressBar;
    private TextView mTextErrorMessage;

    public NewsListFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news_list, container, false);
        mNewsList = new ArrayList<>();

        mProgressBar = v.findViewById(R.id.progress_news);
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisibility(View.VISIBLE);

        mTextErrorMessage = v.findViewById(R.id.text_error_message);
        mTextErrorMessage.setVisibility(View.GONE);

        LinearLayoutManager lineaLayoutManager = new LinearLayoutManager(v.getContext());
        mScrollListener = new EndlessRecyclerViewScrollListener(lineaLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                ApiServices
                        .getPublicationsList(Integer.parseInt(SessionHelper
                                .getInstance()
                                .getSession()
                                .getCareer()), page);
                mNewsAdapter.notifyItemRangeInserted(totalItemsCount, 5);
            }
        };

        mRefreshLayout = v.findViewById(R.id.refreshLayout_NewsList);

        mRefreshLayout.setHeaderView(new ClassicHeader(getContext()));
        mRefreshLayout.setOnRefreshListener(new RefreshingListenerAdapter() {
            @Override
            public void onRefreshBegin(boolean isRefresh) {
                mNewsList.clear();
                mScrollListener.resetState();
                generateCover();
                mNewsAdapter.notifyDataSetChanged();
            }
        });

        mNewsAdapter = new NewsItemAdapter(v.getContext(), mNewsList);
        mNewsAdapter.setClickListener(new NewsItemAdapter.ItemClickListener() {
            @Override
            public void onNewsItemClick(View view, int position) {
                if (!mNewsAdapter.getItem(position).getCategory().equals("COVER")){
                    Intent intent = new Intent(view.getContext(), NewsDetailActivity.class);
                    intent.putExtra(NewsDetailActivity.EXTRA_NEWS,
                            Serializer.Serialize(mNewsAdapter.getItem(position)));
                    startActivity(intent);
                }
            }
        });

        generateCover();
        mRecyclerNews = v.findViewById(R.id.recycler_news);
        mRecyclerNews.setHasFixedSize(true);
        mRecyclerNews.setLayoutManager(lineaLayoutManager);
        mRecyclerNews.addOnScrollListener(mScrollListener);
        mRecyclerNews.setAdapter(mNewsAdapter);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(NewsListMessage event) {
        mProgressBar.setVisibility(View.GONE);
        mTextErrorMessage.setVisibility(View.GONE);
        mNewsList.addAll(event.newsList);
        mNewsAdapter.notifyDataSetChanged();
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.refreshComplete();
            Toast.makeText(getContext(), "Actualizado", Toast.LENGTH_LONG).show();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageErrorEvent(NewsListMessageError event) {
        mProgressBar.setVisibility(View.GONE);
        mTextErrorMessage.setText(event.getError());
        mTextErrorMessage.setVisibility(View.VISIBLE);
    }

    private void generateCover() {
        Date date = java.util.Calendar.getInstance().getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE, d MMMM", Locale.US);
        mNewsList.add(new News(0,0, getString(R.string.news_header_title),
                getString(R.string.news_header_desc),
                "COVER",
                "http://www.upqroo.edu.mx/wp-content/uploads/2018/01/bslider_b03.jpg",
                formatter.format(date)));
    }
}
