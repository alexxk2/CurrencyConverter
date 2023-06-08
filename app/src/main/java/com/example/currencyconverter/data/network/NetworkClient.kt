package com.example.currencyconverter.data.network

import com.example.currencyconverter.data.network.dto.Response

interface NetworkClient {

     suspend fun getAllCurrenciesRates(dto: Any): Response
}