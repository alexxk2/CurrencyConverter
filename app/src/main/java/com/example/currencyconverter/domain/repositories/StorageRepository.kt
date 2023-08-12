package com.example.currencyconverter.domain.repositories

import com.example.currencyconverter.domain.models.CurrencyInfo
import com.example.currencyconverter.domain.models.HistoryInfo

interface StorageRepository {
    fun getCurrencyFormStorage(defaultValue: CurrencyInfo, sharedPrefsName: String): CurrencyInfo
    fun putCurrencyInStorage(side: String, currencyInfo: CurrencyInfo)

    suspend fun addNewHistoryItem(historyInfo: HistoryInfo)
    suspend fun clearHistory()
    suspend fun deleteHistoryItem(historyInfo: HistoryInfo)
    suspend fun getAllHistory(): List<HistoryInfo>
    suspend fun getHistoryItem(id: Int): HistoryInfo
}