package com.example.marsphotos

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.marsphotos.ui.components.TopAppBar
import com.example.marsphotos.ui.components.TopAppBarState
import com.example.marsphotos.ui.screens.details.DetailsScreen
import com.example.marsphotos.ui.screens.home.HomeScreen
import kotlinx.serialization.Serializable

@Composable
fun Navigation() {
    val backStack = rememberNavBackStack(HomeRoute)
    val topAppBarState = remember { mutableStateOf(TopAppBarState()) }

    Scaffold(
        topBar = {
            TopAppBar(
                topAppBarState = topAppBarState,
                modifier = Modifier
            )
        }
    ) { contentPadding ->
        NavDisplay(
            backStack = backStack,
            modifier = Modifier.padding(contentPadding),
            onBack = { backStack.removeLastOrNull() },
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator(),
            ),
            entryProvider = entryProvider {
                entry<HomeRoute> {
                    HomeScreen(
                        setTopAppBarState = { topAppBarState.value = it },
                        navigateToDetails = { photo ->
                            backStack.add(DetailsRoute(photo.id, photo.imageUrl))
                        }
                    )
                }
                entry<DetailsRoute> { route ->
                    DetailsScreen(
                        photoId = route.photoId,
                        imageUrl = route.imageUrl,
                        setTopAppBarState = { topAppBarState.value = it },
                        onNavigateBack = { backStack.removeLastOrNull() }
                    )
                }
            }
        )
    }
}

@Serializable
data object HomeRoute : NavKey

@Serializable
data class DetailsRoute(val photoId: String, val imageUrl: String) : NavKey
