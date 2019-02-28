package mx.edu.upqroo.kristenandroid;

import android.content.Context;
import android.text.TextUtils;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.analytics.FirebaseAnalytics;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;

import io.fabric.sdk.android.Fabric;
import mx.edu.upqroo.kristenandroid.common.FirebaseNotificationsHelper;
import mx.edu.upqroo.kristenandroid.common.NotificationHelper;
import mx.edu.upqroo.kristenandroid.common.PreferencesManager;
import mx.edu.upqroo.kristenandroid.common.SessionHelper;
import mx.edu.upqroo.kristenandroid.models.NotificationLoaded;
import mx.edu.upqroo.kristenandroid.models.SessionLoaded;
import mx.edu.upqroo.kristenandroid.services.sie.messages.LoginMessage;

public class Application extends android.app.Application {
    /* CONSTANTS */
    private static final String TAG = Application.class.getSimpleName();
    private static FirebaseAnalytics mFirebaseAnalytics;


    /* VARIABLES */
    private static WeakReference<Context> mContext;
    private PreferencesManager mPrefManager;

    public void onCreate() {
        super.onCreate();
        mContext = new WeakReference<>(getApplicationContext());
        mPrefManager = PreferencesManager.getInstance();
        mPrefManager.setContext(mContext);

        final Fabric fabric = new Fabric.Builder(this)
                .kits(new Crashlytics())
                .debuggable(false)
                .build();
        Fabric.with(fabric);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        NotificationHelper.GetInstance();

        SessionLoaded sessionLoaded = mPrefManager.loadSession();
        if (!TextUtils.isEmpty(sessionLoaded.getUser()) ||
                !TextUtils.isEmpty(sessionLoaded.getPassword())) {
            EventBus.getDefault().register(this);
            SessionHelper.getInstance().login(sessionLoaded.getUser(), sessionLoaded.getPassword());
        } else {
            SessionHelper.getInstance().createDefaultSession();
        }
    }

    public static WeakReference<Context> getAppContext() {
        return mContext;
    }

    public static FirebaseAnalytics getFirebaseAnalytics() {
        return mFirebaseAnalytics;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageLogin(LoginMessage event) {
        if (event.isResult()) {
            SessionHelper.getInstance().createNewSession(event.getStudent());
            mPrefManager.saveSession(event.getStudent().getUserId(),
                    event.getStudent().getPassword());

            NotificationLoaded notificationLoaded = PreferencesManager
                    .getInstance()
                    .loadNotificationsPreference();
            if (notificationLoaded.isGeneral()) {
                FirebaseNotificationsHelper
                        .SubscribeNotifications(SessionHelper.getInstance().getSession()
                                .getConfig()
                                .getGeneralTopic());
            }
            if (notificationLoaded.isCareer()) {
                FirebaseNotificationsHelper
                        .SubscribeNotifications(SessionHelper.getInstance().getSession()
                                .getConfig()
                                .getUserTopic());
            }
        } else {
            SessionHelper.getInstance().createDefaultSession();
        }
        EventBus.getDefault().unregister(this);
    }
}
