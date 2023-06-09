package com.example.currencyconverter.domain.search

import com.example.currencyconverter.domain.repositories.SearchRepository
import com.example.currencyconverter.models.CurrencyInfo

class GetCurrencyDefaultListUseCase(private val searchRepository: SearchRepository) {
    fun execute(): MutableList<CurrencyInfo> = searchRepository.getCurrencyDefaultList()
}