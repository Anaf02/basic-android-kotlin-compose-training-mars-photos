package com.example.marsphotos.ui.screens.detail

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.ui.components.MarsPhotoCard
import com.example.marsphotos.ui.components.TopAppBar
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    photoId: String,
    imgSrc: String,
    onNavigateBack: () -> Unit,
) {
    val viewModel = koinViewModel<DetailViewModel> { parametersOf(photoId, imgSrc) }
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value

    val photo = MarsPhoto(id = photoId, imgSrc = imgSrc)

    Scaffold(
        topBar = {
            TopAppBar(
                title = "Image id: ${photo.id}",
                enableBackNavigation = true,
                onNavigateBack = onNavigateBack
            )
        }
    ) { contentPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            DetailScreenContent(
                state = uiState,
                photo = photo,
                onPhotoClicked = viewModel::incrementCounter
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenContent(
    state: DetailUiState,
    photo: MarsPhoto,
    onPhotoClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
    ) {
        item {
            MarsPhotoCard(
                photo = photo,
                modifier = Modifier.aspectRatio(1.5f),
                onClick = { onPhotoClicked() }
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