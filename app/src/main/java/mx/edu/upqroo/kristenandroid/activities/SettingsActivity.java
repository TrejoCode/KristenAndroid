package mx.edu.upqroo.kristenandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.common.NotificationsHelper;
import mx.edu.upqroo.kristenandroid.common.PreferencesManager;
import mx.edu.upqroo.kristenandroid.models.NotificationLoaded;

public class SettingsActivity extends UpqrooActivity {

    private PreferencesManager mPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applyTheme();
        setContentView(R.layout.activity_settings);

        Toolbar mToolbar = findViewById(R.id.toolbarSettings);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(getString(R.string.action_settings));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CheckBox mCheckBoxGeneral = findViewById(R.id.check_general_notification);
        CheckBox mCheckBoxCareer = findViewById(R.id.check_career_notifications);
        Switch mSwitchDarkTheme = findViewById(R.id.switch_dark_theme);

        mPrefManager = PreferencesManager.getInstance();

        NotificationLoaded notificationLoaded = mPrefManager.loadNotificationsPreference();
        mCheckBoxGeneral.setChecked(notificationLoaded.isGeneral());
        mCheckBoxCareer.setChecked(notificationLoaded.isCareer());
        mSwitchDarkTheme.setChecked(mPrefManager.loadDarkThemeConfig());

        mCheckBoxGeneral.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mPrefManager.saveNotificationPreference(
                        NotificationsHelper.GENERAL_NOTIFICATION_KEY, b);
            }
        });

        mCheckBoxCareer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mPrefManager.saveNotificationPreference(
                        NotificationsHelper.CAREER_NOTIFICATION_KEY, b);
            }
        });

        mSwitchDarkTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPrefManager.saveDarkThemeConfig(isChecked);
                applyTheme();
                MainActivity.HAS_THEME_CHANGED = true;
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (MainActivity.HAS_THEME_CHANGED) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            super.onBackPressed();
        }
    }
}
