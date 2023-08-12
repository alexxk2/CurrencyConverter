package com.example.currencyconverter.data.repositories.impl

import com.example.currencyconverter.data.db.RoomStorage
import com.example.currencyconverter.data.db.dto.HistoryInfoDto
import com.example.currencyconverter.data.search.models.CurrencyInfoDto
import com.example.currencyconverter.data.storage.SharedPrefStorage
import com.example.currencyconverter.domain.repositories.StorageRepository
import com.example.currencyconverter.domain.models.CurrencyInfo
import com.example.currencyconverter.domain.models.HistoryInfo

class StorageRepositoryImpl(
    private val sharedPrefStorage: SharedPrefStorage,
    private val roomStorage: RoomStorage
) : StorageRepository {

    override fun getCurrencyFormStorage(
        defaultValue: CurrencyInfo,
        sharedPrefsName: String
    ): CurrencyInfo {

        val mappedInput = mapCurrencyToData(defaultValue)
        val dataResult =
            sharedPrefStorage.getCurrencyFormStorage(mappedInput, sharedPrefsName)

        return mapCurrencyToDomain(dataResult)
    }

    override fun putCurrencyInStorage(side: String, currencyInfo: CurrencyInfo) {
        val mappedInput = mapCurrencyToData(currencyInfo)
        sharedPrefStorage.putCurrencyInStorage(side, mappedInput)
    }

    override suspend fun addNewHistoryItem(historyInfo: HistoryInfo) {
        val mappedInput = mapHistoryItemToData(historyInfo)
        roomStorage.addNewHistoryItem(mappedInput)
    }

    override suspend fun clearHistory() {
        roomStorage.clearHistory()
    }

    override suspend fun deleteHistoryItem(historyInfo: HistoryInfo) {
        val mappedInput = mapHistoryItemToData(historyInfo)
        roomStorage.deleteHistoryItem(mappedInput)
    }

    override suspend fun getAllHistory(): List<HistoryInfo> {
        val resultFromData = roomStorage.getAllHistory()
        return resultFromData.map {historyInfoDto->
            mapHistoryItemToDomain(historyInfoDto)
        }
    }

    override suspend fun getHistoryItem(id: Int): HistoryInfo {
        val resultFromData = roomStorage.getHistoryItem(id)
        return mapHistoryItemToDomain(resultFromData)
    }

    private fun mapCurrencyToData(defaultValue: CurrencyInfo): CurrencyInfoDto {
        return CurrencyInfoDto(
            code = defaultValue.code,
            name = defaultValue.name,
            flag = defaultValue.flag,
            symbol = defaultValue.symbol
        )
    }

    private fun mapCurrencyToDomain(defaultValue: CurrencyInfoDto): CurrencyInfo {
        return CurrencyInfo(
            code = defaultValue.code,
            name = defaultValue.name,
            flag = defaultValue.flag,
            symbol = defaultValue.symbol
        )
    }

    private fun mapHistoryItemToData(historyInfo: HistoryInfo): HistoryInfoDto {
        with(historyInfo) {
            return HistoryInfoDto(
                id = id,
                startFlagSrc = startFlagSrc,
                endFlagSrc = endFlagSrc,
                inputValue = inputValue,
                resultValue = resultValue,
                date = date
            )
        }
    }

    private fun mapHistoryItemToDomain(historyInfoDto: HistoryInfoDto): HistoryInfo {
        with(historyInfoDto) {
            return HistoryInfo(
                id = id,
                startFlagSrc = startFlagSrc,
                endFlagSrc = endFlagSrc,
                inputValue = inputValue,
                resultValue = resultValue,
                date = date
            )
        }
    }

}