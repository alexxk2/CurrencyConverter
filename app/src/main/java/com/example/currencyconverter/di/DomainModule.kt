package com.example.currencyconverter.di

import com.example.currencyconverter.domain.converter.ConvertCurrencyUseCase
import com.example.currencyconverter.domain.converter.DecimalLimitUseCase
import com.example.currencyconverter.domain.network.GetExchangeRateUseCase
import com.example.currencyconverter.domain.search.GetCurrencyDefaultListUseCase
import com.example.currencyconverter.domain.search.GetCurrencyFilteredListUseCase
import com.example.currencyconverter.domain.storage.AddNewHistoryItemUseCase
import com.example.currencyconverter.domain.storage.ClearHistoryUseCase
import com.example.currencyconverter.domain.storage.DeleteHistoryItemUseCase
import com.example.currencyconverter.domain.storage.GetAllHistoryUseCase
import com.example.currencyconverter.domain.storage.GetCurrencyFromStorageUseCase
import com.example.currencyconverter.domain.storage.GetHistoryItemUseCase
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

    factory<AddNewHistoryItemUseCase> { AddNewHistoryItemUseCase(storageRepository = get()) }

    factory<ClearHistoryUseCase> { ClearHistoryUseCase(storageRepository = get()) }

    factory<DeleteHistoryItemUseCase> { DeleteHistoryItemUseCase(storageRepository = get()) }

    factory<GetAllHistoryUseCase> { GetAllHistoryUseCase(storageRepository = get()) }

    factory<GetHistoryItemUseCase> { GetHistoryItemUseCase(storageRepository = get()) }

}