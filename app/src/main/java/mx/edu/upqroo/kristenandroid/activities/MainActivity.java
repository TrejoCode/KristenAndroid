package mx.edu.upqroo.kristenandroid.activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.common.NotificationsHelper;
import mx.edu.upqroo.kristenandroid.common.SessionHelper;

public class MainActivity extends AppCompatActivity {
    SessionHelper mSessionHelper;
    TextView mUserId;
    TextView mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = findViewById(R.id.toolbarLogin);
        setSupportActionBar(mToolbar);

        mSessionHelper = SessionHelper.getInstance();

        mUserId = findViewById(R.id.field_user_id);
        mPassword = findViewById(R.id.field_password);

        Button buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSessionHelper.login(mUserId.getText().toString(), mPassword.getText().toString());
                if (mSessionHelper.getSession() != null) {
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
    }
}
