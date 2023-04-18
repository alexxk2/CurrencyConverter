package com.example.currencyconverter.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.currencyconverter.models.ConverterService
import com.example.currencyconverter.models.RequestService
import java.math.BigDecimal

class ConverterViewModel : ViewModel() {


    private val _convertedValue = MutableLiveData<String>()
    val convertedValue: MutableLiveData<String> = _convertedValue

    private var exchangeRate = 1.0f
    private var isRateUpdated = false

    private val requestService = object : RequestService() {}
    private val converterService = object : ConverterService() {}


    fun updateExchangeRate(baseCurrency: String, currencies: String) {
        if (isRateUpdated) return
        else {
            exchangeRate = requestService.getExchangeRate(baseCurrency, currencies)
            isRateUpdated = true
        }
    }

    fun convert(input: Float) {
        _convertedValue.value = converterService.convert(input.toBigDecimal(), exchangeRate)
    }

    fun swapCurrencies(baseCurrency: String, currencies: String){
        if (isRateUpdated){
            exchangeRate = 1/exchangeRate
        }
        else updateExchangeRate(baseCurrency,currencies)
    }



}