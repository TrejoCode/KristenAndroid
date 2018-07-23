package mx.edu.upqroo.kristenandroid.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.common.FragmentHelper;
import mx.edu.upqroo.kristenandroid.common.NotificationsHelper;
import mx.edu.upqroo.kristenandroid.common.SessionHelper;
import mx.edu.upqroo.kristenandroid.fragments.GradesFragment;
import mx.edu.upqroo.kristenandroid.fragments.KardexFragment;
import mx.edu.upqroo.kristenandroid.fragments.NewsListFragment;
import mx.edu.upqroo.kristenandroid.fragments.ScheduleFragment;
import mx.edu.upqroo.kristenandroid.fragments.UserFragment;
import mx.edu.upqroo.kristenandroid.widget.ScheduleWidget;

public class NewsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentHelper mFragmentHelper;
    private Toolbar mToolbar;
    private SessionHelper mSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSession = SessionHelper.getInstance();
        NotificationsHelper
                .SubscribeNotifications(mSession.getSession().getGeneralTopic(),
                        mSession.getSession().getUserTopic());

        onWidgetUpdateMessage(this);

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
            showLogoutDialog();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news, menu);
        TextView mNavHeaderProfileName = findViewById(R.id.text_nav_header_title);
        mNavHeaderProfileName.setText(mSession.getSession().getName());
        TextView mNavHeaderProfileEmail = findViewById(R.id.text_nav_header_subtitle);
        mNavHeaderProfileEmail.setText(mSession.getSession().getEmail());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_news) {
            if (mFragmentHelper != FragmentHelper.NEWS){
                mFragmentHelper = FragmentHelper.NEWS;
                NewsListFragment fragment = new NewsListFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main, fragment)
                        .commit();
                mToolbar.setTitle(R.string.nav_menu_news);
            }
        } else if (id == R.id.nav_user) {
            if (mFragmentHelper != FragmentHelper.USER){
                mFragmentHelper = FragmentHelper.USER;
                UserFragment fragment = new UserFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main, fragment)
                        .commit();
                mToolbar.setTitle(R.string.nave_menu_user);
            }
        } else if (id == R.id.nav_schedule) {
            if (mFragmentHelper != FragmentHelper.SCHEDULE) {
                mFragmentHelper = FragmentHelper.SCHEDULE;
                ScheduleFragment fragment = new ScheduleFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main, fragment)
                        .commit();
                mToolbar.setTitle(R.string.nav_menu_schedule);
            }
        } else if (id == R.id.nav_school) {
            if (mFragmentHelper != FragmentHelper.GRADES) {
                mFragmentHelper = FragmentHelper.GRADES;
                GradesFragment fragment = new GradesFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main, fragment)
                        .commit();
                mToolbar.setTitle(R.string.nav_menu_school);
            }
        } else if (id == R.id.nav_kardex) {
            if (mFragmentHelper != FragmentHelper.KARDEX) {
                mFragmentHelper = FragmentHelper.KARDEX;
                KardexFragment fragment = new KardexFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main, fragment)
                        .commit();
                mToolbar.setTitle(R.string.nav_menu_kardex);
            }
        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        } else if (id == R.id.nav_logout) {
            showLogoutDialog();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void onWidgetUpdateMessage(Context context) {
        Intent intent_meeting_update = new Intent(context, ScheduleWidget.class);
        intent_meeting_update.setAction(ScheduleWidget.UPDATE_MEETING_ACTION);
        sendBroadcast(intent_meeting_update);
    }

    private void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mSession.logout();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
