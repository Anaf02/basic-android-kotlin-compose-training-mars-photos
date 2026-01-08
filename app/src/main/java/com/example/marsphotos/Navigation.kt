package com.example.marsphotos

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marsphotos.ui.MarsPhotosApp
import com.example.marsphotos.ui.screens.HomeScreen
import com.example.marsphotos.ui.screens.MarsViewModel
import kotlinx.serialization.Serializable

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HomeScreen
    ){
        composable<HomeScreen>{
            val marsViewModel : MarsViewModel = viewModel(factory = MarsViewModel.Factory)
            MarsPhotosApp()
        }
        composable<DetailScreen> {
            DetailScreen
        }
    }
}

@Serializable
object HomeScreen

@Serializable
object DetailScreen