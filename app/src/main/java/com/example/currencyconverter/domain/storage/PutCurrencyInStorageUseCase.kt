package com.example.currencyconverter.domain.storage

import com.example.currencyconverter.domain.repositories.StorageRepository
import com.example.currencyconverter.models.CurrencyInfo

class PutCurrencyInStorageUseCase(private val storageRepository: StorageRepository) {

    fun execute(side: String, currencyInfo: CurrencyInfo) =
        storageRepository.putCurrencyInStorage(side,currencyInfo)

}