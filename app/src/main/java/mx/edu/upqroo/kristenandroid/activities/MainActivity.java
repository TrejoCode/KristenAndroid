package mx.edu.upqroo.kristenandroid.activities;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;

import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.common.NotificationsHelper;
import mx.edu.upqroo.kristenandroid.common.PreferencesManager;
import mx.edu.upqroo.kristenandroid.common.Serializer;
import mx.edu.upqroo.kristenandroid.common.SessionHelper;
import mx.edu.upqroo.kristenandroid.models.GeneralInfo;
import mx.edu.upqroo.kristenandroid.models.SessionLoaded;
import mx.edu.upqroo.kristenandroid.service.ApiServices;
import mx.edu.upqroo.kristenandroid.service.messages.LoginMessage;

public class MainActivity extends AppCompatActivity {
    private SessionHelper mSessionHelper;
    private PreferencesManager mPrefManager;
    private TextView mUserId;
    private TextView mPassword;
    private LinearLayoutCompat mLinearOverlay;
    private Button mButtonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = findViewById(R.id.toolbarLogin);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("UPQROO");

        mLinearOverlay = findViewById(R.id.linear_overlay_login);
        mLinearOverlay.setVisibility(View.VISIBLE);

        WeakReference<Context> mContextWeakReference = new WeakReference<>(getApplicationContext());

        mSessionHelper = SessionHelper.getInstance();
        mPrefManager = PreferencesManager.getInstance();
        mPrefManager.setContext(mContextWeakReference);
        SessionLoaded sessionLoaded = mPrefManager.loadSession();
        if (!TextUtils.isEmpty(sessionLoaded.getUser()) || !TextUtils.isEmpty(sessionLoaded.getPassword())) {
            mSessionHelper.login(sessionLoaded.getUser(), sessionLoaded.getPassword());
        } else {
            mLinearOverlay.setVisibility(View.INVISIBLE);
        }

        mUserId = findViewById(R.id.field_user_id);
        mPassword = findViewById(R.id.field_password);

        mButtonLogin = findViewById(R.id.button_login);
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserId.setEnabled(false);
                mPassword.setEnabled(false);
                mButtonLogin.setVisibility(View.INVISIBLE);
                mLinearOverlay.setVisibility(View.VISIBLE);
                mSessionHelper.login(mUserId.getText().toString(), mPassword.getText().toString());
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageLogin(LoginMessage event) {
        if (event.isResult()) {
            mSessionHelper.createNewSession(event.getStudent());
            mPrefManager.saveSession(event.getStudent().getUserId(), event.getStudent().getPassword());
            startActivity(new Intent(this, NewsActivity.class));
        } else {
            Toast.makeText(this, R.string.login_result_failed, Toast.LENGTH_LONG).show();
        }
        mUserId.setEnabled(true);
        mPassword.setEnabled(true);
        mButtonLogin.setVisibility(View.VISIBLE);
        mLinearOverlay.setVisibility(View.INVISIBLE);
    }
}
