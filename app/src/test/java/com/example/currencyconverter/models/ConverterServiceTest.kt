package com.example.currencyconverter.models

import com.example.currencyconverter.domain.converter.ConvertCurrencyUseCase
import org.junit.Assert.*

import org.junit.Test

class ConverterServiceTest {

    private val convertCurrencyUseCase = ConvertCurrencyUseCase()
    @Test
    fun convert() {
        assertEquals("188.02531552", convertCurrencyUseCase.execute(15296.56f.toBigDecimal(),0.012292f))
        assertEquals("0.00012292", convertCurrencyUseCase.execute(0.01f.toBigDecimal(),0.012292f))
        assertEquals("1517530850.535504", convertCurrencyUseCase.execute("123456789012".toDouble().toBigDecimal(),0.012292f))
        assertEquals("1517530850.54373964", convertCurrencyUseCase.execute("123456789012.67".toDouble().toBigDecimal(),0.012292f))
    }
}