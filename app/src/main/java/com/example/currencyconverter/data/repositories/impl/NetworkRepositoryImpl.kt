package com.example.currencyconverter.data.repositories.impl

import com.example.currencyconverter.data.network.NetworkClient
import com.example.currencyconverter.data.network.dto.CurrencyRequestEntity
import com.example.currencyconverter.data.network.dto.CurrencyResponseEntity
import com.example.currencyconverter.domain.repositories.NetworkRepository

class NetworkRepositoryImpl(private val networkClient: NetworkClient) : NetworkRepository {

    override suspend fun getExchangeRate(baseCurrency: String, currencies: String): Float {

        val dataRequestArgument = mapToData(baseCurrency)
        val dataResult = networkClient.getAllCurrenciesRates(dataRequestArgument)

        if (dataResult.resultCode == 200){
            return when(currencies){
                "RUB" -> (dataResult as CurrencyResponseEntity).data.RUB.value
                "USD" -> (dataResult as CurrencyResponseEntity).data.USD.value
                "EUR" -> (dataResult as CurrencyResponseEntity).data.EUR.value
                "AUD" -> (dataResult as CurrencyResponseEntity).data.AUD.value
                "BRL" -> (dataResult as CurrencyResponseEntity).data.BRL.value
                "BYN" -> (dataResult as CurrencyResponseEntity).data.BYN.value
                "CAD" -> (dataResult as CurrencyResponseEntity).data.CAD.value
                "CNY" -> (dataResult as CurrencyResponseEntity).data.CNY.value
                "EGP" -> (dataResult as CurrencyResponseEntity).data.EGP.value
                "GBP" -> (dataResult as CurrencyResponseEntity).data.GBP.value
                "GEL" -> (dataResult as CurrencyResponseEntity).data.GEL.value
                "ILS" -> (dataResult as CurrencyResponseEntity).data.ILS.value
                "INR" -> (dataResult as CurrencyResponseEntity).data.INR.value
                "JPY" -> (dataResult as CurrencyResponseEntity).data.JPY.value
                "KRW" -> (dataResult as CurrencyResponseEntity).data.KRW.value
                "KZT" -> (dataResult as CurrencyResponseEntity).data.KZT.value
                "MXN" -> (dataResult as CurrencyResponseEntity).data.MXN.value
                "SAR" -> (dataResult as CurrencyResponseEntity).data.SAR.value
                "THB" -> (dataResult as CurrencyResponseEntity).data.THB.value
                "UZS" -> (dataResult as CurrencyResponseEntity).data.UZS.value
                else -> (dataResult as CurrencyResponseEntity).data.BTC.value
            }
        }
        else return -1.0f
    }

    private fun mapToData(baseCurrency: String): CurrencyRequestEntity =
        CurrencyRequestEntity(baseCurrency = baseCurrency)

}