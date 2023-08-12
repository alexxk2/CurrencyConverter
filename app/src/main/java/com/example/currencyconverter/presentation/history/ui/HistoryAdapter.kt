package com.example.currencyconverter.presentation.history.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.currencyconverter.R
import com.example.currencyconverter.databinding.HistoryItemBinding
import com.example.currencyconverter.domain.models.HistoryInfo

class HistoryAdapter(
    private val context: Context,
    private val actionListener: HistoryActionListener
    ) : ListAdapter<HistoryInfo, HistoryAdapter.HistoryViewHolder>(DiffCallBack),
    View.OnClickListener {


    class HistoryViewHolder(val binding: HistoryItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryAdapter.HistoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HistoryItemBinding.inflate(inflater, parent, false)

        binding.root.setOnClickListener(this)
        binding.currencyCardView.setOnClickListener(this)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryAdapter.HistoryViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding){

            Glide.with(context)
                .load(item.startFlagSrc)
                .into(currencyStartFlagImage)

            Glide.with(context)
                .load(item.endFlagSrc)
                .into(currencyEndFlagImage)

            Glide.with(context)
                .load(R.drawable.ic_arrow_right)
                .into(arrowImage)

                startCurrencyInfo.text = item.inputValue
                endCurrencyInfo.text = item.resultValue

            root.tag =item
            currencyCardView.tag = item
        }

    }

    override fun onClick(v: View?) {
        val historyInfo = v?.tag as HistoryInfo
        when(v.id){
            R.id.currency_card_view -> actionListener.onClickItem(historyInfo)
            else -> actionListener.onClickItem(historyInfo)
        }
    }

    interface HistoryActionListener {
        fun onClickItem(historyInfo: HistoryInfo)
    }

    companion object {
        val DiffCallBack = object : DiffUtil.ItemCallback<HistoryInfo>() {

            override fun areItemsTheSame(oldItem: HistoryInfo, newItem: HistoryInfo): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: HistoryInfo, newItem: HistoryInfo): Boolean {
                return oldItem == newItem
            }
        }
    }
}