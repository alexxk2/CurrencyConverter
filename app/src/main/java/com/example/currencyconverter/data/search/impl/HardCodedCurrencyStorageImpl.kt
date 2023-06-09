package com.example.currencyconverter.data.search.impl

import android.content.Context
import com.example.currencyconverter.R
import com.example.currencyconverter.data.search.HardCodedCurrencyStorage
import com.example.currencyconverter.data.search.models.CurrencyInfoDto

class HardCodedCurrencyStorageImpl(private val context: Context): HardCodedCurrencyStorage {

    private val listOfCurrencies = mutableListOf(
        CurrencyInfoDto("RUB", R.string.rub , R.drawable.ru,"₽"),
        CurrencyInfoDto("USD", R.string.usd, R.drawable.us,"$"),
        CurrencyInfoDto("EUR", R.string.eur, R.drawable.eu,"€"),
        CurrencyInfoDto("AUD", R.string.aud, R.drawable.au,"$"),
        CurrencyInfoDto("BRL", R.string.brl, R.drawable.br,"R$"),
        CurrencyInfoDto("BYN", R.string.byn, R.drawable.by,"Br"),
        CurrencyInfoDto("CAD", R.string.cad, R.drawable.ca,"$"),
        CurrencyInfoDto("CNY", R.string.cny, R.drawable.cn,"¥"),
        CurrencyInfoDto("EGP", R.string.egp, R.drawable.eg,"£"),
        CurrencyInfoDto("GBP", R.string.bgp, R.drawable.gb,"£"),
        CurrencyInfoDto("GEL", R.string.gel, R.drawable.ge,"₾"),
        CurrencyInfoDto("ILS", R.string.ils, R.drawable.il,"₪"),
        CurrencyInfoDto("INR", R.string.inr, R.drawable.`in`,"₹"),
        CurrencyInfoDto("JPY", R.string.jpy, R.drawable.jp, "¥"),
        CurrencyInfoDto("KRW", R.string.krw, R.drawable.kr,"₩"),
        CurrencyInfoDto("KZT", R.string.kzt, R.drawable.kz,"₸"),
        CurrencyInfoDto("MXN", R.string.mxn, R.drawable.mx,"$"),
        CurrencyInfoDto("SAR", R.string.sar, R.drawable.sa,"﷼"),
        CurrencyInfoDto("THB", R.string.thb, R.drawable.th,"฿"),
        CurrencyInfoDto("UZS", R.string.uzs, R.drawable.uz,"Soʻm"),
        CurrencyInfoDto("BTC", R.string.btc, R.drawable.bc,"₿")
    )

    override fun getCurrencyDefaultList(): MutableList<CurrencyInfoDto> {
        return listOfCurrencies
    }

    override fun getCurrencyFilteredList(searchInput: String): MutableList<CurrencyInfoDto> {
        var filteredList = mutableListOf<CurrencyInfoDto>()
        filteredList = listOfCurrencies.filter {
            it.code.contains(searchInput,true)||context.getString(it.name).contains(searchInput,true)
        }.toMutableList()
        return filteredList
    }
}