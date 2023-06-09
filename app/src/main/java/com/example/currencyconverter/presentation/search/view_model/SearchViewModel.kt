package com.example.currencyconverter.presentation.search.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.currencyconverter.domain.repositories.SearchRepository
import com.example.currencyconverter.domain.search.GetCurrencyDefaultListUseCase
import com.example.currencyconverter.domain.search.GetCurrencyFilteredListUseCase
import com.example.currencyconverter.models.CurrencyInfo

class SearchViewModel(searchRepository: SearchRepository): ViewModel() {


    private val getCurrencyFilteredListUseCase = GetCurrencyFilteredListUseCase(searchRepository)
    private val getCurrencyDefaultListUseCase = GetCurrencyDefaultListUseCase(searchRepository)

    fun getCurrencyDefaultList(): MutableList<CurrencyInfo>{
        return getCurrencyDefaultListUseCase.execute()
    }

    fun getCurrencyFilteredList(searchInput: String): MutableList<CurrencyInfo>{
        return getCurrencyFilteredListUseCase.execute(searchInput)
    }

    companion object{
        fun getViewModelFactory(searchRepository: SearchRepository): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                SearchViewModel(searchRepository = searchRepository)
            }
        }
    }
}