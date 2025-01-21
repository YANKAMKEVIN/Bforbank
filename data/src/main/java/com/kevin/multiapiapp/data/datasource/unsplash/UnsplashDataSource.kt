package com.kevin.multiapiapp.data.datasource.unsplash

import com.kevin.multiapiapp.common.network.NetworkResponse
import com.kevin.multiapiapp.data.model.unsplash.UnsplashResponseDTO

interface UnsplashDataSource {
    suspend fun searchPhotos(
        query: String,
        page: Int,
        clientId: String
    ): NetworkResponse<UnsplashResponseDTO>
}
