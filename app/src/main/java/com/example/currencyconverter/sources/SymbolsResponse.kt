package com.example.currencyconverter.sources



data class SymbolsResponse(
    val data: CurrencyNames
)

data class CurrencyNames(
    val RUB: CurrencyCode,//Russian Ruble
    val USD: CurrencyCode,//US Dollar
    val EUR: CurrencyCode,//Euro
    val AUD: CurrencyCode,//Australian Dollar
    val BRL: CurrencyCode,//Brazilian Real
    val BYN: CurrencyCode,//Belarusian Ruble
    val CAD: CurrencyCode,//Canadian Dollar
    val CNY: CurrencyCode,//Chinese Yuan
    val EGP: CurrencyCode,//Egyptian Pound
    val GBP: CurrencyCode,//British Pound Sterling
    val GEL: CurrencyCode,//Georgian Lari
    val ILS: CurrencyCode,//Israeli New Sheqel
    val INR: CurrencyCode,//Indian Rupee
    val JPY: CurrencyCode,//Japanese Yen
    val KRW: CurrencyCode,//South Korean Won
    val KZT: CurrencyCode,//Kazakhstani Tenge
    val MXN: CurrencyCode,//Mexican Peso
    val SAR: CurrencyCode,//Saudi Riyal
    val THB: CurrencyCode,//Thai Baht
    val UZS: CurrencyCode,//Uzbekistan Som
    val BTC: CurrencyCode//Bitcoin
)

data class CurrencyCode(
    val code: String,
    val value: Float,
    val name: String,
    val flag: String
)