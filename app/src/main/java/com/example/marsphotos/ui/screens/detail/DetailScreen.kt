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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.ui.components.MarsPhotoCard
import com.example.marsphotos.ui.components.TopAppBar
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    photoId: String,
    imgSrc: String,
    onNavigateBack: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val viewModel = koinViewModel<DetailViewModel>()
    val onPhotoClicked = viewModel::incrementCounter
    val uiState = viewModel.uiState
    val photo = MarsPhoto(id = photoId, imgSrc = imgSrc)

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = "Image id: ${photo.id}",
                scrollBehavior = scrollBehavior,
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

            DetailScreenContent(uiState, photo, onPhotoClicked)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenContent(
    detailUiState: DetailUiState,
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
                text = "Click count: ${detailUiState.counter}",
                textAlign = TextAlign.Center,
                modifier = modifier.fillMaxWidth()
            )
        }

    }
}