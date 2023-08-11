package com.example.currencyconverter.presentation.converter.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.domain.converter.ConvertCurrencyUseCase
import com.example.currencyconverter.domain.converter.DecimalLimitUseCase
import com.example.currencyconverter.domain.network.GetExchangeRateUseCase
import com.example.currencyconverter.domain.repositories.NetworkRepository
import com.example.currencyconverter.domain.repositories.StorageRepository
import com.example.currencyconverter.domain.storage.GetCurrencyFromStorageUseCase
import com.example.currencyconverter.domain.storage.PutCurrencyInStorageUseCase
import com.example.currencyconverter.domain.models.CurrencyInfo
import kotlinx.coroutines.launch
import java.lang.Exception

enum class ConverterApiStatus{LOADING, DONE, ERROR}

class ConverterViewModel(
    private val getExchangeRateUseCase: GetExchangeRateUseCase,
    private val convertCurrencyUseCase: ConvertCurrencyUseCase,
    private val decimalLimitUseCase: DecimalLimitUseCase,
    private val getCurrencyFromStorageUseCase: GetCurrencyFromStorageUseCase,
    private val putCurrencyInStorageUseCase: PutCurrencyInStorageUseCase
) : ViewModel() {

    private val _convertedValue = MutableLiveData<String>()
    val convertedValue: MutableLiveData<String> = _convertedValue

    private val _conversionCounter  = MutableLiveData(1)
    val conversionCounter: MutableLiveData<Int> = _conversionCounter

    //убрать DONE по умолчанию после разработки
    private val _apiStatus = MutableLiveData<ConverterApiStatus>(ConverterApiStatus.DONE)
    val apiStatus: MutableLiveData<ConverterApiStatus> = _apiStatus

    private var exchangeRate = 1.0f


    fun convert(input: Double) {
        _convertedValue.value = convertCurrencyUseCase.execute(input.toBigDecimal(), exchangeRate)
        _conversionCounter.value = _conversionCounter.value!! + 1
    }

    fun swapCurrencies() {
        exchangeRate = 1 / exchangeRate
    }

    fun resetConversionCounter() {
        _conversionCounter.value = 0
    }

    fun limitDecimal(beforeLimiting: String, maxDecimal: Int): String{
        return decimalLimitUseCase.execute(beforeLimiting,maxDecimal)
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

    fun putCurrencyInStorage(side: String, currencyInfo: CurrencyInfo){
        putCurrencyInStorageUseCase.execute(side,currencyInfo)
    }

    fun getCurrencyFromStorage(defaultValue: CurrencyInfo, sharedPrefsName: String): CurrencyInfo {
        return getCurrencyFromStorageUseCase.execute(defaultValue,sharedPrefsName)
    }

}