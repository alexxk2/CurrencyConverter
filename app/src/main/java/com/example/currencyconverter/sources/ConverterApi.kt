package com.example.currencyconverter.sources

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ConverterApi {
    @GET("/v3/status?apikey=$API_KEY")
    fun getStatus(): Call<SymbolsResponse>

    @GET("/v3/latest")
    fun getLatest(
        @Query("apikey") apiKey: String = API_KEY,
        @Query("base_currency") baseCurrency: String,
        @Query("currencies") currencies: String
    ): Call<SymbolsResponse>

    @GET("/v3/currencies")
    fun getCurrencies(
        @Query("apikey") apiKey: String = API_KEY
    ): Call<SymbolsResponse>


    companion object {
        const val API_KEY = "m8t10hvSal3gRP9IIlLERGTxd8CgbqcUHkoKKi1Q"
    }
}


