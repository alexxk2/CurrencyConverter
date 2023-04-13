package com.example.currencyconverter.sources



data class SymbolsResponse(

    val data: CurrencyNames
)

data class CurrencyNames(
    val RUB: CurrencyRate,//Russian Ruble
    val USD: CurrencyRate,//US Dollar
    val EUR: CurrencyRate,//Euro
    val AUD: CurrencyRate,//Australian Dollar
    val BRL: CurrencyRate,//Brazilian Real
    val BYN: CurrencyRate,//Belarusian Ruble
    val CAD: CurrencyRate,//Canadian Dollar
    val CNY: CurrencyRate,//Chinese Yuan
    val EGP: CurrencyRate,//Egyptian Pound
    val GBP: CurrencyRate,//British Pound Sterling
    val GEL: CurrencyRate,//Georgian Lari
    val ILS: CurrencyRate,//Israeli New Sheqel
    val INR: CurrencyRate,//Indian Rupee
    val JPY: CurrencyRate,//Japanese Yen
    val KRW: CurrencyRate,//South Korean Won
    val KZT: CurrencyRate,//Kazakhstani Tenge
    val MXN: CurrencyRate,//Mexican Peso
    val SAR: CurrencyRate,//Saudi Riyal
    val THB: CurrencyRate,//Thai Baht
    val UZS: CurrencyRate,//Uzbekistan Som
    val BTC: CurrencyRate//Bitcoin
)

data class CurrencyRate(
    val code: String,
    val value: Float,
    val name: String,
    val flag: String
)