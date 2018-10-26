package mx.edu.upqroo.kristenandroid.common;

import mx.edu.upqroo.kristenandroid.models.GeneralInfo;
import mx.edu.upqroo.kristenandroid.service.ApiServices;

/**
 * <h1>SessionHelper</h1>
 * This class contains all the session operations.
 */
public class SessionHelper {
    private static SessionHelper mInstance;
    private GeneralInfo mSession;
    static final String SESSION_KEY = "SESSION";
    static final String PASS_KEY = "PASS";
    static final String PREFERENCE_FILE = "PREFERENCE";

    /**
     * Default constructor.
     */
    private SessionHelper() {
    }

    /**
     * Get the instance of the class if exists, if not then create it.
     * @return the instance
     */
    public static SessionHelper getInstance() {
        if (mInstance == null) {
            mInstance = new SessionHelper();
        }
        return mInstance;
    }

    /**
     * Calls the api service that logs in the user.
     * @param studentId Student id
     * @param password Student password
     */
    public void login(String studentId, String password) {
        ApiServices.login(studentId, password);
    }

    /**
     * Create a new session.
     * This should only be called when a login has been made successfully.
     * @param session Session to be created, this has all the information retrieve from the login
     */
    public void createNewSession(GeneralInfo session) {
        mSession = session;
    }

    /**
     * Destroy the session and unsubscribe the application from the firebase notifications.
     */
    public void logout() {
        FirebaseNotificationsHelper
                .UnsubscribeNotifications(mSession.getGeneralTopic(), mSession.getUserTopic());
        mSession = null;
        PreferencesManager.getInstance().clearSession();
    }

    /**
     * Returns the session.
     * @return Session
     */
    public GeneralInfo getSession() {
        return mSession;
    }
}
