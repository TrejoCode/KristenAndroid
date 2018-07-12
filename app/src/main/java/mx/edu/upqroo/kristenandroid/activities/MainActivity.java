package mx.edu.upqroo.kristenandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.common.SessionHelper;

public class MainActivity extends AppCompatActivity {
    SessionHelper mSessionHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSessionHelper = SessionHelper.getInstance();

        Button buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSessionHelper.login("1234", "1234");
                if (mSessionHelper.getSession() != null) {
                    startActivity(new Intent(v.getContext(), NewsActivity.class));
                } else {
                    Toast.makeText(v.getContext(),
                            "Usuario o contraseña invalido", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
