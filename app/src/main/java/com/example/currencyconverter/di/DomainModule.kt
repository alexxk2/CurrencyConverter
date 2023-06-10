package com.example.currencyconverter.di

import com.example.currencyconverter.domain.converter.ConvertCurrencyUseCase
import com.example.currencyconverter.domain.converter.DecimalLimitUseCase
import com.example.currencyconverter.domain.network.GetExchangeRateUseCase
import com.example.currencyconverter.domain.search.GetCurrencyDefaultListUseCase
import com.example.currencyconverter.domain.search.GetCurrencyFilteredListUseCase
import com.example.currencyconverter.domain.storage.GetCurrencyFromStorageUseCase
import com.example.currencyconverter.domain.storage.PutCurrencyInStorageUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<ConvertCurrencyUseCase> { ConvertCurrencyUseCase() }

    factory<DecimalLimitUseCase> { DecimalLimitUseCase() }

    factory<GetExchangeRateUseCase> { GetExchangeRateUseCase(networkRepository = get()) }

    factory<GetCurrencyDefaultListUseCase> { GetCurrencyDefaultListUseCase(searchRepository = get()) }

    factory<GetCurrencyFilteredListUseCase> { GetCurrencyFilteredListUseCase(searchRepository = get()) }

    factory<GetCurrencyFromStorageUseCase> { GetCurrencyFromStorageUseCase(storageRepository = get()) }

    factory<PutCurrencyInStorageUseCase> { PutCurrencyInStorageUseCase(storageRepository = get()) }

}