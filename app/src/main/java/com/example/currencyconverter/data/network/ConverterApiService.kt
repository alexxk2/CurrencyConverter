package com.example.currencyconverter.data.network

import com.example.currencyconverter.data.network.dto.CurrencyResponseEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ConverterApiService {
    @GET("/v3/status?apikey=$API_KEY")
    fun getStatus(): Response<CurrencyResponseEntity>

    @GET("/v3/latest")
    suspend fun getLatest(
        @Query("apikey") apiKey: String = API_KEY,
        @Query("base_currency") baseCurrency: String,
        //@Query("currencies") currencies: String
    ): Response<CurrencyResponseEntity>

    @GET("/v3/currencies")
    fun getCurrencies(
        @Query("apikey") apiKey: String = API_KEY
    ): Response<CurrencyResponseEntity>

    companion object {
        const val API_KEY = "m8t10hvSal3gRP9IIlLERGTxd8CgbqcUHkoKKi1Q"
    }
}


