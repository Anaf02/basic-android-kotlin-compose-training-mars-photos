package com.example.marsphotos.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.marsphotos.model.MarsPhoto

@Composable
fun DetailScreenContent(
    photo: MarsPhoto,
    detailUiState: DetailUiState,
    onPhotoClicked: () -> Unit
) {
    Scaffold() { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(10.dp)
                .fillMaxWidth(),
        ) {
            Text(text = "Image id: ${photo.id}")
            MarsPhotoCard(
                photo = photo,
                modifier = Modifier.aspectRatio(1.5f),
                onClick = { onPhotoClicked() }
            )
            Text(text = "Click count: ${detailUiState.counter}")
        }
    }
}