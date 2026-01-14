package com.example.marsphotos.ui.components

import androidx.navigation.NavController

data class TopAppBarState(
    val title: String = "",
    val shouldDisplayBackButton: Boolean = false,
    val onNavigateBack: (navController: NavController) -> Unit = { it.popBackStack() },
)