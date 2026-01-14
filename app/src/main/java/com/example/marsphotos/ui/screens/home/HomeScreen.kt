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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.ui.HandleEffects
import com.example.marsphotos.ui.components.TopAppBarState
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    setTopAppBarState: (TopAppBarState) -> Unit,
    navigateToDetails: (MarsPhoto) -> Unit
) {
    val viewModel = koinViewModel<HomeViewModel>()
    val state = viewModel.uiState.collectAsStateWithLifecycle().value

    LaunchedEffect(key1 = Unit) {
        setTopAppBarState(createTopAppBarState())
    }

    HomeScreenContent(
        state = state,
        setAction = viewModel::setAction
    )

    HandleEffects(
        effects = viewModel.effect,
        handleEffect = {
            handleEffect(
                effect = it,
                navigateToDetails = navigateToDetails
            )
        }
    )
}

private fun handleEffect(
    effect: HomeContract.HomeEffect,
    navigateToDetails: (MarsPhoto) -> Unit,
) {
    when (effect) {
        is HomeContract.HomeEffect.NavigateToDetails -> navigateToDetails.invoke(effect.photo)
    }
}

private fun createTopAppBarState() = TopAppBarState(
    title = "Mars Photos",
    shouldDisplayBackButton = false
)