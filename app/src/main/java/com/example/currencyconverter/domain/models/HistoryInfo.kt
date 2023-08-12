package com.example.currencyconverter.domain.models

data class HistoryInfo(
    val id: Int = 0,
    val startFlagSrc: Int,
    val endFlagSrc: Int,
    val inputValue: String,
    val resultValue: String,
    val date: String
)
