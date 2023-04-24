package com.example.currencyconverter.network


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.currencyapi.com"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()


object ConverterApi {
    val retrofitService: ConverterApiService by lazy {
        retrofit.create(ConverterApiService::class.java)
    }
}
