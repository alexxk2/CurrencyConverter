package com.example.currencyconverter.presentation.history.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.domain.models.HistoryInfo
import com.example.currencyconverter.domain.storage.ClearHistoryUseCase
import com.example.currencyconverter.domain.storage.GetAllHistoryUseCase
import com.example.currencyconverter.presentation.history.models.ScreenState
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val getAllHistoryUseCase: GetAllHistoryUseCase,
    private val clearHistoryUseCase: ClearHistoryUseCase,

    ) : ViewModel() {

    private val _screenState = MutableLiveData<ScreenState>()
    val screenState: LiveData<ScreenState> = _screenState

    private val _historyData = MutableLiveData<List<HistoryInfo>>()
    val historyData: LiveData<List<HistoryInfo>> = _historyData


    fun getAllHistory() {

        _screenState.value = ScreenState.Loading

        viewModelScope.launch {

            try {
                _historyData.value = getAllHistoryUseCase.execute()
                _screenState.value =
                    if (historyData.value?.isEmpty() == true) ScreenState.Empty else ScreenState.Content

            } catch (e: Exception) {
                _screenState.value = ScreenState.Error
            }

        }
    }

    fun clearHistory(){
        _screenState.value = ScreenState.Loading

        viewModelScope.launch {
            clearHistoryUseCase.execute()
            _screenState.value = ScreenState.Empty
        }

    }

}