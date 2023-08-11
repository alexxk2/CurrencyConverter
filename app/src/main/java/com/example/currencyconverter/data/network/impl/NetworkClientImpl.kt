package com.example.currencyconverter.data.network.impl

import com.example.currencyconverter.data.network.ConverterApiService
import com.example.currencyconverter.data.network.NetworkClient
import com.example.currencyconverter.data.network.dto.CurrencyRequestEntity
import com.example.currencyconverter.data.network.dto.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClientImpl: NetworkClient {

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()


    private val retrofitService: ConverterApiService by lazy {
        retrofit.create(ConverterApiService::class.java)
    }

    override suspend fun getAllCurrenciesRates(dto: Any): Response{

        return if(dto is CurrencyRequestEntity){
            val result = retrofitService.getLatest(baseCurrency = dto.baseCurrency)
            val body = result.body()?:Response()

            body.apply { resultCode = result.code() }
        } else Response().apply { resultCode = 400 }

    }

    companion object{
        private const val BASE_URL = "https://api.currencyapi.com"
    }
}




