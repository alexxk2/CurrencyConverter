package com.example.currencyconverter.domain.storage

import com.example.currencyconverter.domain.repositories.StorageRepository

class GetHistoryItemUseCase(private val storageRepository: StorageRepository) {
    suspend fun execute(id: Int) = storageRepository.getHistoryItem(id)
}