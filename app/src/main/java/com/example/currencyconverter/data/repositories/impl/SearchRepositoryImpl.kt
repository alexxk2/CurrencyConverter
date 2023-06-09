package com.example.currencyconverter.data.repositories.impl

import com.example.currencyconverter.data.search.HardCodedCurrencyStorage
import com.example.currencyconverter.domain.repositories.SearchRepository
import com.example.currencyconverter.models.CurrencyInfo

class SearchRepositoryImpl(private val hardCodedCurrencyStorage: HardCodedCurrencyStorage) :
    SearchRepository {

    override fun getCurrencyDefaultList(): MutableList<CurrencyInfo> {
        val defaultDataList = hardCodedCurrencyStorage.getCurrencyDefaultList()
        return defaultDataList.map {
            CurrencyInfo(
                code = it.code,
                name = it.name,
                flag = it.flag,
                symbol = it.symbol
            )
        }.toMutableList()
    }

    override fun getCurrencyFilteredList(searchInput: String): MutableList<CurrencyInfo> {
        val filteredDataList =
            hardCodedCurrencyStorage.getCurrencyFilteredList(searchInput = searchInput)
        return filteredDataList.map {
            CurrencyInfo(
                code = it.code,
                name = it.name,
                flag = it.flag,
                symbol = it.symbol
            )
        }.toMutableList()
    }
}
