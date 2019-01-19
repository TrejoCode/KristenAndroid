package mx.edu.upqroo.kristenandroid.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.common.FragmentHelper;
import mx.edu.upqroo.kristenandroid.common.SessionHelper;
import mx.edu.upqroo.kristenandroid.fragments.GradesFragment;
import mx.edu.upqroo.kristenandroid.fragments.KardexFragment;
import mx.edu.upqroo.kristenandroid.fragments.NewsListFragment;
import mx.edu.upqroo.kristenandroid.fragments.ScheduleFragment;
import mx.edu.upqroo.kristenandroid.fragments.UserFragment;
import mx.edu.upqroo.kristenandroid.widget.ScheduleWidget;

public class MainActivity extends UpqrooActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        BottomNavigationView.OnNavigationItemSelectedListener {

    private FragmentHelper mFragmentHelper;
    private Toolbar mToolbar;
    private SessionHelper mSession;
    private NavigationView mNavigationView;
    private ArrayList<FragmentHelper> mHistoryList;
    private BottomNavigationView mButtonNavigationView;
    public static boolean HAS_THEME_CHANGED = false;
    private NewsListFragment mNewsListFragment;
    private UserFragment mUserFragment;
    private ScheduleFragment mScheduleFragment;
    private GradesFragment mGradesFragment;
    private KardexFragment mKardexFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSession = SessionHelper.getInstance();
        onWidgetUpdateMessage(this);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mHistoryList = new ArrayList<>();

        mButtonNavigationView = findViewById(R.id.bottom_navigation);
        mButtonNavigationView.setOnNavigationItemSelectedListener(this);

        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.setCheckedItem(R.id.nav_news);

        mNewsListFragment = new NewsListFragment();
        mUserFragment = new UserFragment();
        mGradesFragment = new GradesFragment();
        mKardexFragment = new KardexFragment();
        mScheduleFragment = new ScheduleFragment();

        mFragmentHelper = FragmentHelper.NEWS;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_main, mNewsListFragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (mHistoryList.size() > 0) {
                FragmentHelper mLastFragment = mHistoryList.get(mHistoryList.size() - 1);
                if (mLastFragment == FragmentHelper.NEWS) {
                    mNavigationView.setCheckedItem(R.id.nav_news);
                    mFragmentHelper = FragmentHelper.NEWS;
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_main, mNewsListFragment)
                            .commit();
                    mToolbar.setTitle(R.string.nav_menu_news);
                    mButtonNavigationView.setSelectedItemId(R.id.news_menu_item);
                    if (mButtonNavigationView.getVisibility() == View.GONE) {
                        mButtonNavigationView.setVisibility(View.VISIBLE);
                    }
                } else if (mLastFragment == FragmentHelper.USER) {
                    mNavigationView.setCheckedItem(R.id.nav_user);
                    mFragmentHelper = FragmentHelper.USER;
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_main, mUserFragment)
                            .commit();
                    mToolbar.setTitle(R.string.nave_menu_user);
                    mButtonNavigationView.setVisibility(View.GONE);
                } else if (mLastFragment == FragmentHelper.SCHEDULE) {
                    mNavigationView.setCheckedItem(R.id.nav_schedule);
                    mFragmentHelper = FragmentHelper.SCHEDULE;
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_main, mScheduleFragment)
                            .commit();
                    mToolbar.setTitle(R.string.nav_menu_schedule);
                    mButtonNavigationView.setSelectedItemId(R.id.schedule_menu_item);
                    if (mButtonNavigationView.getVisibility() == View.GONE) {
                        mButtonNavigationView.setVisibility(View.VISIBLE);
                    }
                } else if (mLastFragment == FragmentHelper.GRADES) {
                    mNavigationView.setCheckedItem(R.id.nav_school);
                    mFragmentHelper = FragmentHelper.GRADES;
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_main, mGradesFragment)
                            .commit();
                    mToolbar.setTitle(R.string.nav_menu_school);
                    mButtonNavigationView.setSelectedItemId(R.id.grades_menu_item);
                    if (mButtonNavigationView.getVisibility() == View.GONE) {
                        mButtonNavigationView.setVisibility(View.VISIBLE);
                    }
                } else if (mLastFragment == FragmentHelper.KARDEX) {
                    mNavigationView.setCheckedItem(R.id.nav_kardex);
                    mFragmentHelper = FragmentHelper.KARDEX;
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_main, mKardexFragment)
                            .commit();
                    mToolbar.setTitle(R.string.nav_menu_kardex);
                    mButtonNavigationView.setVisibility(View.GONE);
                }
                mHistoryList.remove(mHistoryList.size() - 1);
            } else {
                showLogoutDialog();
            }
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

        if (id == R.id.nav_news || id == R.id.news_menu_item) {
            if (mFragmentHelper != FragmentHelper.NEWS) {
                mHistoryList.add(mFragmentHelper);
                mFragmentHelper = FragmentHelper.NEWS;
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main, mNewsListFragment)
                        .commit();
                mToolbar.setTitle(R.string.nav_menu_news);
                mButtonNavigationView.setSelectedItemId(R.id.news_menu_item);
                mNavigationView.setCheckedItem(R.id.nav_news);
                if (mButtonNavigationView.getVisibility() == View.GONE) {
                    mButtonNavigationView.setVisibility(View.VISIBLE);
                }
            }
        } else if (id == R.id.nav_user) {
            if (mFragmentHelper != FragmentHelper.USER) {
                mHistoryList.add(mFragmentHelper);
                mFragmentHelper = FragmentHelper.USER;
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main, mUserFragment)
                        .commit();
                mToolbar.setTitle(R.string.nave_menu_user);
                mButtonNavigationView.setVisibility(View.GONE);
            }
        } else if (id == R.id.nav_schedule || id == R.id.schedule_menu_item) {
            if (mFragmentHelper != FragmentHelper.SCHEDULE) {
                mHistoryList.add(mFragmentHelper);
                mFragmentHelper = FragmentHelper.SCHEDULE;
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main, mScheduleFragment)
                        .commit();
                mToolbar.setTitle(R.string.nav_menu_schedule);
                mNavigationView.setCheckedItem(R.id.nav_schedule);
                mButtonNavigationView.setSelectedItemId(R.id.schedule_menu_item);
                if (mButtonNavigationView.getVisibility() == View.GONE) {
                    mButtonNavigationView.setVisibility(View.VISIBLE);
                }
            }
        } else if (id == R.id.nav_school || id == R.id.grades_menu_item) {
            if (mFragmentHelper != FragmentHelper.GRADES) {
                mHistoryList.add(mFragmentHelper);
                mFragmentHelper = FragmentHelper.GRADES;
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main, mGradesFragment)
                        .commit();
                mToolbar.setTitle(R.string.nav_menu_school);
                mNavigationView.setCheckedItem(R.id.nav_school);
                mButtonNavigationView.setSelectedItemId(R.id.grades_menu_item);
                if (mButtonNavigationView.getVisibility() == View.GONE) {
                    mButtonNavigationView.setVisibility(View.VISIBLE);
                }
            }
        } else if (id == R.id.nav_kardex) {
            if (mFragmentHelper != FragmentHelper.KARDEX) {
                mHistoryList.add(mFragmentHelper);
                mFragmentHelper = FragmentHelper.KARDEX;
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_main, mKardexFragment)
                        .commit();
                mToolbar.setTitle(R.string.nav_menu_kardex);
                mButtonNavigationView.setVisibility(View.GONE);
            }
        } else if (id == R.id.nav_calendar || id == R.id.calendar_menu_item) {
            /*startActivity(new Intent(Intent.ACTION_VIEW)
                    .setData(Uri.parse(mSession.getSession().getConfig().getCalendarAddress())));*/
        }else if (id == R.id.nav_settings) {
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
        builder.setMessage(getString(R.string.exit_confirmation_message))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.yes_option), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mSession.logout();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    }
                })
                .setNegativeButton(getString(R.string.no_option), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
