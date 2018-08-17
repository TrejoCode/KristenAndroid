package mx.edu.upqroo.kristenandroid.activities;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentTransaction;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.common.Serializer;
import mx.edu.upqroo.kristenandroid.fragments.YoutubeFragment;
import mx.edu.upqroo.kristenandroid.models.News;
import mx.edu.upqroo.kristenandroid.service.ApiServices;
import mx.edu.upqroo.kristenandroid.service.containers.Contenido;
import mx.edu.upqroo.kristenandroid.service.messages.PostContentMessage;

public class NewsDetailActivity extends AppCompatActivity
        implements AppBarLayout.OnOffsetChangedListener {
    public static final String EXTRA_NEWS = "KEY_NEWS";
    private News mNews;
    private TextView mDescription;
    private TextView mCategory;
    private TextView mContent;
    private TextView mUrlOne;
    private TextView mUrlTwo;
    private TextView mUrlThree;

    private ImageView mCover;
    private ImageView mImage1;
    private ImageView mImage2;
    private ImageView mImage3;
    private ImageView mImage4;
    private ImageView mImage5;
    private ImageView mImage6;

    private String imageUrl1;
    private String imageUrl2;
    private String imageUrl3;
    private String imageUrl4;
    private String imageUrl5;
    private String imageUrl6;

    private Toolbar mToolbar;
    private ProgressBar mProgressBar;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private View mFab;
    private int mMaxScrollSize;
    private boolean mIsImageHidden;
    private static final int PERCENTAGE_TO_SHOW_IMAGE = 20;
    private View mYoutubeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        mToolbar = findViewById(R.id.toolbarNewsDetail);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        if (getIntent().hasExtra(EXTRA_NEWS)) {
            mNews = Serializer.Deserialize(getIntent().getStringExtra(EXTRA_NEWS), News.class);
        } else {
            Toast.makeText(this, "La noticia que buscabas no se encuentra disponible",
                    Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, MainActivity.class));
        }

        mProgressBar = findViewById(R.id.progressBar_news_detail);
        mProgressBar.setVisibility(View.VISIBLE);
        mCollapsingToolbar = findViewById(R.id.collapsing_toolbar);
        mCollapsingToolbar.setTitleEnabled(false);
        mDescription = findViewById(R.id.text_detail_description);
        mCategory = findViewById(R.id.text_detail_category);
        mContent = findViewById(R.id.text_detail_content);
        mCover = findViewById(R.id.image_detail_cover);
        mUrlOne = findViewById(R.id.text_detail_url1);
        mUrlTwo = findViewById(R.id.text_detail_url2);
        mUrlThree = findViewById(R.id.text_detail_url3);
        mImage1 = findViewById(R.id.image_detail_1);
        mImage2 = findViewById(R.id.image_detail_2);
        mImage3 = findViewById(R.id.image_detail_3);
        mImage4 = findViewById(R.id.image_detail_4);
        mImage5 = findViewById(R.id.image_detail_5);
        mImage6 = findViewById(R.id.image_detail_6);

        mDescription.setVisibility(View.GONE);
        mCategory.setVisibility(View.GONE);
        mContent.setVisibility(View.GONE);
        mCover.setVisibility(View.GONE);

        mImage1.setVisibility(View.GONE);
        mImage2.setVisibility(View.GONE);
        mImage3.setVisibility(View.GONE);
        mImage4.setVisibility(View.GONE);
        mImage5.setVisibility(View.GONE);
        mImage6.setVisibility(View.GONE);
        mUrlOne.setVisibility(View.GONE);
        mUrlTwo.setVisibility(View.GONE);
        mUrlThree.setVisibility(View.GONE);

        Picasso.get()
                .load(mNews.getCoverUrl())
                .placeholder(R.drawable.side_nav_bar)
                .error(R.drawable.side_nav_bar)
                .into(mCover);

        ApiServices.getPostContent(mNews.getId());

        mFab = findViewById(R.id.flexible_news_fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        });

        mYoutubeFragment = findViewById(R.id.youtube_fragment);
        mYoutubeFragment.setVisibility(View.GONE);

        AppBarLayout appbar = findViewById(R.id.appBarLayout);
        appbar.addOnOffsetChangedListener(this);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        int currentScrollPercentage = (Math.abs(i)) * 100
                / mMaxScrollSize;

        if (currentScrollPercentage >= PERCENTAGE_TO_SHOW_IMAGE) {
            if (!mIsImageHidden) {
                mIsImageHidden = true;
                ViewCompat.animate(mFab).scaleY(0).scaleX(0).start();
            }
        }

        if (currentScrollPercentage < PERCENTAGE_TO_SHOW_IMAGE) {
            if (mIsImageHidden) {
                mIsImageHidden = false;
                ViewCompat.animate(mFab).scaleY(1).scaleX(1).start();
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PostContentMessage event) {
        try {
            mCollapsingToolbar.setTitle(event.getPublicacionContenido().getTitulo());
            mCollapsingToolbar.setTitleEnabled(true);
            mToolbar.setTitle(event.getPublicacionContenido().getTitulo());
            mNews.setPostType(event.getPublicacionContenido().getIdTiposPublicacion());
            mDescription.setText(event.getPublicacionContenido().getDescripcion());
            StringBuilder content = new StringBuilder();
            ArrayList<String> sites = new ArrayList<>();
            ArrayList<String> url = new ArrayList<>();
            int i = 0;
            for (Contenido c : event.getPublicacionContenido().getContenidos()) {
                if (c.getIdTipoContenidos() == 1) {
                    //region text
                    content.append(c.getContenido().getTexto());
                    content.append("\n");
                    //endregion
                } else if (c.getIdTipoContenidos() == 2) {
                    //region links
                    sites.add(c.getContenido().getTexto());
                    url.add(c.getContenido().getUrl());
                    //endregion
                } else if (c.getIdTipoContenidos() == 5) {
                    //region gallery
                    for (String s : c.getContenido().getImagenes()) {
                        switch (i) {
                            case 0:
                                imageUrl1 = c.getContenido().getImagenes().get(i);
                                Picasso.get()
                                        .load(imageUrl1)
                                        .placeholder(R.drawable.side_nav_bar)
                                        .error(R.drawable.side_nav_bar)
                                        .into(mImage1);
                                mImage1.setVisibility(View.VISIBLE);
                                mImage1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Dialog dialog = new Dialog(NewsDetailActivity.this);
                                        dialog.setContentView(R.layout.image_dialog_layout);
                                        dialog.setTitle("Image");
                                        ImageView myImage = dialog.findViewById(R.id.image_dialog);
                                        Picasso.get()
                                                .load(imageUrl1)
                                                .placeholder(R.drawable.side_nav_bar)
                                                .error(R.drawable.side_nav_bar)
                                                .into(myImage);
                                        dialog.show();
                                    }
                                });
                                i++;
                                break;
                            case 1:
                                imageUrl2 = c.getContenido().getImagenes().get(i);
                                Picasso.get()
                                        .load(imageUrl2)
                                        .placeholder(R.drawable.side_nav_bar)
                                        .error(R.drawable.side_nav_bar)
                                        .into(mImage2);
                                mImage2.setVisibility(View.VISIBLE);
                                mImage2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Dialog dialog = new Dialog(NewsDetailActivity.this);
                                        dialog.setContentView(R.layout.image_dialog_layout);
                                        dialog.setTitle("Image");
                                        ImageView myImage = dialog.findViewById(R.id.image_dialog);
                                        Picasso.get()
                                                .load(imageUrl2)
                                                .placeholder(R.drawable.side_nav_bar)
                                                .error(R.drawable.side_nav_bar)
                                                .into(myImage);
                                        dialog.show();
                                    }
                                });
                                i++;
                                break;
                            case 2:
                                imageUrl3 = c.getContenido().getImagenes().get(i);
                                Picasso.get()
                                        .load(imageUrl3)
                                        .placeholder(R.drawable.side_nav_bar)
                                        .error(R.drawable.side_nav_bar)
                                        .into(mImage3);
                                mImage3.setVisibility(View.VISIBLE);
                                mImage3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Dialog dialog = new Dialog(NewsDetailActivity.this);
                                        dialog.setContentView(R.layout.image_dialog_layout);
                                        dialog.setTitle("Image");
                                        ImageView myImage = dialog.findViewById(R.id.image_dialog);
                                        Picasso.get()
                                                .load(imageUrl3)
                                                .placeholder(R.drawable.side_nav_bar)
                                                .error(R.drawable.side_nav_bar)
                                                .into(myImage);
                                        dialog.show();
                                    }
                                });
                                i++;
                                break;
                            case 3:
                                imageUrl4 = c.getContenido().getImagenes().get(i);
                                Picasso.get()
                                        .load(imageUrl4)
                                        .placeholder(R.drawable.side_nav_bar)
                                        .error(R.drawable.side_nav_bar)
                                        .into(mImage4);
                                mImage4.setVisibility(View.VISIBLE);
                                mImage4.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Dialog dialog = new Dialog(NewsDetailActivity.this);
                                        dialog.setContentView(R.layout.image_dialog_layout);
                                        dialog.setTitle("Image");
                                        ImageView myImage = dialog.findViewById(R.id.image_dialog);
                                        Picasso.get()
                                                .load(imageUrl4)
                                                .placeholder(R.drawable.side_nav_bar)
                                                .error(R.drawable.side_nav_bar)
                                                .into(myImage);
                                        dialog.show();
                                    }
                                });
                                i++;
                                break;
                            case 4:
                                imageUrl5 = c.getContenido().getImagenes().get(i);
                                Picasso.get()
                                        .load(imageUrl5)
                                        .placeholder(R.drawable.side_nav_bar)
                                        .error(R.drawable.side_nav_bar)
                                        .into(mImage5);
                                mImage5.setVisibility(View.VISIBLE);
                                mImage5.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Dialog dialog = new Dialog(NewsDetailActivity.this);
                                        dialog.setContentView(R.layout.image_dialog_layout);
                                        dialog.setTitle("Image");
                                        ImageView myImage = dialog.findViewById(R.id.image_dialog);
                                        Picasso.get()
                                                .load(imageUrl5)
                                                .placeholder(R.drawable.side_nav_bar)
                                                .error(R.drawable.side_nav_bar)
                                                .into(myImage);
                                        dialog.show();
                                    }
                                });
                                i++;
                                break;
                            case 5:
                                imageUrl6 = c.getContenido().getImagenes().get(i);
                                Picasso.get()
                                        .load(imageUrl6)
                                        .placeholder(R.drawable.side_nav_bar)
                                        .error(R.drawable.side_nav_bar)
                                        .into(mImage6);
                                mImage6.setVisibility(View.VISIBLE);
                                mImage6.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Dialog dialog = new Dialog(NewsDetailActivity.this);
                                        dialog.setContentView(R.layout.image_dialog_layout);
                                        dialog.setTitle("Image");
                                        ImageView myImage = dialog.findViewById(R.id.image_dialog);
                                        Picasso.get()
                                                .load(imageUrl6)
                                                .placeholder(R.drawable.side_nav_bar)
                                                .error(R.drawable.side_nav_bar)
                                                .into(myImage);
                                        dialog.show();
                                    }
                                });
                                break;
                        }
                    }
                    //endregion
                } else if (c.getIdTipoContenidos() == 7) {
                    //region video
                    mYoutubeFragment.setVisibility(View.VISIBLE);
                    YoutubeFragment youTubeNativeFragmentDemo = new YoutubeFragment();
                    Bundle args = new Bundle();
                    args.putString("videoId", c.getContenido().getId());
                    youTubeNativeFragmentDemo.setArguments(args);
                    FragmentTransaction fragmentTransaction =
                            getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.youtube_fragment, youTubeNativeFragmentDemo);
                    fragmentTransaction.commit();
                    //endregion
                }
            }
            if (!sites.isEmpty()) {
                if (!sites.get(0).equals("")) {
                    mUrlOne.setText(url.get(0));
                    mUrlOne.setVisibility(View.VISIBLE);
                    if (!sites.get(1).equals("")) {
                        mUrlTwo.setText(url.get(1));
                        mUrlTwo.setVisibility(View.VISIBLE);
                        if (!sites.get(2).equals("")) {
                            mUrlThree.setText(url.get(2));
                            mUrlThree.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
            mContent.setText(content);
            mCategory.setText(event.getPublicacionContenido().getCategorias());
            mDescription.setVisibility(View.VISIBLE);
            mCategory.setVisibility(View.VISIBLE);
            mContent.setVisibility(View.VISIBLE);
            mCover.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);
        } catch (Exception ex) {
            mProgressBar.setVisibility(View.GONE);
            Toast.makeText(this,
                    "No se ha podido cargar la noticia", Toast.LENGTH_LONG).show();
        }
    }
}
