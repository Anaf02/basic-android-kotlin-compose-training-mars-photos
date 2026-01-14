package com.example.marsphotos.ui.screens.details

import com.example.marsphotos.ui.UiState
import com.example.marsphotos.ui.ViewAction
import com.example.marsphotos.ui.ViewSideEffect

interface DetailsContract {
    sealed class DetailsAction() : ViewAction {
        data object IncrementCounter : DetailsAction()
    }

    data class DetailsState(
        val counter: Int = 0
    ) : UiState

    sealed class DetailsEffect() : ViewSideEffect {
    }
}