package mx.edu.upqroo.kristenandroid.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.appcompat.widget.LinearLayoutCompat;
import io.fabric.sdk.android.Fabric;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.common.FirebaseNotificationsHelper;
import mx.edu.upqroo.kristenandroid.common.PreferencesManager;
import mx.edu.upqroo.kristenandroid.common.SessionHelper;
import mx.edu.upqroo.kristenandroid.models.NotificationLoaded;
import mx.edu.upqroo.kristenandroid.services.sie.messages.LoginMessage;

public class LoginActivity extends ThemeActivity {
    private TextView mUserId;
    private TextView mPassword;
    private LinearLayoutCompat mLinearOverlay;
    private Button mButtonLogin;
    private Button mButtonLoginNoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLinearOverlay = findViewById(R.id.linear_overlay_login);
        mLinearOverlay.setVisibility(View.VISIBLE);
        mUserId = findViewById(R.id.field_user_id);
        mPassword = findViewById(R.id.field_password);
        mButtonLogin = findViewById(R.id.button_login);
        mButtonLoginNoSession = findViewById(R.id.button_login_no_session);
        mLinearOverlay.setVisibility(View.GONE);

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserId.setEnabled(false);
                mPassword.setEnabled(false);
                mLinearOverlay.setVisibility(View.VISIBLE);
                SessionHelper.getInstance().login(
                        mUserId.getText().toString(),
                        mPassword.getText().toString());
                mButtonLogin.setClickable(false);
            }
        });

        mButtonLoginNoSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionHelper.getInstance().createDefaultSession();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
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
            startActivity(new Intent(this, MainActivity.class));
        } else {
            Toast.makeText(this, event.getMessage(), Toast.LENGTH_LONG).show();
        }
        mUserId.setEnabled(true);
        mPassword.setEnabled(true);
        mButtonLogin.setClickable(true);
        mLinearOverlay.setVisibility(View.GONE);
    }
}
