package com.example.currencyconverter.data.repositories.impl

import com.example.currencyconverter.data.search.models.CurrencyInfoDto
import com.example.currencyconverter.data.storage.SharedPrefStorage
import com.example.currencyconverter.domain.repositories.StorageRepository
import com.example.currencyconverter.models.CurrencyInfo

class StorageRepositoryImpl(private val sharedPrefStorage: SharedPrefStorage): StorageRepository {

    override fun getCurrencyFormStorage(
        defaultValue: CurrencyInfo,
        sharedPrefsName: String
    ): CurrencyInfo {

        val mappedInput = mapToData(defaultValue)
        val dataResult =
            sharedPrefStorage.getCurrencyFormStorage(mappedInput, sharedPrefsName)

        return mapToDomain(dataResult)
    }

    override fun putCurrencyInStorage(side: String, currencyInfo: CurrencyInfo) {
        val mappedInput = mapToData(currencyInfo)
        sharedPrefStorage.putCurrencyInStorage(side,mappedInput)
    }


    private fun mapToData(defaultValue: CurrencyInfo): CurrencyInfoDto{
        return CurrencyInfoDto(
            code = defaultValue.code,
            name = defaultValue.name,
            flag = defaultValue.flag,
            symbol = defaultValue.symbol
        )
    }

    private fun mapToDomain(defaultValue: CurrencyInfoDto): CurrencyInfo{
        return CurrencyInfo(
            code = defaultValue.code,
            name = defaultValue.name,
            flag = defaultValue.flag,
            symbol = defaultValue.symbol
        )
    }
}