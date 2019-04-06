package mx.edu.upqroo.kristenandroid.ui.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.helpers.FirebaseNotificationsHelper;
import mx.edu.upqroo.kristenandroid.managers.PreferencesManager;
import mx.edu.upqroo.kristenandroid.managers.SessionManager;
import mx.edu.upqroo.kristenandroid.data.models.NotificationLoaded;
import mx.edu.upqroo.kristenandroid.data.models.SessionLoaded;
import mx.edu.upqroo.kristenandroid.data.repositories.UserInformationRepository;
import mx.edu.upqroo.kristenandroid.api.sie.messages.LoginMessage;

public class LoadActivity extends ThemeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        // think about a way to kill a session if x time has passed
        SessionLoaded sessionLoaded = PreferencesManager.getInstance().loadSession();
        if (!TextUtils.isEmpty(sessionLoaded.getUser()) ||
                !TextUtils.isEmpty(sessionLoaded.getPassword())) {
            Runnable loadSession = () -> {
                SessionManager.getInstance().createNewSession(
                        UserInformationRepository.getInstance(getApplication())
                                .getUserInformationNotLive(sessionLoaded.getUser()));

                NotificationLoaded notificationLoaded = PreferencesManager
                        .getInstance()
                        .loadNotificationsPreference();

                // Load of the notification preferences
                if (notificationLoaded.isGeneral()) {
                    FirebaseNotificationsHelper
                            .SubscribeNotifications(SessionManager.getInstance().getSession()
                                    .getConfig()
                                    .getGeneralTopic());
                }
                if (notificationLoaded.isCareer()) {
                    FirebaseNotificationsHelper
                            .SubscribeNotifications(SessionManager.getInstance().getSession()
                                    .getConfig()
                                    .getUserTopic());
                }

                Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainIntent);
            };
            AsyncTask.execute(loadSession);
        } else {
            SessionManager.getInstance().createDefaultSession();
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageLogin(LoginMessage event) {
        if (event.isResult()) {
            // Insert of the information to the database
            UserInformationRepository.getInstance(getApplication()).insert(event.getStudent());
            // Creation of a new the session in the session helper
            SessionManager.getInstance().createNewSession(event.getStudent());
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
                        .SubscribeNotifications(SessionManager.getInstance().getSession()
                                .getConfig()
                                .getGeneralTopic());
            }
            if (notificationLoaded.isCareer()) {
                FirebaseNotificationsHelper
                        .SubscribeNotifications(SessionManager.getInstance().getSession()
                                .getConfig()
                                .getUserTopic());
            }
        } else {
            // When there is no one logged in then a create a default session
            SessionManager.getInstance().createDefaultSession();
            // If there is no one logged in i have to delete the User information data
            UserInformationRepository.getInstance(getApplication()).deleteAll();
        }
        // Then i start the main activity
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
        EventBus.getDefault().unregister(this);
    }
}
