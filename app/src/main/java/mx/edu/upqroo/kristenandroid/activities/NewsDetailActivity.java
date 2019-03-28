package mx.edu.upqroo.kristenandroid.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.crashlytics.android.Crashlytics;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.ColorUtils;
import androidx.fragment.app.FragmentTransaction;
import androidx.palette.graphics.Palette;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.common.PostTypeHelper;
import mx.edu.upqroo.kristenandroid.common.Serializer;
import mx.edu.upqroo.kristenandroid.common.SessionHelper;
import mx.edu.upqroo.kristenandroid.database.entities.Content;
import mx.edu.upqroo.kristenandroid.database.entities.News;
import mx.edu.upqroo.kristenandroid.fragments.NewsDetailFragment;
import mx.edu.upqroo.kristenandroid.services.kristen.KristenApiServices;
import mx.edu.upqroo.kristenandroid.services.kristen.messages.NewsDetailMessage;

public class NewsDetailActivity extends ThemeActivity {
    public static final String EXTRA_NEWS = "KEY_NEWS";
    private News mNews;
    private ProgressBar mProgressBar;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private ImageView mCoverImageView;
    public static List<Content> NEWS_CONTENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        Toolbar mToolbar = findViewById(R.id.toolbar_news_detail);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(v -> onBackPressed());

        if (getIntent().hasExtra(EXTRA_NEWS)) {
            mNews = Serializer.Deserialize(getIntent().getStringExtra(EXTRA_NEWS), News.class);
        } else {
            Crashlytics.log("Noticia no existente");
            startActivity(new Intent(this, MainActivity.class));
        }
        mProgressBar = findViewById(R.id.progress_news_detail);
        mCoverImageView = findViewById(R.id.image_cover_news_detail);
        mCollapsingToolbar = findViewById(R.id.collapsing_news_detail);

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
            setUpToolbar(mNews.getCoverUrl(), mNews.getTitle());
            NEWS_CONTENT = new ArrayList<>();
            NEWS_CONTENT.add(new Content(mNews.getDescription()));
            NEWS_CONTENT.addAll(event.getNewsDetail().getContentList());
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_detail, NewsDetailFragment.newInstance());
            fragmentTransaction.commit();
        }
        mProgressBar.setVisibility(View.GONE);
    }

    private void setUpToolbar(String coverUrl, String title) {
        mCollapsingToolbar.setTitle(title);
        //region collapsing toolbar color setup
        Picasso.get()
                .load("https://picsum.photos/500/500/?random")
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .placeholder(R.drawable.ic_launcher_background)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        assert mCoverImageView != null;
                        mCoverImageView.setImageBitmap(bitmap);
                        Palette.from(bitmap)
                                .generate(palette -> {
                                    int finalColor = 0;
                                    assert palette != null;
                                    if (palette.getDarkMutedSwatch() != null) {
                                        finalColor = palette.getDarkMutedSwatch().getRgb();
                                    } else if (palette.getVibrantSwatch() != null) {
                                        finalColor = palette.getVibrantSwatch().getRgb();
                                    } else if (palette.getDominantSwatch() != null) {
                                        finalColor = palette.getDominantSwatch().getRgb();
                                    }

                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        getWindow().setStatusBarColor(finalColor);
                                    }
                                    mCollapsingToolbar.setContentScrimColor(ColorUtils
                                            .setAlphaComponent(finalColor, 122));
                                });
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
        //endregion
    }
}
