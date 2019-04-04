package mx.edu.upqroo.kristenandroid.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.data.repositories.UserInformationRepository;
import mx.edu.upqroo.kristenandroid.managers.FragmentManager;
import mx.edu.upqroo.kristenandroid.managers.SessionManager;
import mx.edu.upqroo.kristenandroid.services.kristen.KristenApiServices;
import mx.edu.upqroo.kristenandroid.services.kristen.messages.CalendarUrlMessage;
import mx.edu.upqroo.kristenandroid.ui.fragments.CalendarFragment;
import mx.edu.upqroo.kristenandroid.widget.ScheduleWidget;

public class MainActivity extends ThemeActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        BottomNavigationView.OnNavigationItemSelectedListener,
        NavController.OnDestinationChangedListener {
    private Toolbar mToolbar;
    private SessionManager mSession;
    private NavigationView mNavigationView;
    private BottomNavigationView mBottomNavigationView;
    private NavController mNavController;
    private DrawerLayout mDrawerLayout;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSession = SessionManager.getInstance();
        onWidgetUpdateMessage(this);
        //region toolbar setup
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.nav_menu_news);
        setSupportActionBar(mToolbar);
        //endregion
        //region drawer layout setup
        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = findViewById(R.id.nav_view);
        View navHeader = mNavigationView.inflateHeaderView(R.layout.nav_header_news);
        TextView mNavHeaderProfileName = navHeader.findViewById(R.id.text_nav_header_title);
        TextView mNavHeaderProfileEmail = navHeader.findViewById(R.id.text_nav_header_subtitle);
        if (mNavHeaderProfileName != null && mNavHeaderProfileEmail != null) {
            mNavHeaderProfileName.setText(mSession.getSession().getName());
            mNavHeaderProfileEmail.setText(mSession.getSession().getEmail());
        }

        mNavigationView.setNavigationItemSelectedListener(this);

        if (!mSession.sessionAlive()) {
            mNavigationView.getMenu()
                    .getItem(mNavigationView.getMenu().size() - 1)
                    .setTitle(R.string.button_login);
        }
        //endregion
        //region bottom navigation setup
        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        //endregion
        //region nav controller setup
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment);
        mFragmentManager = FragmentManager.NEWS_FRAGMENT;
        mNavController.addOnDestinationChangedListener(this);
        //endregion
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.news, menu);
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
        if (id == R.id.nav_logout) {
            if (mSession.sessionAlive()) {
                showLogoutDialog();
            } else {
                Intent loginIntent = new Intent(this, LoginActivity.class);
                loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(loginIntent);
            }
        } else if ((id == R.id.nav_news || id == R.id.news_menu_item)
                && mFragmentManager != FragmentManager.NEWS_FRAGMENT) {

            mNavController.navigate(R.id.newsListFragment);

        } else if (id == R.id.nav_notices
                && mFragmentManager != FragmentManager.NOTICES_FRAGMENT) {

            mNavController.navigate(R.id.noticesFragment);

        } else if ((id == R.id.nav_calendar || id == R.id.calendar_menu_item)
                && mFragmentManager != FragmentManager.CALENDAR_FRAGMENT) {

            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this);
            }
            KristenApiServices.getInstance().getCalendarUrl();

        } else if (id == R.id.nav_settings) {

            mNavController.navigate(R.id.settingsActivity);

        } else {

            if (!mSession.sessionAlive()) {
                Snackbar.make(findViewById(R.id.bottom_navigation),
                        R.string.login_message,
                        Snackbar.LENGTH_LONG)
                        .setAction(R.string.button_login, v -> startActivity(
                                new Intent(getApplicationContext(), LoginActivity.class)))
                        .show();

            } else {

                if (id == R.id.nav_user
                        && mFragmentManager != FragmentManager.USER_FRAGMENT) {

                    mNavController.navigate(R.id.userFragment);

                } else if ((id == R.id.nav_schedule || id == R.id.schedule_menu_item)
                        && mFragmentManager != FragmentManager.SCHEDULE_FRAGMENT) {

                    mNavController.navigate(R.id.scheduleFragment);

                } else if ((id == R.id.nav_grades || id == R.id.grades_menu_item)
                        && mFragmentManager != FragmentManager.GRADES_FRAGMENT) {

                    mNavController.navigate(R.id.gradesFragment);

                } else if (id == R.id.nav_kardex
                        && mFragmentManager != FragmentManager.KARDEX_FRAGMENT) {

                    mNavController.navigate(R.id.kardexFragment);

                }
            }
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onDestinationChanged(@NonNull NavController controller,
                                     @NonNull NavDestination destination,
                                     @Nullable Bundle arguments) {
        mToolbar.setTitle(destination.getLabel());
        switch (destination.getId()) {
            case R.id.newsListFragment:
                mNavigationView.setCheckedItem(R.id.nav_news);
                mBottomNavigationView.getMenu().getItem(0).setChecked(true);
                mBottomNavigationView.setVisibility(View.VISIBLE);

                mFragmentManager = FragmentManager.NEWS_FRAGMENT;
                break;
            case R.id.userFragment:
                mNavigationView.setCheckedItem(R.id.nav_user);
                mBottomNavigationView.setVisibility(View.GONE);

                mFragmentManager = FragmentManager.USER_FRAGMENT;
                break;
            case R.id.scheduleFragment:
                mNavigationView.setCheckedItem(R.id.nav_schedule);
                mBottomNavigationView.getMenu().getItem(1).setChecked(true);
                mBottomNavigationView.setVisibility(View.VISIBLE);

                mFragmentManager = FragmentManager.SCHEDULE_FRAGMENT;
                break;
            case R.id.gradesFragment:
                mNavigationView.setCheckedItem(R.id.nav_grades);
                mBottomNavigationView.getMenu().getItem(2).setChecked(true);
                mBottomNavigationView.setVisibility(View.VISIBLE);

                mFragmentManager = FragmentManager.GRADES_FRAGMENT;
                break;
            case R.id.noticesFragment:
                mNavigationView.setCheckedItem(R.id.nav_notices);
                mBottomNavigationView.setVisibility(View.GONE);

                mFragmentManager = FragmentManager.NOTICES_FRAGMENT;
                break;
            case R.id.kardexFragment:
                mNavigationView.setCheckedItem(R.id.nav_kardex);
                mBottomNavigationView.setVisibility(View.GONE);

                mFragmentManager = FragmentManager.KARDEX_FRAGMENT;
                break;
            case R.id.calendarFragment:
                mNavigationView.setCheckedItem(R.id.nav_calendar);
                mBottomNavigationView.getMenu().getItem(3).setChecked(true);
                mBottomNavigationView.setVisibility(View.VISIBLE);

                mFragmentManager = FragmentManager.CALENDAR_FRAGMENT;
                break;
        }
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
                .setPositiveButton(getString(R.string.yes_option), (dialog, id) -> {
                    mSession.logout();
                    UserInformationRepository.getInstance(getApplication()).deleteAll();
                    Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                    loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(loginIntent);
                })
                .setNegativeButton(getString(R.string.no_option), (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void calendarServiceResponse(CalendarUrlMessage message) {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        if (!message.getCalendarUrl().isEmpty()) {
            Bundle bundle = new Bundle();
            bundle.putString(CalendarFragment.URL_KEY, message.getCalendarUrl());
            mNavController.navigate(R.id.calendarFragment, bundle);
        }
    }
}
