package com.example.marsphotos

import com.example.marsphotos.data.MarsPhotosRepository
import com.example.marsphotos.data.NetworkMarsPhotosRepository
import com.example.marsphotos.network.MarsApiService
import com.example.marsphotos.ui.screens.detail.DetailViewModel
import com.example.marsphotos.ui.screens.home.HomeViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

val appModule = module {

    single<Retrofit> {
        Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(BASE_URL)
            .build()
    }

    single {
        get<Retrofit>().create(MarsApiService::class.java)
    }

    single<MarsPhotosRepository> {
        NetworkMarsPhotosRepository(get())
    }

    viewModel {
        HomeViewModel(get())
    }

    viewModel { (photoId: String, imageUrl: String) ->
        DetailViewModel(photoId, imageUrl)
    }
}