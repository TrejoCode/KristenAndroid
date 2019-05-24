package mx.edu.upqroo.kristenandroid.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.CheckBox
import android.widget.Switch

import androidx.appcompat.widget.Toolbar
import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.helpers.FirebaseNotificationsHelper
import mx.edu.upqroo.kristenandroid.managers.PreferencesManager
import mx.edu.upqroo.kristenandroid.data.models.NotificationLoaded

class SettingsActivity : ThemeActivity() {

    private lateinit var mPrefManager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val mToolbar = findViewById<Toolbar>(R.id.toolbarSettings)
        setSupportActionBar(mToolbar)
        supportActionBar!!.title = getString(R.string.action_settings)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val mCheckBoxGeneral = findViewById<CheckBox>(R.id.check_general_notification)
        val mCheckBoxCareer = findViewById<CheckBox>(R.id.check_career_notifications)
        val mSwitchDarkTheme = findViewById<Switch>(R.id.switch_dark_theme)

        mPrefManager = PreferencesManager.instance

        val notificationLoaded = mPrefManager.loadNotificationsPreference()
        mCheckBoxGeneral.isChecked = notificationLoaded.isGeneral
        mCheckBoxCareer.isChecked = notificationLoaded.isCareer
        mSwitchDarkTheme.isChecked = mPrefManager.loadDarkThemeConfig()

        mCheckBoxGeneral.setOnCheckedChangeListener { _, b ->
            mPrefManager.saveNotificationPreference(
                    FirebaseNotificationsHelper.GENERAL_NOTIFICATION_KEY, b)
        }

        mCheckBoxCareer.setOnCheckedChangeListener { _, b ->
            mPrefManager.saveNotificationPreference(
                    FirebaseNotificationsHelper.CAREER_NOTIFICATION_KEY, b)
        }

        mSwitchDarkTheme.setOnCheckedChangeListener { _, isChecked ->
            mPrefManager.saveDarkThemeConfig(isChecked)
            applyTheme()
            HAS_THEME_CHANGED = true
            startActivity(Intent(applicationContext, SettingsActivity::class.java))
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == android.R.id.home) {
            onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (HAS_THEME_CHANGED) {
            val mainIntent = Intent(this, MainActivity::class.java)
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(mainIntent)
        } else {
            super.onBackPressed()
        }
    }
}
