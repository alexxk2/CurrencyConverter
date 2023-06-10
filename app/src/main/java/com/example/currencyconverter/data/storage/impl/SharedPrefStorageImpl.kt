package com.example.currencyconverter.data.storage.impl

import android.content.Context
import com.example.currencyconverter.app.SHARED_PREFS
import com.example.currencyconverter.data.search.models.CurrencyInfoDto
import com.example.currencyconverter.data.storage.SharedPrefStorage
import com.google.gson.Gson

class SharedPrefStorageImpl(context: Context): SharedPrefStorage {

    private val sharedPrefs = context.getSharedPreferences(SHARED_PREFS,0)

    override fun getCurrencyFormStorage(
        defaultValue: CurrencyInfoDto,
        sharedPrefsName: String
    ): CurrencyInfoDto {
        val defaultJsonLeft = Gson().toJson(defaultValue)
        val leftJsonCurrency = sharedPrefs.getString(sharedPrefsName,defaultJsonLeft)
        return Gson().fromJson(leftJsonCurrency,CurrencyInfoDto::class.java)
    }

    override fun putCurrencyInStorage(side: String, currencyInfo: CurrencyInfoDto) {
        val jSonCurrency = Gson().toJson(currencyInfo, CurrencyInfoDto::class.java)
        sharedPrefs.edit()
            .putString(side, jSonCurrency)
            .apply()
    }
}