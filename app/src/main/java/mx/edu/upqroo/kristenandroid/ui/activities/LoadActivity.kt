package mx.edu.upqroo.kristenandroid.ui.activities

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.text.TextUtils
import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.api.sie.messages.LoginMessage
import mx.edu.upqroo.kristenandroid.data.repositories.UserInformationRepository
import mx.edu.upqroo.kristenandroid.helpers.FirebaseNotificationsHelper
import mx.edu.upqroo.kristenandroid.managers.PreferencesManager
import mx.edu.upqroo.kristenandroid.managers.SessionManager
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class LoadActivity : ThemeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)

        // think about a way to kill a session if x time has passed
        val sessionLoaded = PreferencesManager.instance.loadSession()
        if (!TextUtils.isEmpty(sessionLoaded.user) || !TextUtils.isEmpty(sessionLoaded.password)) {
            val loadSession = {
                SessionManager.instance.createNewSession(
                        UserInformationRepository.getInstance(application)
                                .getUserInformationNotLive(sessionLoaded.user))

                val notificationLoaded = PreferencesManager
                        .instance
                        .loadNotificationsPreference()

                // Load of the notification preferences
                if (notificationLoaded.isGeneral) {
                    FirebaseNotificationsHelper
                            .SubscribeNotifications(SessionManager.instance.session
                                    .config
                                    .generalTopic)
                }
                if (notificationLoaded.isCareer) {
                    FirebaseNotificationsHelper
                            .SubscribeNotifications(SessionManager.instance.session
                                    .config
                                    .userTopic)
                }

                val mainIntent = Intent(applicationContext, MainActivity::class.java)
                startActivity(mainIntent)
            }
            AsyncTask.execute(loadSession)
        } else {
            SessionManager.instance.createDefaultSession()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageLogin(event: LoginMessage) {
        if (event.isResult) {
            // Insert of the information to the database
            UserInformationRepository.getInstance(application).insert(event.student)
            // Creation of a new the session in the session helper
            SessionManager.instance.createNewSession(event.student)
            // This may change, now i have the info in the database so i can use that instead of the preference manager
            PreferencesManager.instance.saveSession(event.student.userId,
                    event.student.password)

            // I should take this to the database to
            val notificationLoaded = PreferencesManager
                    .instance
                    .loadNotificationsPreference()

            // Load of the notification preferences
            if (notificationLoaded.isGeneral) {
                FirebaseNotificationsHelper
                        .SubscribeNotifications(SessionManager.instance.session
                                .config
                                .generalTopic)
            }
            if (notificationLoaded.isCareer) {
                FirebaseNotificationsHelper
                        .SubscribeNotifications(SessionManager.instance.session
                                .config
                                .userTopic)
            }
        } else {
            // When there is no one logged in then a create a default session
            SessionManager.instance.createDefaultSession()
            // If there is no one logged in i have to delete the User information data
            UserInformationRepository.getInstance(application).deleteAll()
        }
        // Then i start the main activity
        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)
        EventBus.getDefault().unregister(this)
    }
}
