package com.example.marsphotos

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.marsphotos.ui.screens.detail.DetailScreen
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
            HomeScreen(onNavigateToDetail = { (photoId, imgSrc) ->
                navController.navigate(DetailsRoute(photoId, imgSrc))
            })
        }
        composable<DetailsRoute> { backStackEntry ->
            val detailsRoute = backStackEntry.toRoute<DetailsRoute>()

            DetailScreen(
                photoId = detailsRoute.photoId,
                imgSrc = detailsRoute.imgSrc,
                onNavigateBack = { navController.navigateUp() }
            )
        }
    }
}

@Serializable
object HomeRoute

@Serializable
data class DetailsRoute(val photoId: String, val imgSrc: String)