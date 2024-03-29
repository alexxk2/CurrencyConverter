package com.example.currencyconverter.app

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.currencyconverter.di.dataModule
import com.example.currencyconverter.di.domainModule
import com.example.currencyconverter.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import java.util.*

const val SHARED_PREFS = "shared_prefs"
const val IS_DARK_THEME = "is_dark_theme"
const val LANGUAGE = "language"

class App : Application() {

    var darkTheme = false
    var language: String? = "en"

    override fun onCreate() {
        super.onCreate()


        startKoin {
            androidContext(this@App)
            androidLogger(Level.DEBUG)
            modules(listOf(dataModule, domainModule, presentationModule))
        }

        val sharedPrefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        darkTheme = sharedPrefs.getBoolean(IS_DARK_THEME, false)
        language = sharedPrefs.getString(LANGUAGE, "en")


        choseLanguage(language!!)
        setNightMode(darkTheme)
    }


    fun switchNightMode(isDarkThemeEnabled: Boolean) {
        darkTheme = isDarkThemeEnabled

        val sharedPrefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        sharedPrefs.edit()
            .putBoolean(IS_DARK_THEME, darkTheme)
            .apply()

        setNightMode(isDarkThemeEnabled)
    }

    fun switchLanguage(languageChoice: String) {
        language = languageChoice

        val sharedPrefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        sharedPrefs.edit()
            .putString(LANGUAGE, language)
            .apply()

        choseLanguage(language!!)
    }

    private fun setNightMode(isDarkThemeEnabled: Boolean) {
        AppCompatDelegate.setDefaultNightMode(
            if (isDarkThemeEnabled) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
    }

    private fun choseLanguage(languageChoice: String) {
        val locale = Locale(languageChoice)
        val appLocale2 = LocaleListCompat.create(locale)
        // Call this on the main thread as it may require Activity.restart()
        AppCompatDelegate.setApplicationLocales(appLocale2)
    }

}