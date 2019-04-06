package mx.edu.upqroo.kristenandroid.managers;

import android.app.Application;

import mx.edu.upqroo.kristenandroid.data.models.Config;
import mx.edu.upqroo.kristenandroid.data.database.entities.UserInformation;
import mx.edu.upqroo.kristenandroid.helpers.FirebaseNotificationsHelper;
import mx.edu.upqroo.kristenandroid.services.sie.SieApiServices;
import mx.edu.upqroo.kristenandroid.widget.DataWidgetManager;

/**
 * <h1>SessionManager</h1>
 * This class contains all the session operations.
 */
public class SessionManager {
    private static SessionManager mInstance;
    private UserInformation mSession;
    static final String SESSION_KEY = "SESSION";
    static final String PASS_KEY = "PASS";
    static final String PREFERENCE_FILE = "PREFERENCE";

    /**
     * Default constructor.
     */
    private SessionManager() {
    }

    /**
     * Get the instance of the class if exists, if not then create it.
     * @return the instance
     */
    public static SessionManager getInstance() {
        if (mInstance == null) {
            mInstance = new SessionManager();
        }
        return mInstance;
    }

    /**
     * Calls the api service that logs in the user.
     * @param studentId Student id
     * @param password Student password
     */
    public void login(String studentId, String password, Application app) {
        SieApiServices.getInstance(app).login(studentId, password);
    }

    /**
     * Create a new session.
     * This should only be called when a login has been made successfully.
     * @param session Session to be created, this has all the information retrieve from the login
     */
    public void createNewSession(UserInformation session) {
        mSession = session;
    }

    public void createDefaultSession() {
        createNewSession(
                new UserInformation("Usuario",
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
                        new Config("GENERAL",
                                "TODAS",
                                "BASEADDRESS",
                                "")));
    }

    /**
     * Destroy the session and unsubscribe the application from the firebase notifications.
     */
    public void logout() {
        FirebaseNotificationsHelper
                .UnsubscribeNotifications(mSession.getConfig()
                        .getGeneralTopic(), mSession.getConfig().getUserTopic());
        mSession = null;
        PreferencesManager.getInstance().clearSession();
    }

    /**
     * Returns the session.
     * @return Session
     */
    public UserInformation getSession() {
        return mSession;
    }

    public boolean sessionAlive() {
        return !mSession.getConfig().getUserToken().equals("");
    }
}
