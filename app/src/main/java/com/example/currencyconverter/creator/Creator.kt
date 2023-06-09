package com.example.currencyconverter.creator

import android.content.Context
import com.example.currencyconverter.data.network.impl.NetworkClientImpl
import com.example.currencyconverter.data.repositories.impl.NetworkRepositoryImpl
import com.example.currencyconverter.data.repositories.impl.SearchRepositoryImpl
import com.example.currencyconverter.data.repositories.impl.StorageRepositoryImpl
import com.example.currencyconverter.data.search.HardCodedCurrencyStorage
import com.example.currencyconverter.data.search.impl.HardCodedCurrencyStorageImpl
import com.example.currencyconverter.data.storage.impl.SharedPrefStorageImpl
import com.example.currencyconverter.domain.repositories.NetworkRepository
import com.example.currencyconverter.domain.repositories.SearchRepository
import com.example.currencyconverter.domain.repositories.StorageRepository

object Creator {

    fun provideNetworkRepository(): NetworkRepository{
        return NetworkRepositoryImpl(NetworkClientImpl())
    }

    fun provideSearchRepository(context: Context): SearchRepository{
        return SearchRepositoryImpl(HardCodedCurrencyStorageImpl(context))
    }

    fun provideStorageRepository(context: Context): StorageRepository {
        return StorageRepositoryImpl(SharedPrefStorageImpl(context))
    }
}