package com.example.currencyconverter.data.db.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class HistoryInfoDto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "start_flag_src") val startFlagSrc: Int,
    @ColumnInfo(name = "end_flag_src") val endFlagSrc: Int,
    @ColumnInfo(name = "input_value") val inputValue: String,
    @ColumnInfo(name = "result_value") val resultValue: String,
    val date: String
)
