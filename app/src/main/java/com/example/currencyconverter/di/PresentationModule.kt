package com.example.currencyconverter.di

import com.example.currencyconverter.presentation.converter.view_model.ConverterViewModel
import com.example.currencyconverter.presentation.search.view_model.SearchViewModel
import com.example.currencyconverter.presentation.settings.view_model.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel<ConverterViewModel> {
        ConverterViewModel(networkRepository = get(), storageRepository = get())
    }

    viewModel<SearchViewModel> {
        SearchViewModel(searchRepository = get(), storageRepository = get())
    }

    viewModel<SettingsViewModel> {
        SettingsViewModel()
    }
}