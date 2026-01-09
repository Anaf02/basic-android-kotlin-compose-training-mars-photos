package com.example.marsphotos.ui.screens.detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class DetailUiState(
    val counter: Int = 0,
    val photoId: String = "",
    val imageUrl: String = "",
)

class DetailViewModel(
    private val photoId: String,
    private val imageUrl: String,
) : ViewModel() {
    private val _uiState: MutableStateFlow<DetailUiState> = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    init {
        populate()
    }

    fun incrementCounter() {
        _uiState.value = uiState.value.copy(counter = uiState.value.counter + 1)
    }

    private fun populate() {
        _uiState.value = uiState.value.copy(photoId = photoId, imageUrl = imageUrl)
    }
}
