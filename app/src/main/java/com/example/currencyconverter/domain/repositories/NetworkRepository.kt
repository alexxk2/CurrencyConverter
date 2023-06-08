package com.example.currencyconverter.domain.repositories

interface NetworkRepository {

    suspend fun getExchangeRate(baseCurrency: String, currencies: String): Float
}