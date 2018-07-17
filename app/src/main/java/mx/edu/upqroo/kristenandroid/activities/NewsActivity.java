package mx.edu.upqroo.kristenandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.common.FragmentHelper;
import mx.edu.upqroo.kristenandroid.common.SessionHelper;
import mx.edu.upqroo.kristenandroid.fragments.GradesFragment;
import mx.edu.upqroo.kristenandroid.fragments.GroupsFragment;
import mx.edu.upqroo.kristenandroid.fragments.KardexFragment;
import mx.edu.upqroo.kristenandroid.fragments.NewsListFragment;
import mx.edu.upqroo.kristenandroid.fragments.ScheduleFragment;
import mx.edu.upqroo.kristenandroid.fragments.UserFragment;

public class NewsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentHelper mFragmentHelper;
    private Toolbar mToolbar;
    private SessionHelper mSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSession = SessionHelper.getInstance();

        setContentView(R.layout.activity_news);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

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
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_news);

        mFragmentHelper = FragmentHelper.NEWS;
        NewsListFragment initialFrag = new NewsListFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_main, initialFrag)
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            mSession.logout();
            startActivity(new Intent(this, MainActivity.class));
            //todo agregar dialogo que te pregunte si de verdad quieres salir de la aplicación
            //super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.news, menu);
        TextView mNavHeaderProfileName = findViewById(R.id.text_nav_header_title);
        mNavHeaderProfileName.setText(mSession.getSession().getName());
        TextView mNavHeaderProfileEmail = findViewById(R.id.text_nav_header_subtitle);
        mNavHeaderProfileEmail.setText(mSession.getSession().getEmail());
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_news) {
            if (mFragmentHelper != FragmentHelper.NEWS){
                mFragmentHelper = FragmentHelper.NEWS;
                NewsListFragment fragment = new NewsListFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main, fragment)
                        .commit();
                mToolbar.setTitle("News");
            }
        } else if (id == R.id.nav_user) {
            if (mFragmentHelper != FragmentHelper.USER){
                mFragmentHelper = FragmentHelper.USER;
                UserFragment fragment = new UserFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main, fragment)
                        .commit();
                mToolbar.setTitle("Student");
            }
        } else if (id == R.id.nav_schedule) {
            if (mFragmentHelper != FragmentHelper.SCHEDULE) {
                mFragmentHelper = FragmentHelper.SCHEDULE;
                ScheduleFragment fragment = new ScheduleFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main, fragment)
                        .commit();
                mToolbar.setTitle("Schedule");
            }
        } else if (id == R.id.nav_school) {
            if (mFragmentHelper != FragmentHelper.GRADES) {
                mFragmentHelper = FragmentHelper.GRADES;
                GradesFragment fragment = new GradesFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main, fragment)
                        .commit();
                mToolbar.setTitle("Grades");
            }
        } else if (id == R.id.nav_kardex) {
            if (mFragmentHelper != FragmentHelper.KARDEX) {
                mFragmentHelper = FragmentHelper.KARDEX;
                KardexFragment fragment = new KardexFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main, fragment)
                        .commit();
                mToolbar.setTitle("Kardex");
            }
        } else if (id == R.id.nav_group) {
            if (mFragmentHelper != FragmentHelper.GROUPS) {
                mFragmentHelper = FragmentHelper.GROUPS;
                GroupsFragment fragment = new GroupsFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main, fragment)
                        .commit();
                mToolbar.setTitle("Groups");
            }
        } else if (id == R.id.nav_logout) {
            //todo agregar confirmación de cerrar sesión
            startActivity(new Intent(this, MainActivity.class));
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
