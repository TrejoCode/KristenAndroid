package mx.edu.upqroo.kristenandroid

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.crashlytics.android.Crashlytics
import com.google.firebase.analytics.FirebaseAnalytics
import io.fabric.sdk.android.Fabric
import mx.edu.upqroo.kristenandroid.data.database.KristenRoomDatabase
import mx.edu.upqroo.kristenandroid.helpers.NotificationHelper
import mx.edu.upqroo.kristenandroid.managers.PreferencesManager
import java.lang.ref.WeakReference

class Application : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        appContext = WeakReference(applicationContext)
        PreferencesManager.instance.setContext(appContext!!)

        val fabric = Fabric.Builder(this)
                .kits(Crashlytics())
                .debuggable(false)
                .build()
        Fabric.with(fabric)

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        NotificationHelper.instance()

        KristenRoomDatabase.getInstance(this)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object {
        private val TAG = Application::class.java.simpleName
        var firebaseAnalytics: FirebaseAnalytics? = null
            private set
        var appContext: WeakReference<Context>? = null
            private set
    }

}
