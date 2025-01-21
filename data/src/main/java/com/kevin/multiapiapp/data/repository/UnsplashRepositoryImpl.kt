package com.kevin.multiapiapp.data.repository

import com.kevin.multiapiapp.common.network.NetworkResponse
import com.kevin.multiapiapp.data.BuildConfig
import com.kevin.multiapiapp.data.datasource.unsplash.UnsplashDataSource
import com.kevin.multiapiapp.data.mapper.unsplash.UnsplashMapper
import com.kevin.multiapiapp.domain.model.unsplash.UnsplashDomain
import com.kevin.multiapiapp.domain.repository.UnsplashRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UnsplashRepositoryImpl @Inject constructor(
    private val unsplashDataSource: UnsplashDataSource
) : UnsplashRepository {

    override suspend fun searchPhotos(query: String, page: Int): Flow<UnsplashDomain> =
        flow {
            val clientId = BuildConfig.UNSPLASH_CLIENT_ID
            when (val response = unsplashDataSource.searchPhotos(query, page, clientId)) {
                is NetworkResponse.Success -> emit(UnsplashMapper.mapToDomain(response.data))
                is NetworkResponse.Failure -> {
                    throw Exception("Error: ${response.error.message}")
                }
            }
        }
}