package mx.edu.upqroo.kristenandroid.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.managers.PreferencesManager

abstract class ThemeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyTheme()
    }

    fun applyTheme() {
        if (PreferencesManager.instance.loadDarkThemeConfig()) {
            setTheme(R.style.AppThemeDark)
        } else {
            setTheme(R.style.AppTheme)
        }
    }

    companion object {
        var HAS_THEME_CHANGED = false
    }
}
