package com.example.marsphotos.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    navController: NavController,
    topAppBarState: State<TopAppBarState>,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = topAppBarState.value.title,
                style = MaterialTheme.typography.headlineSmall
            )
        },
        modifier = modifier,
        navigationIcon = {
            AnimatedVisibility(
                visible = topAppBarState.value.shouldDisplayBackButton,
                enter = expandHorizontally(tween(durationMillis = 200, easing = LinearEasing)),
                exit = shrinkHorizontally(tween(durationMillis = 200, easing = LinearEasing)),
            ) {
                IconButton(
                    onClick = { topAppBarState.value.onNavigateBack(navController) }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}