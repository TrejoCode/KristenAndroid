package mx.edu.upqroo.kristenandroid.common;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.ref.WeakReference;

import mx.edu.upqroo.kristenandroid.models.NotificationLoaded;
import mx.edu.upqroo.kristenandroid.models.SessionLoaded;

/**
 * <h1>PreferenceManager</h1>
 * This class handles all the preferences that need to be store in the application.
 */
public class PreferencesManager {
    private static PreferencesManager mInstance;
    private SharedPreferences mSharedPref;
    private WeakReference<Context> mContextWeakReference;

    /**
     * Default constructor.
     */
    private PreferencesManager() {
    }

    /**
     * Get the instance of the class if exists, if not then create it.
     * @return the instance
     */
    public static PreferencesManager getInstance() {
        if (mInstance == null) {
            mInstance = new PreferencesManager();
        }
        return mInstance;
    }

    /**
     * A context is needed before any operation is made. All the preferences stored are context
     * depending, so only one context should be use.
     * @param contextWeakReference Context where the preferences are going to be stored.
     */
    public void setContext(WeakReference<Context> contextWeakReference) {
        this.mContextWeakReference = contextWeakReference;
        mSharedPref = contextWeakReference.get()
                .getSharedPreferences(SessionHelper.PREFERENCE_FILE, Context.MODE_PRIVATE);
    }

    /**
     * Gets the context.
     * @return context
     */
    public Context getContext() {
        return mContextWeakReference.get();
    }

    /**
     * Saves a session, it is usually called when a login was successful in order to be able to keep
     * the session even when the application is closed.
     * @param userId User's id
     * @param password User's password
     */
    public void saveSession(String userId, String password) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putString(SessionHelper.SESSION_KEY, userId);
        editor.putString(SessionHelper.PASS_KEY, password);
        editor.apply();
    }

    /**
     * Retrieve the session stored to be able to login again, if the is no session stored then
     * it returns empty values.
     * @return Session values to login again
     */
    public SessionLoaded loadSession() {
        SessionLoaded result = new SessionLoaded();
        String session = mSharedPref.getString(SessionHelper.SESSION_KEY, "");
        String pass = mSharedPref.getString(SessionHelper.PASS_KEY, "");
        if (!session.equals("")) {
            result.setUser(session);
            result.setPassword(pass);
        }
        return result;
    }

    /**
     * Clears any session stored, this is usually called when a logout is made.
     */
    void clearSession() {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.clear();
        editor.apply();
    }

    /**
     * Save the notifications preferences for the general and user's firebase channel.
     * @param general general channel
     * @param career user's channel
     */
    void saveNotificationsPreference(boolean general, boolean career) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putBoolean(FirebaseNotificationsHelper.GENERAL_NOTIFICATION_KEY, general);
        editor.putBoolean(FirebaseNotificationsHelper.CAREER_NOTIFICATION_KEY, career);
        editor.apply();
    }

    /**
     * Save the notification preference. This works for both channels.
     * @param channel channel to change
     * @param config configuration value.
     */
    public void saveNotificationPreference(String channel, boolean config) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        if (channel.equals(FirebaseNotificationsHelper.GENERAL_NOTIFICATION_KEY)) {
            editor.putBoolean(FirebaseNotificationsHelper.GENERAL_NOTIFICATION_KEY, config);
            if (config) {
                FirebaseNotificationsHelper
                        .SubscribeNotifications(SessionHelper
                                .getInstance().getSession().getGeneralTopic());
            } else {
                FirebaseNotificationsHelper
                        .UnsubscribeNotifications(SessionHelper
                                .getInstance().getSession().getGeneralTopic());
            }
        } else if (channel.equals(FirebaseNotificationsHelper.CAREER_NOTIFICATION_KEY)) {
            editor.putBoolean(FirebaseNotificationsHelper.CAREER_NOTIFICATION_KEY, config);
            if (config) {
                FirebaseNotificationsHelper
                        .SubscribeNotifications(SessionHelper
                                .getInstance().getSession().getUserTopic());
            } else {
                FirebaseNotificationsHelper
                        .UnsubscribeNotifications(SessionHelper
                                .getInstance().getSession().getGeneralTopic());
            }
        }
        editor.apply();
    }

    /**
     * Loads the notifications preferences.
     * @return Notifications preferences
     */
    public NotificationLoaded loadNotificationsPreference() {
        NotificationLoaded result = new NotificationLoaded();
        result.setGeneral(mSharedPref.getBoolean(
                FirebaseNotificationsHelper.GENERAL_NOTIFICATION_KEY, true));
        result.setCareer(mSharedPref.getBoolean(
                FirebaseNotificationsHelper.CAREER_NOTIFICATION_KEY, true));
        return result;
    }

    /**
     * Saves the configuration of the theme selected.
     * @param pref boolean with the preference
     */
    public void saveDarkThemeConfig(boolean pref) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putBoolean("DARK_THEME", pref);
        editor.apply();
    }

    /**
     * Loads the dark theme configuration
     * @return a boolean with the configuration
     */
    public boolean loadDarkThemeConfig() {
        return mSharedPref.getBoolean("DARK_THEME", false);
    }
}
