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

import java.lang.ref.WeakReference;

import androidx.appcompat.widget.Toolbar;

import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.common.NotificationsHelper;
import mx.edu.upqroo.kristenandroid.common.PreferencesManager;
import mx.edu.upqroo.kristenandroid.common.Serializer;
import mx.edu.upqroo.kristenandroid.common.SessionHelper;
import mx.edu.upqroo.kristenandroid.models.GeneralInfo;
import mx.edu.upqroo.kristenandroid.models.SessionLoaded;

public class MainActivity extends AppCompatActivity {
    private SessionHelper mSessionHelper;
    private PreferencesManager mPrefManager;
    private TextView mUserId;
    private TextView mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = findViewById(R.id.toolbarLogin);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("UPQROO");

        WeakReference<Context> mContextWeakReference = new WeakReference<>(getApplicationContext());

        mSessionHelper = SessionHelper.getInstance();
        mPrefManager = PreferencesManager.getInstance();
        mPrefManager.setContext(mContextWeakReference);
        SessionLoaded sessionLoaded = mPrefManager.loadSession();
        if (!TextUtils.isEmpty(sessionLoaded.getUser()) || !TextUtils.isEmpty(sessionLoaded.getPassword())) {
            mSessionHelper.login(sessionLoaded.getUser(), sessionLoaded.getPassword());
            startActivity(new Intent(this, NewsActivity.class));
        }

        mUserId = findViewById(R.id.field_user_id);
        mPassword = findViewById(R.id.field_password);

        Button buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSessionHelper.login(mUserId.getText().toString(), mPassword.getText().toString());
                if (mSessionHelper.getSession() != null) {
                    mPrefManager.saveSession(mUserId.getText().toString(), mPassword.getText().toString());
                    startActivity(new Intent(v.getContext(), NewsActivity.class));
                } else {
                    Toast.makeText(v.getContext(), R.string.login_result_failed,
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }/*

    private void saveSession(String a, String b) {
        SharedPreferences sharedPref = getSharedPreferences(SessionHelper.PREFERENCE_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(SessionHelper.SESSION_KEY, a);
        editor.putString(SessionHelper.PASS_KEY, b);
        editor.apply();
    }

    private void loadSession() {
        SharedPreferences sharedPref = getSharedPreferences(SessionHelper.PREFERENCE_FILE, MODE_PRIVATE);
        String session = sharedPref.getString(SessionHelper.SESSION_KEY, "");
        String pass = sharedPref.getString(SessionHelper.PASS_KEY, "");
        if (!session.equals("")) {
            mSessionHelper.login(session, pass);
            //todo aqui se tiene que evaluar si el login es exitoso antes de iniciar el siguiente activity
            startActivity(new Intent(this, NewsActivity.class));
        }
    }

    public static void clearSession() {
        Context c = mContextWeakReference.get();
        SharedPreferences sharedPref = c.getSharedPreferences(SessionHelper.PREFERENCE_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.apply();
    }*/
}
