package com.example.currencyconverter.domain.repositories

import com.example.currencyconverter.domain.models.CurrencyInfo

interface SearchRepository {
    fun getCurrencyDefaultList(): MutableList<CurrencyInfo>
    fun getCurrencyFilteredList(searchInput: String): MutableList<CurrencyInfo>
}