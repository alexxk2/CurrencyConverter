package com.example.currencyconverter.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.databinding.CurrencyItemBinding
import com.example.currencyconverter.models.CurrencyInfo


class CurrencyAdapter(
    private var dataSet: MutableList<CurrencyInfo>,
    private val context: Context
) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    fun updateDataSet(newList: MutableList<CurrencyInfo>) {
        val diffCallback = CurrencyDiffCallback(dataSet,newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        dataSet = newList
        diffResult.dispatchUpdatesTo(this)
    }

    class CurrencyViewHolder(val binding: CurrencyItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CurrencyItemBinding.inflate(inflater, parent, false)
        return CurrencyViewHolder(binding)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val item = dataSet[position]

        val currencyTextInfo =
            context.getString(R.string.currency_text_recycler_view, item.name, item.code)

        with(holder.binding) {

            currencyFlagImage.setImageResource(item.flag)
            currencyInfo.text = currencyTextInfo
        }


    }
}