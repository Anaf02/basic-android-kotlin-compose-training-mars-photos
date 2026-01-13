package com.example.marsphotos.ui.screens.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.marsphotos.ui.components.TopAppBarState
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(
    photoId: String,
    imageUrl: String,
    setTopAppBarState: (TopAppBarState) -> Unit
) {
    val viewModel = koinViewModel<DetailsViewModel>()
    val state = viewModel.uiState.collectAsStateWithLifecycle().value

    LaunchedEffect(key1 = Unit) {
        setTopAppBarState(createTopAppBarState(photoId))
    }

    DetailScreenContent(
        photoId = photoId,
        imageUrl = imageUrl,
        state = state,
        setAction = viewModel::setAction
    )
}

private fun createTopAppBarState(photoId: String) = TopAppBarState(
    title = "Image id: $photoId",
    shouldDisplayBackButton = true
)
