package com.example.marsphotos

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.marsphotos.ui.screens.details.DetailsScreen
import com.example.marsphotos.ui.screens.home.HomeScreen
import kotlinx.serialization.Serializable

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        composable<HomeRoute> {
            HomeScreen(navigateToDetails = { (photoId, imageUrl) ->
                navController.navigate(DetailsRoute(photoId, imageUrl))
            })
        }
        composable<DetailsRoute> { backStackEntry ->
            val detailsRoute = backStackEntry.toRoute<DetailsRoute>()

            DetailsScreen(
                photoId = detailsRoute.photoId,
                imageUrl = detailsRoute.imageUrl,
                navigateBack = { navController.navigateUp() }
            )
        }
    }
}

@Serializable
object HomeRoute

@Serializable
data class DetailsRoute(val photoId: String, val imageUrl: String)