package com.example.marsphotos.fake

import com.example.marsphotos.ui.screens.home.HomeUiState
import com.example.marsphotos.ui.screens.home.HomeViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import rules.TestDispatcherRule

class HomeViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun homeViewModel_getMarsPhotos_verifyHomeUiStateSuccess() =
        runTest {
            val homeViewModel = HomeViewModel(
                marsPhotosRepository = FakeNetworkMarsPhotosRepository()
            )
            assertEquals(
                HomeUiState.Success(FakeDataSource.photosList),
                homeViewModel.homeUiState
            )
        }
}