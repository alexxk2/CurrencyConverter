package com.example.currencyconverter.models

import com.example.currencyconverter.domain.ConverterService
import org.junit.Assert.*

import org.junit.Test

class ConverterServiceTest {

    private val converterService = ConverterService()
    @Test
    fun convert() {
        assertEquals("188.02531552", converterService.convert(15296.56f.toBigDecimal(),0.012292f))
        assertEquals("0.00012292", converterService.convert(0.01f.toBigDecimal(),0.012292f))
        assertEquals("1517530850.535504", converterService.convert("123456789012".toDouble().toBigDecimal(),0.012292f))
        assertEquals("1517530850.54373964", converterService.convert("123456789012.67".toDouble().toBigDecimal(),0.012292f))
    }
}