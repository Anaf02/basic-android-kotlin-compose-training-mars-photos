package com.example.marsphotos.ui.screens

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenContent(
    photo: MarsPhoto,
    detailUiState: DetailUiState,
    onPhotoClicked: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
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
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
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
}