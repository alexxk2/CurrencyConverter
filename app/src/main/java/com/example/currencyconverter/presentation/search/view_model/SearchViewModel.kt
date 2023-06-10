package com.example.currencyconverter.presentation.search.view_model

import androidx.lifecycle.ViewModel
import com.example.currencyconverter.domain.repositories.SearchRepository
import com.example.currencyconverter.domain.repositories.StorageRepository
import com.example.currencyconverter.domain.search.GetCurrencyDefaultListUseCase
import com.example.currencyconverter.domain.search.GetCurrencyFilteredListUseCase
import com.example.currencyconverter.domain.storage.PutCurrencyInStorageUseCase
import com.example.currencyconverter.domain.models.CurrencyInfo

class SearchViewModel(searchRepository: SearchRepository, storageRepository: StorageRepository): ViewModel() {


    private val getCurrencyFilteredListUseCase = GetCurrencyFilteredListUseCase(searchRepository)
    private val getCurrencyDefaultListUseCase = GetCurrencyDefaultListUseCase(searchRepository)
    private val putCurrencyInStorageUseCase = PutCurrencyInStorageUseCase(storageRepository)

    fun getCurrencyDefaultList(): MutableList<CurrencyInfo>{
        return getCurrencyDefaultListUseCase.execute()
    }

    fun getCurrencyFilteredList(searchInput: String): MutableList<CurrencyInfo>{
        return getCurrencyFilteredListUseCase.execute(searchInput)
    }

    fun putCurrencyInStorage(side: String, currencyInfo: CurrencyInfo){
        putCurrencyInStorageUseCase.execute(side,currencyInfo)
    }

}