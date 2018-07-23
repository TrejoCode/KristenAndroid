package mx.edu.upqroo.kristenandroid.common;

import mx.edu.upqroo.kristenandroid.models.GeneralInfo;
import mx.edu.upqroo.kristenandroid.service.ApiServices;

public class SessionHelper {
    private static SessionHelper mInstance;
    private GeneralInfo mSession;
    static final String SESSION_KEY = "SESSION";
    static final String PASS_KEY = "PASS";
    static final String PREFERENCE_FILE = "PREFERENCE";

    private SessionHelper() {
    }

    public static SessionHelper getInstance() {
        if (mInstance == null) {
            mInstance = new SessionHelper();
        }
        return mInstance;
    }

    public void login(String studentId, String password) {
        ApiServices.login(studentId, password);
    }

    public void createNewSession(GeneralInfo session) {
        mSession = session;
    }

    public void logout() {
        NotificationsHelper
                .UnsuscribeNotifications(mSession.getGeneralTopic(), mSession.getUserTopic());
        mSession = null;
        PreferencesManager.getInstance().clearSession();
    }

    public GeneralInfo getSession() {
        return mSession;
    }
}
