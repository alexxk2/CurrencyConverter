package com.example.currencyconverter.models

import com.example.currencyconverter.fragments.ConverterFragment.Companion.BASE_URL
import com.example.currencyconverter.sources.ConverterApi
import com.example.currencyconverter.sources.CurrencyCode
import com.example.currencyconverter.sources.CurrencyNames
import com.example.currencyconverter.sources.SymbolsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class RequestService {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val converterApiService = retrofit.create(ConverterApi::class.java)

    fun getExchangeRate(baseCurrency: String, currencies: String): Float {
        var exchangeRate: Float? = 1f

        converterApiService.getLatest(baseCurrency = baseCurrency, currencies = currencies)
            .enqueue(object :
                Callback<SymbolsResponse> {
                override fun onResponse(
                    call: Call<SymbolsResponse>,
                    response: Response<SymbolsResponse>
                ) {
                    if (response.code() == 200) {

                        exchangeRate = when (currencies) {
                            "RUB" -> response.body()?.data?.RUB?.value
                            "USD" -> response.body()?.data?.USD?.value
                            "EUR" -> response.body()?.data?.EUR?.value
                            "AUD" -> response.body()?.data?.AUD?.value
                            "BRL" -> response.body()?.data?.BRL?.value
                            "BYN" -> response.body()?.data?.BYN?.value
                            "CAD" -> response.body()?.data?.CAD?.value
                            "CNY" -> response.body()?.data?.CNY?.value
                            "EGP" -> response.body()?.data?.EGP?.value
                            "GBP" -> response.body()?.data?.GBP?.value
                            "GEL" -> response.body()?.data?.GEL?.value
                            "ILS" -> response.body()?.data?.ILS?.value
                            "INR" -> response.body()?.data?.INR?.value
                            "JPY" -> response.body()?.data?.JPY?.value
                            "KRW" -> response.body()?.data?.KRW?.value
                            "KZT" -> response.body()?.data?.KZT?.value
                            "MXN" -> response.body()?.data?.MXN?.value
                            "SAR" -> response.body()?.data?.SAR?.value
                            "THB" -> response.body()?.data?.THB?.value
                            "UZS" -> response.body()?.data?.UZS?.value
                            else -> response.body()?.data?.BTC?.value
                        }
                    }
                }

                override fun onFailure(call: Call<SymbolsResponse>, t: Throwable) {

                }
            })
        return exchangeRate!!
    }

    companion object {
        const val BASE_URL = "https://api.currencyapi.com"
    }

}