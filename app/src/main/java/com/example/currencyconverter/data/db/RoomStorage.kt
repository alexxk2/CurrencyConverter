package com.example.currencyconverter.data.db

import com.example.currencyconverter.data.db.dto.HistoryInfoDto

interface RoomStorage {

    suspend fun addNewHistoryItem(historyInfoDto: HistoryInfoDto)
    suspend fun clearHistory()
    suspend fun deleteHistoryItem(historyInfoDto: HistoryInfoDto)
    suspend fun getAllHistory(): List<HistoryInfoDto>
    suspend fun getHistoryItem(id: Int): HistoryInfoDto
}