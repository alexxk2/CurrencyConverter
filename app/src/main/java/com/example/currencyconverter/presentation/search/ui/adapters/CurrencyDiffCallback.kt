package com.example.currencyconverter.presentation.search.ui.adapters


import androidx.recyclerview.widget.DiffUtil
import com.example.currencyconverter.models.CurrencyInfo

class CurrencyDiffCallback(
    private val oldList: MutableList<CurrencyInfo>,
    private val newList: MutableList<CurrencyInfo>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int  = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCurrency = oldList[oldItemPosition]
        val newCurrency = newList[newItemPosition]
        return oldCurrency.code == newCurrency.code
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCurrency = oldList[oldItemPosition]
        val newCurrency = newList[newItemPosition]
        return oldCurrency == newCurrency
    }
}