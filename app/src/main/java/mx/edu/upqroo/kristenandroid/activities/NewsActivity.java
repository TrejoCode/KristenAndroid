package mx.edu.upqroo.kristenandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.adapters.NewsItemAdapter;
import mx.edu.upqroo.kristenandroid.common.Serializer;
import mx.edu.upqroo.kristenandroid.models.News;

public class NewsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView mRecyclerNews;
    ArrayList<News> mNewsList;
    NewsItemAdapter mNewsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mNewsList = new ArrayList<>();
        fillNewsList(mNewsList);

        mRecyclerNews = findViewById(R.id.recycler_news);
        mRecyclerNews.setHasFixedSize(true);
        mRecyclerNews.setLayoutManager(new LinearLayoutManager(this));
        mNewsAdapter = new NewsItemAdapter(this, mNewsList);
        mNewsAdapter.setClickListener(new NewsItemAdapter.ItemClickListener() {
            @Override
            public void onNewsItemClick(View view, int position) {
                Intent intent = new Intent(view.getContext(), NewsDetailActivity.class);
                intent.putExtra(NewsDetailActivity.EXTRA_NEWS,
                        Serializer.Serialize(mNewsAdapter.getItem(position)));
                startActivity(intent);
            }
        });
        mRecyclerNews.setAdapter(mNewsAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.news, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            startActivity(new Intent(this, GeneralInfoActivity.class));
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void fillNewsList(ArrayList<News> news){
        news.add(new News("Important new 1", "Generic subtitle","lorem ipsum sdfvisjfgisjfigjsg","Noticia",
                "https://i.pinimg.com/originals/1a/36/a7/1a36a7d0bba0eeaef587dbf29980e931.jpg"));
        news.add(new News("Important new 2", "Generic subtitle","lorem ipsum sdfvisjfgisjfigjsg","Aviso",
                "http://www.babymetal-darake.com/wp-content/uploads/2018/05/31428231_1686186171497482_3686882304359137280_n-768x575.jpg"));
        news.add(new News("Important new 3", "Generic subtitle","lorem ipsum sdfvisjfgisjfigjsg","Noticia",
                "https://cdnx.natalie.mu/media/news/music/2018/0522/_9Y1A2573_fixw_730_hq.jpg"));
        news.add(new News("Important new 4", "Generic subtitle","lorem ipsum sdfvisjfgisjfigjsg","Noticia",
                "http://cdn-ak.f.st-hatena.com/images/fotolife/o/onigashima/20160406/20160406003728.jpg"));
        news.add(new News("Dominando el mundo", "Las chinas van a dominar al mundo","lorem ipsum sdfvisjfgisjfigjsg","Aviso",
                "https://image.jimcdn.com/app/cms/image/transf/dimension=910x10000:format=jpg/path/sfeb3e2f9ecd74f84/image/i51e21e0d8017312a/version/1421125812/image.jpg"));
    }
}
