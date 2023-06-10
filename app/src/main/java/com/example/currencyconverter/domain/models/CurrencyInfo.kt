package com.example.currencyconverter.domain.models

import android.os.Parcelable
import com.example.currencyconverter.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrencyInfo(
    val code: String,
    val name: Int,
    val flag: Int,
    val symbol: String
): Parcelable{
    companion object{
        @JvmStatic val DEFAULT_LEFT = CurrencyInfo("RUB", R.string.rub, R.drawable.ru,"₽")
        @JvmStatic val DEFAULT_RIGHT = CurrencyInfo("USD", R.string.usd, R.drawable.us,"$")
    }

}
