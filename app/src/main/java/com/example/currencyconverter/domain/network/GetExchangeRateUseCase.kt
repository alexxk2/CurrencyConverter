package com.example.currencyconverter.domain.network

import com.example.currencyconverter.domain.repositories.NetworkRepository

class GetExchangeRateUseCase(private val networkRepository: NetworkRepository) {

    suspend fun execute(baseCurrency: String, currencies: String): Float =
        networkRepository.getExchangeRate(baseCurrency = baseCurrency, currencies = currencies)

}