package com.example.currencyconverter.sources



data class SymbolsResponse(

    val data: CurrencyNames
)

data class CurrencyNames(
    val RUB: Currency,
    val USD: Currency,
    val EUR: Currency,
    val AUD: Currency,//Australian Dollar
    val BRL: Currency,//Brazilian Real
    val BYR: Currency,//Belarusian Ruble
    val CAD: Currency,//Canadian Dollar
    val CNY: Currency,//Chinese Yuan
    val CZK: Currency,//Czech Republic Koruna
    val EGP: Currency,//Egyptian Pound
    val GBP: Currency,//British Pound Sterling
    val GEL: Currency,//Georgian Lari
    val ILS: Currency,//Israeli New Sheqel
    val INR: Currency,//Indian Rupee
    val JPY: Currency,//Japanese Yen
    val KRW: Currency,//South Korean Won
    val KZT: Currency,//Kazakhstani Tenge
    val MXN: Currency,//Mexican Peso
    val SAR: Currency,//Saudi Riyal
    val THB: Currency,//Thai Baht
    val UZS: Currency,//Uzbekistan Som
    val BTC: Currency,//Bitcoin

)

data class Currency(
    val code: String,
    val value: Float
)