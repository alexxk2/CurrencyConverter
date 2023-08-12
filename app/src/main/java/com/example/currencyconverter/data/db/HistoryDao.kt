package com.example.currencyconverter.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.currencyconverter.data.db.dto.HistoryInfoDto


@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNewItem(historyInfoDto: HistoryInfoDto)

    @Delete
    suspend fun deleteItem(historyInfoDto: HistoryInfoDto)

    @Query("SELECT * FROM history ORDER BY id ASC")
    suspend fun getAllItems(): List<HistoryInfoDto>

    @Query("SELECT * FROM history WHERE id =:id")
    suspend fun getItem(id: Int): HistoryInfoDto

    @Query("DELETE FROM history")
    suspend fun clearHistory()

}