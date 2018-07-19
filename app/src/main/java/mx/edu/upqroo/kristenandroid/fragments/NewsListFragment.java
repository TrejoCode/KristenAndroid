package mx.edu.upqroo.kristenandroid.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsListFragment extends Fragment {
    private RecyclerView mRecyclerNews;
    private NewsItemAdapter mNewsAdapter;
    private ArrayList<News> mNewsList;
    private EndlessRecyclerViewScrollListener mScrollListener;
    private SmoothRefreshLayout mRefreshLayout;

    public NewsListFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news_list, container, false);
        mNewsList = new ArrayList<>();

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
                Toast.makeText(view.getContext(), "Pagina actual: " + page +
                        " Total items count: " + totalItemsCount, Toast.LENGTH_LONG).show();
            }
        };

        mRefreshLayout = v.findViewById(R.id.refreshLayout_NewsList);

        mRefreshLayout.setHeaderView(new ClassicHeader(getContext()));
        mRefreshLayout.setOnRefreshListener(new RefreshingListenerAdapter() {
            @Override
            public void onRefreshBegin(boolean isRefresh) {
                mNewsList.clear();
                mScrollListener.resetState();
                ApiServices
                        .getPublicationsList(Integer.parseInt(SessionHelper
                                .getInstance()
                                .getSession()
                                .getCareer()), 1);
            }
        });

        mNewsAdapter = new NewsItemAdapter(v.getContext(), mNewsList);
        mNewsAdapter.setClickListener(new NewsItemAdapter.ItemClickListener() {
            @Override
            public void onNewsItemClick(View view, int position) {
                Intent intent = new Intent(view.getContext(), NewsDetailActivity.class);
                intent.putExtra(NewsDetailActivity.EXTRA_NEWS,
                        Serializer.Serialize(mNewsAdapter.getItem(position)));
                startActivity(intent);
            }
        });
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
        ApiServices
                .getPublicationsList(Integer.parseInt(SessionHelper
                        .getInstance()
                        .getSession()
                        .getCareer()), 1);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(NewsListMessage event) {
        mNewsList.addAll(event.newsList);
        mNewsAdapter.notifyDataSetChanged();
        mRefreshLayout.refreshComplete();
    }
}
