package mx.edu.upqroo.kristenandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.adapters.NewsDetailContentAdapter;
import mx.edu.upqroo.kristenandroid.common.Serializer;
import mx.edu.upqroo.kristenandroid.models.Content;
import mx.edu.upqroo.kristenandroid.models.News;
import mx.edu.upqroo.kristenandroid.service.ApiServices;
import mx.edu.upqroo.kristenandroid.service.messages.NewsDetailMessage;

public class NewsDetailActivity2 extends UpqrooActivity {
    public static final String EXTRA_NEWS = "KEY_NEWS";
    private RecyclerView mRecyclerView;
    private List<Content> mContentList;
    private News mNews;
    private NewsDetailContentAdapter mAdapter;

    private ImageView mCoverImage;
    private TextView mTextTitle;
    private TextView mTextSubTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail2);

        if (getIntent().hasExtra(EXTRA_NEWS)) {
            mNews = Serializer.Deserialize(getIntent().getStringExtra(EXTRA_NEWS), News.class);
        } else {
            Crashlytics.log("Noticia no existente");
            startActivity(new Intent(this, MainActivity.class));
        }

        mContentList = new ArrayList<>();
        mAdapter = new NewsDetailContentAdapter(this,
                mContentList, getSupportFragmentManager());

        mRecyclerView = findViewById(R.id.recycler_news_detail_content);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        mCoverImage = findViewById(R.id.cover_news_detail);
        mTextTitle = findViewById(R.id.title_news_detail);
        mTextSubTitle = findViewById(R.id.subtitle_news_detail);

        Picasso.get()
                .load(mNews.getCoverUrl())
                .placeholder(R.drawable.side_nav_bar)
                .error(R.drawable.side_nav_bar)
                .into(mCoverImage);

        mTextTitle.setText(mNews.getTitle());
        mTextSubTitle.setText(mNews.getDescription());

        ApiServices.getPostContent(mNews.getId());
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(NewsDetailMessage event) {
        mContentList.addAll(event.getNewsDetail().getContentList());
        mAdapter.notifyDataSetChanged();
    }
}
