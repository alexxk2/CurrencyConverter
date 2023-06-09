package com.example.currencyconverter.data.search

import com.example.currencyconverter.data.search.models.CurrencyInfoDto


interface HardCodedCurrencyStorage {
    fun getCurrencyDefaultList(): MutableList<CurrencyInfoDto>
    fun getCurrencyFilteredList(searchInput: String): MutableList<CurrencyInfoDto>
}