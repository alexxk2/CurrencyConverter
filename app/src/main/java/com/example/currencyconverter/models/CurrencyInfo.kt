package com.example.currencyconverter.models

import android.os.Parcelable
import com.example.currencyconverter.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrencyInfo(
    val code: String,
    val name: String,
    val flag: Int
): Parcelable{
    companion object{
        @JvmStatic val DEFAULT_LEFT = CurrencyInfo("RUB", "Russian Ruble", R.drawable.ru)
        @JvmStatic val DEFAULT_RIGHT = CurrencyInfo("USD", "US Dollar", R.drawable.us)
    }

}
