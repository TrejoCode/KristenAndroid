package mx.edu.upqroo.kristenandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.crashlytics.android.Crashlytics;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.common.Serializer;
import mx.edu.upqroo.kristenandroid.fragments.NewsDetailFragment;
import mx.edu.upqroo.kristenandroid.models.Content;
import mx.edu.upqroo.kristenandroid.models.News;
import mx.edu.upqroo.kristenandroid.service.ApiServices;
import mx.edu.upqroo.kristenandroid.service.messages.NewsDetailMessage;

public class NewsDetailActivity extends UpqrooActivity {
    public static final String EXTRA_NEWS = "KEY_NEWS";
    private News mNews;
    private ProgressBar mProgressBar;
    public static List<Content> NEWS_CONTENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        Toolbar mToolbar = findViewById(R.id.ioexample_toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (getIntent().hasExtra(EXTRA_NEWS)) {
            mNews = Serializer.Deserialize(getIntent().getStringExtra(EXTRA_NEWS), News.class);
        } else {
            Crashlytics.log("Noticia no existente");
            startActivity(new Intent(this, MainActivity.class));
        }

        ImageView mCoverImage = findViewById(R.id.cover_news_detail);
        TextView mTextTitle = findViewById(R.id.title_news_detail);
        TextView mTextSubTitle = findViewById(R.id.subtitle_news_detail);
        mProgressBar = findViewById(R.id.progress_news_detail);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_share) {
            if (mNews.getPostType() == 1) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        mNews.getTitle() + "\n" + "http://www.google.com");
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Share"));
            } else if (mNews.getPostType() == 2) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        mNews.getTitle() + "\n" + "http://www.google.com");
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Share"));
            } else if (mNews.getPostType() == 3) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        mNews.getTitle() + "\n" + "http://www.google.com");
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Share"));
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(NewsDetailMessage event) {
        NEWS_CONTENT = event.getNewsDetail().getContentList();
        NewsDetailFragment newsFragment = new NewsDetailFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_detail, newsFragment);
        fragmentTransaction.commit();
        mProgressBar.setVisibility(View.GONE);
    }
}
