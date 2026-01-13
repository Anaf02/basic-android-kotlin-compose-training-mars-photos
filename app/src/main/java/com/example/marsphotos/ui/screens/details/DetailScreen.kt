package com.example.marsphotos.ui.screens.details

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.ui.HandleEffects
import com.example.marsphotos.ui.components.MarsPhotoCard
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

    HandleEffects(
        effects = viewModel.effect,
        handleEffect = {
            handleEffect(
                effect = it,
                navigateBack = { viewModel.setAction(DetailsContract.DetailsAction.NavigateBack) }
            )
        }
    )
}

@Composable
fun DetailScreenContent(
    photoId: String,
    imageUrl: String,
    state: DetailsContract.DetailsState,
    setAction: (DetailsContract.DetailsAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val photo = MarsPhoto(id = photoId, imageUrl = imageUrl)

    LazyColumn(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
    ) {
        item {
            MarsPhotoCard(
                photo = photo,
                modifier = Modifier.aspectRatio(1.5f),
                onClick = { setAction(DetailsContract.DetailsAction.IncrementCounter) }
            )
        }
        item {
            Text(
                text = "Click count: ${state.counter}",
                textAlign = TextAlign.Center,
                modifier = modifier.fillMaxWidth()
            )
        }
    }
}

private fun handleEffect(
    effect: DetailsContract.DetailsEffect,
    navigateBack: () -> Unit,
) {
    when (effect) {
        is DetailsContract.DetailsEffect.NavigateBack -> navigateBack.invoke()
    }
}

private fun createTopAppBarState(photoId: String) = TopAppBarState(
    title = "Image id: $photoId",
    shouldDisplayBackButton = true
)
