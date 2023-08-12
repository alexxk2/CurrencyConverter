package com.example.currencyconverter.presentation.history.models

sealed interface ScreenState{
    object Loading: ScreenState
    object Content: ScreenState
    object Error: ScreenState
    object Empty: ScreenState
}