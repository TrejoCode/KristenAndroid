package mx.edu.upqroo.kristenandroid.ui.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.appcompat.widget.LinearLayoutCompat;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.helpers.FirebaseNotificationsHelper;
import mx.edu.upqroo.kristenandroid.managers.PreferencesManager;
import mx.edu.upqroo.kristenandroid.managers.SessionManager;
import mx.edu.upqroo.kristenandroid.data.models.NotificationLoaded;
import mx.edu.upqroo.kristenandroid.data.repositories.UserInformationRepository;
import mx.edu.upqroo.kristenandroid.api.sie.messages.LoginMessage;

public class LoginActivity extends ThemeActivity {
    private TextView mUserId;
    private TextView mPassword;
    private LinearLayoutCompat mLinearOverlay;
    private Button mButtonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mLinearOverlay = findViewById(R.id.linear_overlay_login);
        mLinearOverlay.setVisibility(View.VISIBLE);
        mUserId = findViewById(R.id.field_user_id);
        mPassword = findViewById(R.id.field_password);
        mButtonLogin = findViewById(R.id.button_login);
        final Button mButtonLoginNoSession = findViewById(R.id.button_login_no_session);
        mLinearOverlay.setVisibility(View.GONE);

        mButtonLogin.setOnClickListener(v -> {
            mUserId.setEnabled(false);
            mPassword.setEnabled(false);
            mLinearOverlay.setVisibility(View.VISIBLE);
            mButtonLogin.setClickable(false);
            SessionManager.getInstance().login(
                    mUserId.getText().toString(),
                    mPassword.getText().toString(),
                    getApplication());
        });

        mButtonLoginNoSession.setOnClickListener(v -> {
            SessionManager.getInstance().createDefaultSession();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageLogin(LoginMessage event) {
        if (event.isResult()) {
            UserInformationRepository.getInstance(getApplication()).insert(event.getStudent());
            SessionManager.getInstance().createNewSession(event.getStudent());
            PreferencesManager.getInstance().saveSession(event.getStudent().getUserId(),
                    event.getStudent().getPassword());

            NotificationLoaded notificationLoaded = PreferencesManager
                    .getInstance()
                    .loadNotificationsPreference();
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
            Intent mainIntent = new Intent(this, MainActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);
        } else {
            Toast.makeText(this, event.getMessage(), Toast.LENGTH_LONG).show();
        }
        mUserId.setEnabled(true);
        mPassword.setEnabled(true);
        mButtonLogin.setClickable(true);
        mLinearOverlay.setVisibility(View.GONE);
    }
}
