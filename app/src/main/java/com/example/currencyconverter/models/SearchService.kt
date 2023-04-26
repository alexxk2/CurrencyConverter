package com.example.currencyconverter.models

import com.example.currencyconverter.R


class SearchService {


    private val listOfCurrencies = mutableListOf(
        CurrencyInfo("RUB",R.string.rub , R.drawable.ru,"₽"),
        CurrencyInfo("USD", R.string.usd, R.drawable.us,"$"),
        CurrencyInfo("EUR", R.string.eur, R.drawable.eu,"€"),
        CurrencyInfo("AUD", R.string.aud, R.drawable.au,"$"),
        CurrencyInfo("BRL", R.string.brl, R.drawable.br,"R$"),
        CurrencyInfo("BYN", R.string.byn, R.drawable.by,"Br"),
        CurrencyInfo("CAD", R.string.cad, R.drawable.ca,"$"),
        CurrencyInfo("CNY", R.string.cny, R.drawable.cn,"¥"),
        CurrencyInfo("EGP", R.string.egp, R.drawable.eg,"£"),
        CurrencyInfo("GBP", R.string.bgp, R.drawable.gb,"£"),
        CurrencyInfo("GEL", R.string.gel, R.drawable.ge,"₾"),
        CurrencyInfo("ILS", R.string.ils, R.drawable.il,"₪"),
        CurrencyInfo("INR", R.string.inr, R.drawable.`in`,"₹"),
        CurrencyInfo("JPY", R.string.jpy, R.drawable.jp, "¥"),
        CurrencyInfo("KRW", R.string.krw, R.drawable.kr,"₩"),
        CurrencyInfo("KZT", R.string.kzt, R.drawable.kz,"₸"),
        CurrencyInfo("MXN", R.string.mxn, R.drawable.mx,"$"),
        CurrencyInfo("SAR", R.string.sar, R.drawable.sa,"﷼"),
        CurrencyInfo("THB", R.string.thb, R.drawable.th,"฿"),
        CurrencyInfo("UZS", R.string.uzs, R.drawable.uz,"Soʻm"),
        CurrencyInfo("BTC", R.string.btc, R.drawable.bc,"₿")
    )
    val defaultListOfCurrencies = listOfCurrencies

}

