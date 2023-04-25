package com.example.currencyconverter

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

const val SHARED_PREFS = "shared_prefs"
const val IS_DARK_THEME = "is_dark_theme"

class App: Application() {

    var darkTheme = false

    override fun onCreate() {
        super.onCreate()

        val sharedPrefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        darkTheme = sharedPrefs.getBoolean(IS_DARK_THEME,false)


        setNightMode(darkTheme)
    }

    fun switchNightMode(isDarkThemeEnabled: Boolean){
        darkTheme = isDarkThemeEnabled

        val sharedPrefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        sharedPrefs.edit()
            .putBoolean(IS_DARK_THEME,darkTheme)
            .apply()

        setNightMode(isDarkThemeEnabled)
    }

    private fun setNightMode(isDarkThemeEnabled: Boolean){
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkThemeEnabled) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
    }
}