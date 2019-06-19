package mx.edu.upqroo.kristenandroid.managers

import android.app.Application
import mx.edu.upqroo.kristenandroid.api.sie.SieApiServices
import mx.edu.upqroo.kristenandroid.data.database.entities.UserInformation
import mx.edu.upqroo.kristenandroid.data.models.Config
import mx.edu.upqroo.kristenandroid.helpers.FirebaseNotificationsHelper

/**
 * <h1>SessionManager</h1>
 * This class contains all the session operations.
 */
class SessionManager
/**
 * Default constructor.
 */
private constructor() {
    /**
     * Returns the session.
     * @return Session
     */
    lateinit var session: UserInformation
        private set

    /**
     * Calls the api service that logs in the user.
     * @param studentId Student id
     * @param password Student password
     */
    fun login(studentId: String, password: String, app: Application) {
        SieApiServices.getInstance(app).login(studentId, password)
    }

    /**
     * Create a new session.
     * This should only be called when a login has been made successfully.
     * @param session Session to be created, this has all the information retrieve from the login
     */
    fun createNewSession(session: UserInformation) {
        this.session = session
    }

    fun createDefaultSession() {
        createNewSession(
                UserInformation("Usuario",
                        "99",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        Config("GENERAL",
                                "TODAS",
                                "BASEADDRESS",
                                "")))
    }

    /**
     * Destroy the session and unsubscribe the application from the firebase notifications.
     */
    fun logout() {
        FirebaseNotificationsHelper
                .unsubscribeNotifications(session.config
                        .generalTopic, session.config.userTopic)
        createDefaultSession()
        PreferencesManager.instance.clearSession()
    }

    fun sessionAlive(): Boolean {
        return session.config.userToken != ""
    }

    companion object {
        private var mInstance: SessionManager? = null
        internal const val SESSION_KEY = "SESSION"
        internal const val PASS_KEY = "PASS"
        internal const val PREFERENCE_FILE = "PREFERENCE"

        /**
         * Get the instance of the class if exists, if not then create it.
         * @return the instance
         */
        val instance: SessionManager
            get() {
                if (mInstance == null) {
                    mInstance = SessionManager()
                }
                return mInstance as SessionManager
            }
    }
}
