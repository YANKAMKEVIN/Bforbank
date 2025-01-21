package com.kevin.multiapiapp.data.datasource.unsplash.impl

import com.kevin.multiapiapp.common.network.NetworkResponse
import com.kevin.multiapiapp.common.network.NetworkUtil
import com.kevin.multiapiapp.data.api.unsplash.UnsplashApi
import com.kevin.multiapiapp.data.datasource.unsplash.UnsplashDataSource
import com.kevin.multiapiapp.data.model.unsplash.UnsplashResponseDTO
import javax.inject.Inject

class UnsplashDataSourceImpl @Inject constructor(
    private val unsplashApi: UnsplashApi
) : UnsplashDataSource {

    override suspend fun searchPhotos(
        query: String,
        page: Int,
        clientId: String
    ): NetworkResponse<UnsplashResponseDTO> =
        NetworkUtil.executeApiCall { unsplashApi.searchPhotos(query, page, clientId = clientId) }

}
