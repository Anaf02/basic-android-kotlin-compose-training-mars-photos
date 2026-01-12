package com.example.marsphotos.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

@Composable
fun <T> HandleEffects(
    effects: Flow<T>,
    handleEffect: (T) -> Unit
) {
    LaunchedEffect(Unit) {
        effects.collect { effect ->
            handleEffect(effect)
        }
    }
}
