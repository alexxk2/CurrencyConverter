package com.example.currencyconverter.domain.storage

import com.example.currencyconverter.domain.repositories.StorageRepository

class ClearHistoryUseCase(private val storageRepository: StorageRepository) {
    suspend fun execute() = storageRepository.clearHistory()
}