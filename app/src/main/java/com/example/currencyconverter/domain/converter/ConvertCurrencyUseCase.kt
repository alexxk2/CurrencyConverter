package com.example.currencyconverter.domain.converter

import java.math.BigDecimal

class ConvertCurrencyUseCase {

    fun execute(currencyInput: BigDecimal, exchangeRate: Float): String {
        val rate = exchangeRate.toBigDecimal()
        return "${currencyInput * rate}"
    }
}