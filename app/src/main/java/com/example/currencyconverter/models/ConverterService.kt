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

}