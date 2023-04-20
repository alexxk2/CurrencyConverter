package com.example.currencyconverter.models

import java.math.BigDecimal


open class ConverterService {

    fun convert(
        currencyInput: BigDecimal,
        exchangeRate: Float
    ): String {
        val rate = exchangeRate.toBigDecimal()
        return "${currencyInput * rate}"
    }

    fun calculateReverse(
        currencyInput: BigDecimal,
        exchangeRate: Float
    ): String {
        val input = currencyInput
        val rate = exchangeRate.toBigDecimal()
        return "${input / rate}"
    }

}