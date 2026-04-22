package com.example.marsphotos.ui.components

data class TopAppBarState(
    val title: String = "",
    val shouldDisplayBackButton: Boolean = false,
    val onNavigateBack: () -> Unit = {},
)
