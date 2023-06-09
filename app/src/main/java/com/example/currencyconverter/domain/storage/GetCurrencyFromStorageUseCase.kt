package com.example.currencyconverter.domain.storage

import com.example.currencyconverter.domain.repositories.StorageRepository
import com.example.currencyconverter.models.CurrencyInfo

class GetCurrencyFromStorageUseCase(private val storageRepository: StorageRepository) {

    fun execute(defaultValue: CurrencyInfo, sharedPrefsName: String): CurrencyInfo =
        storageRepository.getCurrencyFormStorage(defaultValue, sharedPrefsName)

}