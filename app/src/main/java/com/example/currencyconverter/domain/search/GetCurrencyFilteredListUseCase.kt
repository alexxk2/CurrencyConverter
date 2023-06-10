package com.example.currencyconverter.domain.search

import com.example.currencyconverter.domain.repositories.SearchRepository
import com.example.currencyconverter.domain.models.CurrencyInfo

class GetCurrencyFilteredListUseCase(private val searchRepository: SearchRepository) {

    fun execute(searchInput: String): MutableList<CurrencyInfo> =
        searchRepository.getCurrencyFilteredList(searchInput)

}