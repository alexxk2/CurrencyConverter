package com.example.currencyconverter.domain.storage

import com.example.currencyconverter.domain.models.HistoryInfo
import com.example.currencyconverter.domain.repositories.StorageRepository

class AddNewHistoryItemUseCase(private val storageRepository: StorageRepository) {
    suspend fun execute(historyInfo: HistoryInfo) = storageRepository.addNewHistoryItem(historyInfo)
}