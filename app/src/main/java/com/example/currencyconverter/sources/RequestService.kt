package com.example.currencyconverter.sources

import androidx.lifecycle.MutableLiveData

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class RequestService {

    var isUpdated = false
    private val _exchangeRate2 = MutableLiveData<Float?>()
    val exchangeRate2: MutableLiveData<Float?> = _exchangeRate2
    var exchangeRate: Float? = 1.0f


    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val converterApiService = retrofit.create(ConverterApi::class.java)

    fun getConverterApiServiceInstance(): ConverterApi{
        return converterApiService
    }

    companion object {
        const val BASE_URL = "https://api.currencyapi.com"
    }

}