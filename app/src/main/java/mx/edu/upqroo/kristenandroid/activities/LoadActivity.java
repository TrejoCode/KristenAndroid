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
import mx.edu.upqroo.kristenandroid.models.NotificationLoaded;
import mx.edu.upqroo.kristenandroid.models.SessionLoaded;
import mx.edu.upqroo.kristenandroid.services.sie.messages.LoginMessage;

public class LoadActivity extends ThemeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

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
            SessionHelper.getInstance().createNewSession(event.getStudent());
            PreferencesManager.getInstance().saveSession(event.getStudent().getUserId(),
                    event.getStudent().getPassword());

            NotificationLoaded notificationLoaded = PreferencesManager
                    .getInstance()
                    .loadNotificationsPreference();
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
            SessionHelper.getInstance().createDefaultSession();
        }
        startActivity(new Intent(this, MainActivity.class));
        EventBus.getDefault().unregister(this);
    }
}
