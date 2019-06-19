package mx.edu.upqroo.kristenandroid.managers

import android.content.Context
import android.content.SharedPreferences

import java.lang.ref.WeakReference

import mx.edu.upqroo.kristenandroid.data.models.NotificationLoaded
import mx.edu.upqroo.kristenandroid.data.models.SessionLoaded
import mx.edu.upqroo.kristenandroid.helpers.FirebaseNotificationsHelper

/**
 * <h1>PreferenceManager</h1>
 * This class handles all the preferences that need to be store in the application.
 */
class PreferencesManager
/**
 * Default constructor.
 */
private constructor() {
    private var mSharedPref: SharedPreferences? = null
    private var mContextWeakReference: WeakReference<Context>? = null

    /**
     * Gets the context.
     * @return context
     */
    val context: Context
        get() = mContextWeakReference!!.get()!!

    /**
     * A context is needed before any operation is made. All the preferences stored are context
     * depending, so only one context should be use.
     * @param contextWeakReference Context where the preferences are going to be stored.
     */
    fun setContext(contextWeakReference: WeakReference<Context>) {
        this.mContextWeakReference = contextWeakReference
        mSharedPref = contextWeakReference.get()?.getSharedPreferences(
                SessionManager.PREFERENCE_FILE, Context.MODE_PRIVATE)
    }

    /**
     * Saves a session, it is usually called when a login was successful in order to be able to keep
     * the session even when the application is closed.
     * @param userId User's id
     * @param password User's password
     */
    fun saveSession(userId: String, password: String) {
        val editor = mSharedPref!!.edit()
        editor.putString(SessionManager.SESSION_KEY, userId)
        editor.putString(SessionManager.PASS_KEY, password)
        editor.apply()
    }

    /**
     * Retrieve the session stored to be able to login again, if the is no session stored then
     * it returns empty values.
     * @return Session values to login again
     */
    fun loadSession(): SessionLoaded {
        val result = SessionLoaded()
        val session = mSharedPref!!.getString(SessionManager.SESSION_KEY, "")
        val pass = mSharedPref!!.getString(SessionManager.PASS_KEY, "")
        if (session != "") {
            result.user = session
            result.password = pass
        }
        return result
    }

    /**
     * Clears any session stored, this is usually called when a logout is made.
     */
    fun clearSession() {
        val editor = mSharedPref!!.edit()
        editor.clear()
        editor.apply()
    }

    /**
     * Save the notifications preferences for the general and user's firebase channel.
     * @param general general channel
     * @param career user's channel
     */
    fun saveNotificationsPreference(general: Boolean, career: Boolean) {
        val editor = mSharedPref!!.edit()
        editor.putBoolean(FirebaseNotificationsHelper.GENERAL_NOTIFICATION_KEY, general)
        editor.putBoolean(FirebaseNotificationsHelper.CAREER_NOTIFICATION_KEY, career)
        editor.apply()
    }

    /**
     * Save the notification preference. This works for both channels.
     * @param channel channel to change
     * @param config configuration value.
     */
    fun saveNotificationPreference(channel: String, config: Boolean) {
        val editor = mSharedPref!!.edit()
        if (channel == FirebaseNotificationsHelper.GENERAL_NOTIFICATION_KEY) {
            editor.putBoolean(FirebaseNotificationsHelper.GENERAL_NOTIFICATION_KEY, config)
            if (config) {
                FirebaseNotificationsHelper
                        .subscribeNotifications(SessionManager
                                .instance.session.config.generalTopic)
            } else {
                FirebaseNotificationsHelper
                        .unsubscribeNotifications(SessionManager
                                .instance.session.config.generalTopic)
            }
        } else if (channel == FirebaseNotificationsHelper.CAREER_NOTIFICATION_KEY) {
            editor.putBoolean(FirebaseNotificationsHelper.CAREER_NOTIFICATION_KEY, config)
            if (config) {
                FirebaseNotificationsHelper
                        .subscribeNotifications(SessionManager
                                .instance.session.config.userTopic)
            } else {
                FirebaseNotificationsHelper
                        .unsubscribeNotifications(SessionManager
                                .instance.session.config.generalTopic)
            }
        }
        editor.apply()
    }

    /**
     * Loads the notifications preferences.
     * @return Notifications preferences
     */
    fun loadNotificationsPreference(): NotificationLoaded {
        val result = NotificationLoaded()
        result.isGeneral = mSharedPref!!.getBoolean(
                FirebaseNotificationsHelper.GENERAL_NOTIFICATION_KEY, true)
        result.isCareer = mSharedPref!!.getBoolean(
                FirebaseNotificationsHelper.CAREER_NOTIFICATION_KEY, true)
        return result
    }

    /**
     * Saves the configuration of the theme selected.
     * @param pref boolean with the preference
     */
    fun saveDarkThemeConfig(pref: Boolean) {
        val editor = mSharedPref!!.edit()
        editor.putBoolean("DARK_THEME", pref)
        editor.apply()
    }

    /**
     * Loads the dark theme configuration
     * @return a boolean with the configuration
     */
    fun loadDarkThemeConfig(): Boolean {
        return mSharedPref!!.getBoolean("DARK_THEME", false)
    }

    companion object {
        private var mInstance: PreferencesManager? = null

        /**
         * Get the instance of the class if exists, if not then create it.
         * @return the instance
         */
        val instance: PreferencesManager
            get() {
                if (mInstance == null) {
                    mInstance = PreferencesManager()
                }
                return mInstance as PreferencesManager
            }
    }
}
