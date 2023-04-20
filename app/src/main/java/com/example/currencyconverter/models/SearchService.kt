package com.example.currencyconverter.models

import com.example.currencyconverter.R


class SearchService {

    private val defaultList = mutableListOf(
        CurrencyInfo("RUB", "Russian Ruble", R.drawable.ru,"₽"),
        CurrencyInfo("USD", "US Dollar", R.drawable.us,"$"),
        CurrencyInfo("EUR", "Euro", R.drawable.eu,"€"),
        CurrencyInfo("AUD", "Australian Dollar", R.drawable.au,"$"),
        CurrencyInfo("BRL", "Brazilian Real", R.drawable.br,"R$"),
        CurrencyInfo("BYN", "Belarusian Ruble", R.drawable.by,"Br"),
        CurrencyInfo("CAD", "Canadian Dollar", R.drawable.ca,"$"),
        CurrencyInfo("CNY", "Chinese Yuan", R.drawable.cn,"¥"),
        CurrencyInfo("EGP", "Egyptian Pound", R.drawable.eg,"£"),
        CurrencyInfo("GBP", "British Pound Sterling", R.drawable.gb,"£"),
        CurrencyInfo("GEL", "Georgian Lari", R.drawable.ge,"₾"),
        CurrencyInfo("ILS", "Israeli New Sheqel", R.drawable.il,"₪"),
        CurrencyInfo("INR", "Indian Rupee", R.drawable.`in`,"₹"),
        CurrencyInfo("JPY", "Japanese Yen", R.drawable.jp, "¥"),
        CurrencyInfo("KRW", "South Korean Won", R.drawable.kr,"₩"),
        CurrencyInfo("KZT", "Kazakhstani Tenge", R.drawable.kz,"₸"),
        CurrencyInfo("MXN", "Mexican Peso", R.drawable.mx,"$"),
        CurrencyInfo("SAR", "Saudi Riyal", R.drawable.sa,"﷼"),
        CurrencyInfo("THB", "Thai Baht", R.drawable.th,"฿"),
        CurrencyInfo("UZS", "Uzbekistan Som", R.drawable.uz,"Soʻm"),
        CurrencyInfo("BTC", "Bitcoin", R.drawable.bc,"₿")
    )
    val listOfCurrencies = defaultList

     fun searchFilter(searchInput: String): MutableList<CurrencyInfo>{
        var tempList = mutableListOf<CurrencyInfo>()
        tempList = listOfCurrencies.filter {
            it.code.contains(searchInput,true)||it.name.contains(searchInput,true)
        }.toMutableList()
         return tempList
    }
}

