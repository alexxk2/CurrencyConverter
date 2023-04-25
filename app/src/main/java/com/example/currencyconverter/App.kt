package com.example.currencyconverter

import android.app.Application
import android.content.res.Configuration
import android.os.LocaleList
import android.view.ContextThemeWrapper
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import java.util.*

const val SHARED_PREFS = "shared_prefs"
const val IS_DARK_THEME = "is_dark_theme"

class App : Application() {

    var darkTheme = false

    override fun onCreate() {
        super.onCreate()

        val sharedPrefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        darkTheme = sharedPrefs.getBoolean(IS_DARK_THEME, false)


        val locale = Locale("ru")
        val appLocale2 = LocaleListCompat.create(locale)

        val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags("xx-YY")
        // Call this on the main thread as it may require Activity.restart()
        AppCompatDelegate.setApplicationLocales(appLocale2)



        setNightMode(darkTheme)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)




    }

    fun switchNightMode(isDarkThemeEnabled: Boolean) {
        darkTheme = isDarkThemeEnabled

        val sharedPrefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        sharedPrefs.edit()
            .putBoolean(IS_DARK_THEME, darkTheme)
            .apply()

        setNightMode(isDarkThemeEnabled)
    }

    private fun setNightMode(isDarkThemeEnabled: Boolean) {
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkThemeEnabled) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
    }


}