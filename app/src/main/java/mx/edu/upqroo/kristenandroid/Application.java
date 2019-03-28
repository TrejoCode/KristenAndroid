package mx.edu.upqroo.kristenandroid;

import android.content.Context;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.lang.ref.WeakReference;

import io.fabric.sdk.android.Fabric;
import mx.edu.upqroo.kristenandroid.common.NotificationHelper;
import mx.edu.upqroo.kristenandroid.common.PreferencesManager;
import mx.edu.upqroo.kristenandroid.database.KristenRoomDatabase;

public class Application extends android.app.Application {
    private static final String TAG = Application.class.getSimpleName();
    private static FirebaseAnalytics mFirebaseAnalytics;
    private static WeakReference<Context> mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = new WeakReference<>(getApplicationContext());
        PreferencesManager.getInstance().setContext(mContext);

        final Fabric fabric = new Fabric.Builder(this)
                .kits(new Crashlytics())
                .debuggable(false)
                .build();
        Fabric.with(fabric);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        NotificationHelper.GetInstance();

        KristenRoomDatabase.getInstance(this);
    }

    public static WeakReference<Context> getAppContext() {
        return mContext;
    }

    public static FirebaseAnalytics getFirebaseAnalytics() {
        return mFirebaseAnalytics;
    }

}
