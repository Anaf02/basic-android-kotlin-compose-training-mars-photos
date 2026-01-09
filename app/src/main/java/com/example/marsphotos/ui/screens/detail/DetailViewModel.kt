package com.example.marsphotos.ui.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class DetailUiState(
    val counter: Int = 0
)

class DetailViewModel() : ViewModel() {

    var uiState by mutableStateOf(DetailUiState())
        private set

    fun incrementCounter() {
        uiState = uiState.copy(counter = uiState.counter + 1)
    }
}
