package com.example.currencyconverter.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.currencyconverter.data.db.dto.HistoryInfoDto


@Database(entities = [HistoryInfoDto::class], version = 1, exportSchema = false)
abstract class HistoryDatabase : RoomDatabase() {

    abstract fun getHistoryDao(): HistoryDao

    companion object {
        private var INSTANCE: HistoryDatabase? = null

        fun getDataBase(context: Context): HistoryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    HistoryDatabase::class.java,
                    "history_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance

            }

        }
    }
}