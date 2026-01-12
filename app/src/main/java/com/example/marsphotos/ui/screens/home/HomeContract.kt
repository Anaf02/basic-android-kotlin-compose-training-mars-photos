package com.example.marsphotos.ui.screens.home

import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.ui.UiState
import com.example.marsphotos.ui.ViewAction
import com.example.marsphotos.ui.ViewSideEffect

interface HomeContract {

    sealed class HomeAction : ViewAction {
        data object LoadPhotos : HomeAction()
        data class OnPhotoClicked(val photo: MarsPhoto) : HomeAction()
    }

    data class HomeState(
        val photos: List<MarsPhoto> = emptyList(),
        val isLoading: Boolean = true,
        val error: String? = ""
    ) : UiState

    sealed class HomeEffect : ViewSideEffect {
        data class NavigateToDetails(val photo: MarsPhoto) : HomeEffect()
    }
}