package mx.edu.upqroo.kristenandroid.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
import androidx.navigation.ui.NavigationUI;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.common.SessionHelper;
import mx.edu.upqroo.kristenandroid.fragments.CalendarFragment;
import mx.edu.upqroo.kristenandroid.services.kristen.KristenApiServices;
import mx.edu.upqroo.kristenandroid.services.kristen.messages.CalendarUrlMessage;
import mx.edu.upqroo.kristenandroid.widget.ScheduleWidget;

public class MainActivity extends ThemeActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        BottomNavigationView.OnNavigationItemSelectedListener,
        NavController.OnDestinationChangedListener {
    private Toolbar mToolbar;
    private SessionHelper mSession;
    private NavigationView mNavigationView;
    private BottomNavigationView mBottomNavigationView;
    private NavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSession = SessionHelper.getInstance();
        onWidgetUpdateMessage(this);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.nav_menu_news);
        setSupportActionBar(mToolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);

        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        if (!mSession.sessionAlive()) {
            mNavigationView.getMenu().getItem(7).setTitle("Login");
        }
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment);

        mNavController.addOnDestinationChangedListener(this);
        mNavController.navigate(R.id.newsListFragment);
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
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            mNavController.popBackStack();
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
        if (id == R.id.nav_logout) {
            if (mSession.sessionAlive()) {
                showLogoutDialog();
            } else {
                startActivity(new Intent(this, LoginActivity.class));
            }
        } else if (id == R.id.nav_news || id == R.id.news_menu_item) {
            mNavController.navigate(R.id.newsListFragment);
        }  else if (id == R.id.nav_calendar || id == R.id.calendar_menu_item) {
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this);
            }
            KristenApiServices.getCalendarUrl();
        } else if (id == R.id.nav_settings) {
            mNavController.navigate(R.id.settingsActivity);
        } else {
            if (!mSession.sessionAlive()) {
                Snackbar.make(findViewById(R.id.bottom_navigation),
                        "Inicia sesión para ver a esta información",
                        Snackbar.LENGTH_LONG)
                        .setAction("Login", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            }
                        })
                        .show();
            } else {
                if (id == R.id.nav_user) {
                    mNavController.navigate(R.id.userFragment);
                } else if (id == R.id.nav_schedule || id == R.id.schedule_menu_item) {
                    mNavController.navigate(R.id.scheduleFragment);
                } else if (id == R.id.nav_school || id == R.id.grades_menu_item) {
                    mNavController.navigate(R.id.gradesFragment);
                } else if (id == R.id.nav_kardex) {
                    mNavController.navigate(R.id.kardexFragment);
                }
            }
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
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
                break;
            case R.id.userFragment:
                mNavigationView.setCheckedItem(R.id.nav_user);
                mBottomNavigationView.setVisibility(View.GONE);
                break;
            case R.id.scheduleFragment:
                mNavigationView.setCheckedItem(R.id.nav_schedule);
                mBottomNavigationView.getMenu().getItem(1).setChecked(true);
                mBottomNavigationView.setVisibility(View.VISIBLE);
                break;
            case R.id.gradesFragment:
                mNavigationView.setCheckedItem(R.id.nav_school);
                mBottomNavigationView.getMenu().getItem(2).setChecked(true);
                mBottomNavigationView.setVisibility(View.VISIBLE);
                break;
            case R.id.kardexFragment:
                mNavigationView.setCheckedItem(R.id.nav_kardex);
                mBottomNavigationView.setVisibility(View.GONE);
                break;
            case R.id.calendarFragment:
                mNavigationView.setCheckedItem(R.id.nav_calendar);
                mBottomNavigationView.getMenu().getItem(3).setChecked(true);
                mBottomNavigationView.setVisibility(View.VISIBLE);
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
