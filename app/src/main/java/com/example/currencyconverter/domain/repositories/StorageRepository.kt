package com.example.currencyconverter.domain.repositories

import com.example.currencyconverter.models.CurrencyInfo

interface StorageRepository {
    fun getCurrencyFormStorage(defaultValue: CurrencyInfo, sharedPrefsName: String): CurrencyInfo
    fun putCurrencyInStorage(side: String, currencyInfo: CurrencyInfo)
}