/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.marsphotos.ui.screens.home

import androidx.lifecycle.viewModelScope
import com.example.marsphotos.data.MarsPhotosRepository
import com.example.marsphotos.ui.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(
    private val marsPhotosRepository: MarsPhotosRepository
) : BaseViewModel<HomeContract.HomeAction, HomeContract.HomeState, HomeContract.HomeEffect>() {

    override fun setInitialState() = HomeContract.HomeState()

    init {
        getMarsPhotos()
    }

    override fun handleViewAction(action: HomeContract.HomeAction) {
        when (action) {
            is HomeContract.HomeAction.OnPhotoClicked -> {
                setEffect { HomeContract.HomeEffect.NavigateToDetails(action.photo) }
            }

            is HomeContract.HomeAction.LoadPhotos -> {
                getMarsPhotos()
            }
        }
    }

    private fun getMarsPhotos() {
        viewModelScope.launch {
            setState { copy(isLoading = true, error = null) }
            try {
                val photos = marsPhotosRepository.getMarsPhotos()
                setState { copy(isLoading = false, photos = photos) }
            } catch (e: Exception) {
                setState { copy(isLoading = false, error = e.message) }
            }
        }
    }
}
