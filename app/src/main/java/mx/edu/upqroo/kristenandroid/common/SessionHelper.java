package mx.edu.upqroo.kristenandroid.common;

import mx.edu.upqroo.kristenandroid.activities.MainActivity;
import mx.edu.upqroo.kristenandroid.models.GeneralInfo;

public class SessionHelper {
    private static SessionHelper mInstance;
    private GeneralInfo mSession;
    public static final String SESSION_KEY = "SESSION";
    public static final String PASS_KEY = "PASS";
    public static final String PREFERENCE_FILE = "PREFERENCE";

    private SessionHelper() {
    }

    public static SessionHelper getInstance() {
        if (mInstance == null) {
            mInstance = new SessionHelper();
        }
        return mInstance;
    }

    public void login(String studentId, String password) {
        GeneralInfo session = null;
        if (studentId.equals("1234")) {
            if (password.equals("1234")) {
                //todo here will go the petition to the web service to try to login
                session = new GeneralInfo("Rodrigo", "Pech", "Ing. Software",
                        "201500100", "ISOF-2013 DE 375 CREDITOS", "( )",
                        "295.0", "VIGENTE", "(3153) SEP DIC 1",
                        "00", "(09) (3182) MAY-AGO 18", "pepr970313hqrccd01",
                        "1997-03-13", "SANTA CECILIA", "CANCUN", "77500",
                        "991564564", "9981158454", "rodrigoupech13@gmail.com",
                        "MANO AMIGA", "DIEGO LOVERA", "GENERAL", "SOFTWARE");
            }
        }
        createNewSession(session);
    }

    private void createNewSession(GeneralInfo session) {
        mSession = session;
    }

    public void logout() {
        NotificationsHelper
                .UnsuscribeNotifications(mSession.getGeneralTopic(), mSession.getUserTopic());
        mSession = null;
        MainActivity.clearSession();
    }

    public GeneralInfo getSession() {
        return mSession;
    }
}
