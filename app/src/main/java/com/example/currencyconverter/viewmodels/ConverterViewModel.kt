package com.example.currencyconverter.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.currencyconverter.domain.ConverterService
import com.example.currencyconverter.domain.network.GetExchangeRateUseCase
import com.example.currencyconverter.domain.repositories.NetworkRepository
import kotlinx.coroutines.launch
import java.lang.Exception

enum class ConverterApiStatus{LOADING, DONE, ERROR}

class ConverterViewModel(networkRepository: NetworkRepository) : ViewModel() {

    private val _convertedValue = MutableLiveData<String>()
    val convertedValue: MutableLiveData<String> = _convertedValue

    private val _conversionCounter  = MutableLiveData(1)
    val conversionCounter: MutableLiveData<Int> = _conversionCounter

    private val _apiStatus = MutableLiveData<ConverterApiStatus>()
    val apiStatus: MutableLiveData<ConverterApiStatus> = _apiStatus

    private var exchangeRate = 1.0f

    private val converterService = object : ConverterService() {}

    private val getExchangeRateUseCase = GetExchangeRateUseCase(networkRepository)

    fun convert(input: Double) {
        _convertedValue.value = converterService.convert(input.toBigDecimal(), exchangeRate)
        _conversionCounter.value = _conversionCounter.value!! + 1
    }

    fun swapCurrencies(){
            exchangeRate = 1/exchangeRate
    }

   fun resetConversionCounter(){
       _conversionCounter.value = 0
   }

    fun makeRequest(baseCurrency: String, currencies: String) {
        _apiStatus.value = ConverterApiStatus.LOADING

        viewModelScope.launch {
            try {
                val response = getExchangeRateUseCase.execute(baseCurrency, currencies)
                if (response > 0) {
                    exchangeRate = response
                    _apiStatus.value = ConverterApiStatus.DONE
                } else {
                    _apiStatus.value = ConverterApiStatus.ERROR
                }
            } catch (e: Exception) {
                _apiStatus.value = ConverterApiStatus.ERROR
            }
        }
    }

    companion object {
        fun getViewModelFactory(networkRepository: NetworkRepository): ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    ConverterViewModel(networkRepository = networkRepository)
                }
            }
    }

}