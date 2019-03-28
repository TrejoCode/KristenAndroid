package mx.edu.upqroo.kristenandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.common.FirebaseNotificationsHelper;
import mx.edu.upqroo.kristenandroid.common.PreferencesManager;
import mx.edu.upqroo.kristenandroid.common.SessionHelper;
import mx.edu.upqroo.kristenandroid.database.entities.NotificationLoaded;
import mx.edu.upqroo.kristenandroid.database.entities.SessionLoaded;
import mx.edu.upqroo.kristenandroid.repositories.UserInformationRepository;
import mx.edu.upqroo.kristenandroid.services.sie.messages.LoginMessage;

public class LoadActivity extends ThemeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        // i should change this to load the session from the database, and maybe think about a way to kill a session if x time has passed
        SessionLoaded sessionLoaded = PreferencesManager.getInstance().loadSession();
        if (!TextUtils.isEmpty(sessionLoaded.getUser()) ||
                !TextUtils.isEmpty(sessionLoaded.getPassword())) {
            EventBus.getDefault().register(this);
            SessionHelper.getInstance().login(sessionLoaded.getUser(), sessionLoaded.getPassword());
        } else {
            SessionHelper.getInstance().createDefaultSession();
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageLogin(LoginMessage event) {
        if (event.isResult()) {
            // Insert of the information to the database
            UserInformationRepository.getInstance(getApplication()).insert(event.getStudent());
            // Creation of a new the session in the session helper
            SessionHelper.getInstance().createNewSession(event.getStudent());
            // This may change, now i have the info in the database so i can use that instead of the preference manager
            PreferencesManager.getInstance().saveSession(event.getStudent().getUserId(),
                    event.getStudent().getPassword());

            // I should take this to the database to
            NotificationLoaded notificationLoaded = PreferencesManager
                    .getInstance()
                    .loadNotificationsPreference();

            // Load of the notification preferences
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
            // When there is no one logged in then a create a default session
            SessionHelper.getInstance().createDefaultSession();
            // If there is no one logged in i have to delete the User information data
            UserInformationRepository.getInstance(getApplication()).deleteAll();
        }
        // Then i start the main activity
        Intent mainIntent = new Intent(this, MainActivity.class);
        //+mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(mainIntent);
        EventBus.getDefault().unregister(this);
    }
}
