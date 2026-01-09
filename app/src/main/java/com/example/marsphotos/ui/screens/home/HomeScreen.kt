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

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marsphotos.R
import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.ui.components.PhotosGridScreen
import com.example.marsphotos.ui.components.TopAppBar
import com.example.marsphotos.ui.theme.MarsPhotosTheme
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToDetail: (MarsPhoto) -> Unit
) {
    val viewModel = koinViewModel<HomeViewModel>()
    val uiState: HomeUiState = viewModel.homeUiState
    val retryAction: () -> Unit = viewModel::getMarsPhotos

    Scaffold(
        topBar = {
            TopAppBar(
                title = stringResource(R.string.app_name)
            )
        }
    ) { contentPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            HomeScreenContent(uiState, retryAction, onNavigateToDetail)
        }
    }
}

@Composable
fun HomeScreenContent(
    homeUiState: HomeUiState,
    retryAction: () -> Unit,
    onNavigateToDetail: (MarsPhoto) -> Unit,
    modifier: Modifier = Modifier
) {
    when (homeUiState) {
        is HomeUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is HomeUiState.Success -> PhotosGridScreen(
            homeUiState.photos,
            modifier,
            onCellClicked = { marsPhoto ->
                onNavigateToDetail(marsPhoto)
            })

        is HomeUiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
    }
}


@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading_img),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun ResultScreen(photos: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Text(text = photos)
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    MarsPhotosTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    MarsPhotosTheme {
        ErrorScreen({})
    }
}

@Preview(showBackground = true)
@Composable
fun PhotosGridScreenPreview() {
    MarsPhotosTheme {
        val mockData = List(10) { MarsPhoto("$it", "") }
        PhotosGridScreen(mockData, onCellClicked = {})
    }
}
