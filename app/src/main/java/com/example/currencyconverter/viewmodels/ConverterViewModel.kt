package com.example.currencyconverter.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.currencyconverter.models.ConverterService
import com.example.currencyconverter.models.RequestService
import com.example.currencyconverter.sources.ConverterApi
import com.example.currencyconverter.sources.SymbolsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConverterViewModel : ViewModel() {


    private val _convertedValue = MutableLiveData<String>()
    val convertedValue: MutableLiveData<String> = _convertedValue

    private val _isRateUpdated = MutableLiveData<Boolean>(true)//поменять на фолс после тестов
    val isRateUpdated: MutableLiveData<Boolean> = _isRateUpdated

    private var exchangeRate = 1.0f
    //private var isRateUpdated = false

    private val converterService = object : ConverterService() {}
    private val requestService = object : RequestService() {}



    /*fun updateExchangeRate(baseCurrency: String, currencies: String) {
            exchangeRate = requestService.getExchangeRate(baseCurrency,currencies)
            isRateUpdated = true
    }*/

    /*fun updateExchangeRateInViewModel(rate: Float?) {
       if (_isRateUpdated.value == true) return
       else {
           exchangeRate = rate!!
           _isRateUpdated.value = true
       }
   }*/

    fun convert(input: Float) {
        _convertedValue.value = converterService.convert(input.toBigDecimal(), exchangeRate)
    }

    fun swapCurrencies(baseCurrency: String, currencies: String){
            exchangeRate = 1/exchangeRate
    }

    /*fun swapCurrencies(){
            exchangeRate = 1/exchangeRate
    }*/

    fun makeRequest(baseCurrency: String, currencies: String) {

        var rateFromResponse: Float? = 1.0f

        val retrofit = Retrofit.Builder()
            .baseUrl(RequestService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val converterApiService = retrofit.create(ConverterApi::class.java)


        converterApiService.getLatest(baseCurrency = baseCurrency, currencies = currencies)
            .enqueue(object :
                Callback<SymbolsResponse> {
                override fun onResponse(
                    call: Call<SymbolsResponse>,
                    response: Response<SymbolsResponse>
                ) {
                    if (response.code() == 200) {
                        exchangeRate = when (currencies) {
                            "RUB" -> response.body()?.data?.RUB?.value ?: 1.0f
                            "USD" -> response.body()?.data?.USD?.value ?: 1.0f
                            "EUR" -> response.body()?.data?.EUR?.value ?: 1.0f
                            "AUD" -> response.body()?.data?.AUD?.value ?: 1.0f
                            "BRL" -> response.body()?.data?.BRL?.value ?: 1.0f
                            "BYN" -> response.body()?.data?.BYN?.value ?: 1.0f
                            "CAD" -> response.body()?.data?.CAD?.value ?: 1.0f
                            "CNY" -> response.body()?.data?.CNY?.value ?: 1.0f
                            "EGP" -> response.body()?.data?.EGP?.value ?: 1.0f
                            "GBP" -> response.body()?.data?.GBP?.value ?: 1.0f
                            "GEL" -> response.body()?.data?.GEL?.value ?: 1.0f
                            "ILS" -> response.body()?.data?.ILS?.value ?: 1.0f
                            "INR" -> response.body()?.data?.INR?.value ?: 1.0f
                            "JPY" -> response.body()?.data?.JPY?.value ?: 1.0f
                            "KRW" -> response.body()?.data?.KRW?.value ?: 1.0f
                            "KZT" -> response.body()?.data?.KZT?.value ?: 1.0f
                            "MXN" -> response.body()?.data?.MXN?.value ?: 1.0f
                            "SAR" -> response.body()?.data?.SAR?.value ?: 1.0f
                            "THB" -> response.body()?.data?.THB?.value ?: 1.0f
                            "UZS" -> response.body()?.data?.UZS?.value ?: 1.0f
                            else -> response.body()?.data?.BTC?.value ?: 1.0f
                        }
                        _isRateUpdated.value = true
                    }
                }
                override fun onFailure(call: Call<SymbolsResponse>, t: Throwable) {
                }
            })


    }

}