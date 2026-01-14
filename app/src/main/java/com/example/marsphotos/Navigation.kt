package com.example.marsphotos

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.marsphotos.ui.components.TopAppBar
import com.example.marsphotos.ui.components.TopAppBarState
import com.example.marsphotos.ui.screens.details.DetailsScreen
import com.example.marsphotos.ui.screens.home.HomeScreen
import kotlinx.serialization.Serializable

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val topAppBarState = remember { mutableStateOf(TopAppBarState()) }

    Scaffold(
        topBar = {
            TopAppBar(
                navController = navController,
                topAppBarState = topAppBarState,
                modifier = Modifier
            )
        }
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = HomeRoute,
            modifier = Modifier.padding(contentPadding)
        ) {
            composable<HomeRoute> {
                HomeScreen(
                    setTopAppBarState = { topAppBarState.value = it },
                    navigateToDetails = { (photoId, imageUrl) ->
                        navController.navigate(DetailsRoute(photoId, imageUrl))
                    }
                )
            }
            composable<DetailsRoute> { backStackEntry ->
                val detailsRoute = backStackEntry.toRoute<DetailsRoute>()

                DetailsScreen(
                    photoId = detailsRoute.photoId,
                    imageUrl = detailsRoute.imageUrl,
                    setTopAppBarState = { topAppBarState.value = it }
                )
            }
        }
    }
}

@Serializable
object HomeRoute

@Serializable
data class DetailsRoute(val photoId: String, val imageUrl: String)