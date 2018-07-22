package mx.edu.upqroo.kristenandroid.common;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.ref.WeakReference;

import mx.edu.upqroo.kristenandroid.models.NotificationLoaded;
import mx.edu.upqroo.kristenandroid.models.SessionLoaded;

public class PreferencesManager {
    private static PreferencesManager mInstance;
    private SharedPreferences mSharedPref;

    private PreferencesManager() {
    }

    public static PreferencesManager getInstance() {
        if (mInstance == null) {
            mInstance = new PreferencesManager();
        }
        return mInstance;
    }

    public void setContext(WeakReference<Context> contextWeakReference) {
        mSharedPref = contextWeakReference.get()
                .getSharedPreferences(SessionHelper.PREFERENCE_FILE, Context.MODE_PRIVATE);
    }

    public void saveSession(String a, String b) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putString(SessionHelper.SESSION_KEY, a);
        editor.putString(SessionHelper.PASS_KEY, b);
        editor.apply();
    }

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

    void clearSession() {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.clear();
        editor.apply();
    }

    void saveNotificationsPreference(boolean general, boolean career) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        editor.putBoolean(NotificationsHelper.GENERAL_NOTIFICATION_KEY, general);
        editor.putBoolean(NotificationsHelper.CAREER_NOTIFICATION_KEY, career);
        editor.apply();
    }

    public void saveNotificationPreference(String channel, boolean config) {
        SharedPreferences.Editor editor = mSharedPref.edit();
        if (channel.equals(NotificationsHelper.GENERAL_NOTIFICATION_KEY)) {
            editor.putBoolean(NotificationsHelper.GENERAL_NOTIFICATION_KEY, config);
            if (config) {
                NotificationsHelper
                        .SuscribeNotifications(SessionHelper
                                .getInstance().getSession().getGeneralTopic());
            } else {
                NotificationsHelper
                        .UnsuscribeNotifications(SessionHelper
                                .getInstance().getSession().getGeneralTopic());
            }
        } else if (channel.equals(NotificationsHelper.CAREER_NOTIFICATION_KEY)) {
            editor.putBoolean(NotificationsHelper.CAREER_NOTIFICATION_KEY, config);
            if (config) {
                NotificationsHelper
                        .SuscribeNotifications(SessionHelper
                                .getInstance().getSession().getUserTopic());
            } else {
                NotificationsHelper
                        .UnsuscribeNotifications(SessionHelper
                                .getInstance().getSession().getGeneralTopic());
            }
        }
        editor.apply();
    }

    public NotificationLoaded loadNotificationsPreference() {
        NotificationLoaded result = new NotificationLoaded();
        result.setGeneral(mSharedPref.getBoolean(NotificationsHelper.GENERAL_NOTIFICATION_KEY, true));
        result.setCareer(mSharedPref.getBoolean(NotificationsHelper.CAREER_NOTIFICATION_KEY, true));
        return result;
    }
}
