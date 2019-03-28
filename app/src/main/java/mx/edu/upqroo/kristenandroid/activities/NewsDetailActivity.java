package mx.edu.upqroo.kristenandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.crashlytics.android.Crashlytics;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.common.PostTypeHelper;
import mx.edu.upqroo.kristenandroid.common.Serializer;
import mx.edu.upqroo.kristenandroid.common.SessionHelper;
import mx.edu.upqroo.kristenandroid.fragments.NewsDetailFragment;
import mx.edu.upqroo.kristenandroid.database.entities.Content;
import mx.edu.upqroo.kristenandroid.database.entities.ContentImage;
import mx.edu.upqroo.kristenandroid.database.entities.ContentTitle;
import mx.edu.upqroo.kristenandroid.database.entities.News;
import mx.edu.upqroo.kristenandroid.services.kristen.KristenApiServices;
import mx.edu.upqroo.kristenandroid.services.kristen.messages.NewsDetailMessage;

public class NewsDetailActivity extends ThemeActivity {
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
        mProgressBar = findViewById(R.id.progress_news_detail);

        KristenApiServices.getPostContent(mNews.getId());
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
            if (mNews.getPostType() == PostTypeHelper.EVENT.getId()) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        mNews.getTitle() + "\n" + SessionHelper.getInstance().getSession()
                                .getConfig().getBaseAddress() + "evento/" + mNews.getUrl());
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Share"));
            } else if (mNews.getPostType() == PostTypeHelper.NEWS.getId()) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        mNews.getTitle() + "\n" + SessionHelper.getInstance().getSession()
                                .getConfig().getBaseAddress() + "noticia/" + mNews.getUrl());
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Share"));
            } else if (mNews.getPostType() == PostTypeHelper.WORK.getId()) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        mNews.getTitle() + "\n" + SessionHelper.getInstance().getSession()
                                .getConfig().getBaseAddress() + "trabajo/" + mNews.getUrl());
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Share"));
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(NewsDetailMessage event) {
        if (event.isSuccessful()) {
            NEWS_CONTENT = new ArrayList<>();
            NEWS_CONTENT.add(new ContentImage(mNews.getTitle(), mNews.getCoverUrl()));
            NEWS_CONTENT.add(new ContentTitle(mNews.getTitle() + " - " + mNews.getDescription()));
            NEWS_CONTENT.addAll(event.getNewsDetail().getContentList());
            NewsDetailFragment newsFragment = new NewsDetailFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_detail, newsFragment);
            fragmentTransaction.commit();
        }
        mProgressBar.setVisibility(View.GONE);
    }
}
