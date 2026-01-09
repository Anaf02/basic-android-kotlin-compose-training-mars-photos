package com.example.marsphotos

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.ui.MarsPhotosApp
import com.example.marsphotos.ui.screens.DetailScreenContent
import com.example.marsphotos.ui.screens.DetailViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HomeScreen
    ) {
        composable<HomeScreen> {
            MarsPhotosApp(onNavigateToDetail = { (photoId, imgSrc) ->
                navController.navigate(DetailScreen(photoId, imgSrc))
            })
        }
        composable<DetailScreen> { backStackEntry ->
            val detailViewModel = koinViewModel<DetailViewModel>()

            val detailScreen: DetailScreen = backStackEntry.toRoute()
            DetailScreenContent(
                photo = MarsPhoto(id = detailScreen.photoId, imgSrc = detailScreen.imgSrc),
                detailUiState = detailViewModel.uiState,
                onPhotoClicked = detailViewModel::incrementCounter,
                onNavigateBack = { navController.navigateUp() }
            )
        }
    }
}

@Serializable
object HomeScreen

@Serializable
data class DetailScreen(val photoId: String, val imgSrc: String)