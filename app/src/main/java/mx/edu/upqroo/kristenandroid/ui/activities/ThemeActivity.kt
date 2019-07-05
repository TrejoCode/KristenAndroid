package mx.edu.upqroo.kristenandroid.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import mx.edu.upqroo.kristenandroid.R
import mx.edu.upqroo.kristenandroid.managers.PreferencesManager

abstract class ThemeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyTheme()
    }

    fun applyTheme() {
        alternateTheme(PreferencesManager.instance.loadDarkThemeConfig())
    }

    private fun alternateTheme(darkTheme: Boolean) {
        if (darkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    companion object {
        var HAS_THEME_CHANGED = false
    }
}
