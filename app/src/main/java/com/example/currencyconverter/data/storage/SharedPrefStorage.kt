package com.example.currencyconverter.data.storage

import com.example.currencyconverter.data.search.models.CurrencyInfoDto


interface SharedPrefStorage {
    fun getCurrencyFormStorage(defaultValue: CurrencyInfoDto, sharedPrefsName: String): CurrencyInfoDto
    fun putCurrencyInStorage(side: String, currencyInfo: CurrencyInfoDto)
}