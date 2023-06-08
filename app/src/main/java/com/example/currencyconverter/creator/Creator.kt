package com.example.currencyconverter.creator

import com.example.currencyconverter.data.network.impl.NetworkClientImpl
import com.example.currencyconverter.data.repositories.impl.NetworkRepositoryImpl
import com.example.currencyconverter.domain.repositories.NetworkRepository

object Creator {

    fun provideNetworkRepository(): NetworkRepository{
        return NetworkRepositoryImpl(NetworkClientImpl())
    }
}