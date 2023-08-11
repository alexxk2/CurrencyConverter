package com.example.currencyconverter.di

import com.example.currencyconverter.data.network.NetworkClient
import com.example.currencyconverter.data.network.impl.NetworkClientImpl
import com.example.currencyconverter.data.repositories.impl.NetworkRepositoryImpl
import com.example.currencyconverter.data.repositories.impl.SearchRepositoryImpl
import com.example.currencyconverter.data.repositories.impl.StorageRepositoryImpl
import com.example.currencyconverter.data.search.HardCodedCurrencyStorage
import com.example.currencyconverter.data.search.impl.HardCodedCurrencyStorageImpl
import com.example.currencyconverter.data.storage.SharedPrefStorage
import com.example.currencyconverter.data.storage.impl.SharedPrefStorageImpl
import com.example.currencyconverter.domain.repositories.NetworkRepository
import com.example.currencyconverter.domain.repositories.SearchRepository
import com.example.currencyconverter.domain.repositories.StorageRepository
import org.koin.dsl.module

val dataModule = module {

    single<SharedPrefStorage> { SharedPrefStorageImpl(context = get()) }

    single<StorageRepository> { StorageRepositoryImpl(sharedPrefStorage = get()) }

    single<NetworkClient> { NetworkClientImpl() }

    single<NetworkRepository> { NetworkRepositoryImpl(networkClient = get()) }

    single<HardCodedCurrencyStorage> { HardCodedCurrencyStorageImpl(context = get()) }

    single<SearchRepository> { SearchRepositoryImpl(hardCodedCurrencyStorage = get())}
    
}