package com.example.currencyconverter.data.db.impl

import com.example.currencyconverter.data.db.HistoryDatabase
import com.example.currencyconverter.data.db.RoomStorage
import com.example.currencyconverter.data.db.dto.HistoryInfoDto

class RoomStorageImpl(historyDatabase: HistoryDatabase) : RoomStorage {

    private val historyDao = historyDatabase.getHistoryDao()

    override suspend fun addNewHistoryItem(historyInfoDto: HistoryInfoDto) {
        historyDao.addNewItem(historyInfoDto)
    }

    override suspend fun clearHistory() {
        historyDao.clearHistory()
    }

    override suspend fun deleteHistoryItem(historyInfoDto: HistoryInfoDto) {
        historyDao.deleteItem(historyInfoDto)
    }

    override suspend fun getAllHistory(): List<HistoryInfoDto> = historyDao.getAllItems()


    override suspend fun getHistoryItem(id: Int): HistoryInfoDto = historyDao.getItem(id)

}