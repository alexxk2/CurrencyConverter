package com.example.currencyconverter.domain.storage

import com.example.currencyconverter.domain.models.HistoryInfo
import com.example.currencyconverter.domain.repositories.StorageRepository

class GetAllHistoryUseCase(private val storageRepository: StorageRepository) {
    suspend fun execute(): List<HistoryInfo> = storageRepository.getAllHistory()
}