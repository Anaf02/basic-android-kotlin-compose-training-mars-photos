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
        startDestination = HomeScreen
    ) {
        composable<HomeScreen> {
            HomeScreen(onNavigateToDetail = { (photoId, imgSrc) ->
                navController.navigate(DetailScreen(photoId, imgSrc))
            })
        }
        composable<DetailScreen> { backStackEntry ->
            val detailScreen: DetailScreen = backStackEntry.toRoute()

            DetailScreen(
                photoId = detailScreen.photoId,
                imgSrc = detailScreen.imgSrc,
                onNavigateBack = { navController.navigateUp() }
            )
        }
    }
}

@Serializable
object HomeScreen

@Serializable
data class DetailScreen(val photoId: String, val imgSrc: String)