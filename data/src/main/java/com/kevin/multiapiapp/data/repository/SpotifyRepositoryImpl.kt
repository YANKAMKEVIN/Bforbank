package com.kevin.multiapiapp.data.repository

import com.kevin.multiapiapp.common.network.NetworkResponse
import com.kevin.multiapiapp.data.datasource.spotify.SpotifyDataSource
import com.kevin.multiapiapp.data.mapper.spotify.SpotifyMapper
import com.kevin.multiapiapp.domain.model.spotify.SpotifySearchDomain
import com.kevin.multiapiapp.domain.repository.SpotifyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Implementation of the [SpotifyRepository] interface.
 * This class is responsible for processing the data returned by the [SpotifyDataSource]
 * and transforming it into a domain model.
 */
class SpotifyRepositoryImpl @Inject constructor(
    private val spotifyDataSource: SpotifyDataSource
) : SpotifyRepository {

    /**
     * Searches for tracks by calling the data source and mapping the result
     * into a domain model.
     *
     * @param query The search term to find tracks.
     * @param offset The offset for pagination.
     * @return A [Flow] emitting the search results.
     */
    override suspend fun searchTracks(query: String, offset: Int): Flow<SpotifySearchDomain> =
        flow {
            when (val response = spotifyDataSource.searchTracks(query = query, offset = offset)) {
                is NetworkResponse.Success -> emit(SpotifyMapper.mapToDomain(response.data))
                is NetworkResponse.Failure -> {
                    throw Exception("Error: ${response.error.message}")
                }
            }
        }
}
