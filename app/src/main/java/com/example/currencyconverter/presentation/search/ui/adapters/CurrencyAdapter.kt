package com.example.currencyconverter.presentation.search.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.databinding.CurrencyItemBinding
import com.example.currencyconverter.domain.models.CurrencyInfo


class CurrencyAdapter(
    private var dataSet: MutableList<CurrencyInfo>,
    private val context: Context,
    private val actionListener: CurrencyActionListener

) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>(), View.OnClickListener {

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

        binding.constraintView.setOnClickListener(this)
        binding.root.setOnClickListener(this)

        return CurrencyViewHolder(binding)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val item = dataSet[position]

        val itemName = context.getString(item.name)
        val currencyTextInfo =
            context.getString(R.string.currency_text_recycler_view, itemName, item.code)

        with(holder.binding) {

            currencyFlagImage.setImageResource(item.flag)
            currencyInfo.text = currencyTextInfo
            constraintView.tag = item
            root.tag = item
        }

    }

    override fun onClick(v: View?) {
        val currencyInfo = v?.tag as CurrencyInfo
        when(v.id){
            R.id.constraint_view-> actionListener.onClickCurrency(currencyInfo)
            else -> actionListener.onClickCurrency(currencyInfo)
        }
    }

    interface CurrencyActionListener {
        fun onClickCurrency(currencyInfo: CurrencyInfo)
    }


}