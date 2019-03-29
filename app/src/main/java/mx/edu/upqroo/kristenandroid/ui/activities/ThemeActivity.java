package mx.edu.upqroo.kristenandroid.ui.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.managers.PreferencesManager;

abstract class ThemeActivity extends AppCompatActivity {
    public static boolean HAS_THEME_CHANGED = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applyTheme();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void applyTheme() {
        if (PreferencesManager.getInstance().loadDarkThemeConfig()) {
            setTheme(R.style.AppThemeDark);
        } else {
            setTheme(R.style.AppTheme);
        }
    }
}
