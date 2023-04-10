package com.example.currencyconverter.sources

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ConverterApi {

    @GET("/symbols?access_key=API_KEY")
    fun getSymbols(@Query("access_key") accessKey: String ): Call<SymbolsResponse>

    @GET("/latest?access_key=API_KEY")
    fun getLatest(@Query("access_key")accessKey: String):Call<SymbolsResponse>

    @GET("/v3/status?apikey=$API_KEY")
    fun getLatest2():Call<SymbolsResponse>


    companion object{
        const val API_KEY = "m8t10hvSal3gRP9IIlLERGTxd8CgbqcUHkoKKi1Q"
    }
}


//&base=USD&symbols=GBP,JPY,EUR